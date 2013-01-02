package org.skife.natty;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.EnumSet;

public class SyslogTest
{
    @Test
    public void testApiDoesStuff() throws Exception
    {
        Syslog.openlog(SyslogTest.class.getName(), EnumSet.of(Syslog.LogOption.PERROR), Syslog.Facility.USER);
        Syslog.notice("hello notice");
        Syslog.debug("hello debug");
        Syslog.error("hello error");
    }

    @Test
    @Ignore
    public void testBenchmark() throws Exception
    {
        Syslog.openlog(SyslogTest.class.getName(), EnumSet.of(Syslog.LogOption.NOWAIT), Syslog.Facility.USER);

        for (int i = 0; i < 100000000; i++) {
            Syslog.info("hello world");
        }

    }

    @Test
    @Ignore
    public void testLogToFileVsSyslog() throws Exception
    {
        Thread t = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                try {
                    DatagramSocket ds = new DatagramSocket(10514);
                    byte[] buffer = new byte[2048];
                    DatagramPacket packet = new DatagramPacket(buffer, 2048);
                    while (!Thread.interrupted()) {
                        ds.receive(packet);
                    }
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();

        Logger log = LoggerFactory.getLogger(SyslogTest.class);
        Syslog.openlog(SyslogTest.class.getName(), EnumSet.of(Syslog.LogOption.NOWAIT), Syslog.Facility.USER);

        for (int i = 0; i < 100000; i++) {
            log.info("hello world");
        }
        long start1 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            log.info("hello world");
        }
        long stop1 = System.nanoTime();

        for (int i = 0; i < 100000; i++) {
            Syslog.info("hello world");
        }
        long start2 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            Syslog.info("hello world");
        }
        long stop2 = System.nanoTime();

        System.out.println(stop1 - start1);
        System.out.println(stop2 - start2);
        t.interrupt();
    }
}
