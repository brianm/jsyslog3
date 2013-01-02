package org.skife.natty;

import org.junit.Test;

import java.util.EnumSet;

public class SyslogTest
{
    @Test
    public void testFoo() throws Exception
    {
        Syslog.openlog(SyslogTest.class.getName(), EnumSet.of(Syslog.LogOption.PERROR), Syslog.Facility.USER);
        Syslog.notice("hello notice");
        Syslog.debug("hello debug");
        Syslog.error("hello error");
    }
}
