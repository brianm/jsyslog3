package org.skife.natty;

import org.fusesource.hawtjni.runtime.FieldFlag;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniField;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.Library;
import org.fusesource.hawtjni.runtime.MethodFlag;

import java.util.EnumSet;

@JniClass
public class Syslog
{
    private static final Library LIBRARY = new Library("jsyslog3", Syslog.class);

    static {
        LIBRARY.load();
        init();
    }

    @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
    private static native void init();

    public static native void openlog(String ident, int logopt, int facility);

    public static void openlog(String ident, EnumSet<LogOption> logopt, Facility facility)
    {
        int flags = 0;
        for (LogOption option : logopt) {
            flags = flags | option.constant();
        }
        openlog(ident, flags, facility.constant());
    }

    public static native void syslog(int priority, String message);

    /**
     * debug-level messages
     */
    public static void debug(String msg)
    {
        syslog(LOG_DEBUG, msg);
    }

    /**
     * informational
     */
    public static void info(String msg)
    {
        syslog(LOG_INFO, msg);
    }

    /**
     * normal but significant condition
     */
    public static void notice(String msg)
    {
        syslog(LOG_NOTICE, msg);
    }

    /**
     * warning conditions
     */
    public static void warn(String msg)
    {
        syslog(LOG_WARNING, msg);
    }

    /**
     * error conditions
     */
    public static void error(String msg)
    {
        syslog(LOG_ERR, msg);
    }

    /**
     * critical conditions
     */
    public static void critical(String msg)
    {
        syslog(LOG_CRIT, msg);
    }

    /**
     * action must be taken immediately
     */
    public static void alert(String msg)
    {
        syslog(LOG_ALERT, msg);
    }

    /**
     * system is unusable
     */
    public static void emergency(String msg)
    {
        syslog(LOG_EMERG, msg);
    }

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_EMERG;

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_ALERT;

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_CRIT;

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_ERR;

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_WARNING;

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_NOTICE;

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_INFO;

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_DEBUG;


//    #define LOG_KERN        (0<<3)  /* kernel messages */
//    #define LOG_USER        (1<<3)  /* random user-level messages */
//    #define LOG_MAIL        (2<<3)  /* mail system */
//    #define LOG_DAEMON      (3<<3)  /* system daemons */
//    #define LOG_AUTH        (4<<3)  /* authorization messages */
//    #define LOG_SYSLOG      (5<<3)  /* messages generated internally by syslogd */
//    #define LOG_LPR         (6<<3)  /* line printer subsystem */
//    #define LOG_NEWS        (7<<3)  /* network news subsystem */
//    #define LOG_UUCP        (8<<3)  /* UUCP subsystem */
//    #define LOG_CRON        (9<<3)  /* clock daemon */
//    #define LOG_AUTHPRIV    (10<<3) /* authorization messages (private) */
    /* other codes through 15 reserved for system use */
//    #define LOG_LOCAL0      (16<<3) /* reserved for local use */
//    #define LOG_LOCAL1      (17<<3) /* reserved for local use */
//    #define LOG_LOCAL2      (18<<3) /* reserved for local use */
//    #define LOG_LOCAL3      (19<<3) /* reserved for local use */
//    #define LOG_LOCAL4      (20<<3) /* reserved for local use */
//    #define LOG_LOCAL5      (21<<3) /* reserved for local use */
//    #define LOG_LOCAL6      (22<<3) /* reserved for local use */
//    #define LOG_LOCAL7      (23<<3) /* reserved for local use */


    public static enum Facility
    {
        LOCAL0
            {
                public int constant() { return LOG_LOCAL0; }
            },
        LOCAL1
            {
                public int constant() { return LOG_LOCAL1; }
            },
        LOCAL2
            {
                public int constant() { return LOG_LOCAL2; }
            },
        LOCAL3
            {
                public int constant() { return LOG_LOCAL3; }
            },
        LOCAL4
            {
                public int constant() { return LOG_LOCAL4; }
            },
        LOCAL5
            {
                public int constant() { return LOG_LOCAL5; }
            },
        LOCAL6
            {
                public int constant() { return LOG_LOCAL6; }
            },
        LOCAL7
            {
                public int constant() { return LOG_LOCAL7; }
            },
        USER
            {
                public int constant() { return LOG_USER; }
            },
        DAEMON
            {
                public int constant() { return LOG_DAEMON; }
            };

        public abstract int constant();
    }

    @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LOG_USER")
    public static int LOG_USER;

    @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LOG_DAEMON")
    public static int LOG_DAEMON;

    @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LOG_LOCAL0")
    public static int LOG_LOCAL0;

    @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LOG_LOCAL1")
    public static int LOG_LOCAL1;

    @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LOG_LOCAL2")
    public static int LOG_LOCAL2;

    @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LOG_LOCAL3")
    public static int LOG_LOCAL3;

    @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LOG_LOCAL4")
    public static int LOG_LOCAL4;

    @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LOG_LOCAL5")
    public static int LOG_LOCAL5;

    @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LOG_LOCAL6")
    public static int LOG_LOCAL6;

    @JniField(flags = {FieldFlag.CONSTANT}, accessor = "LOG_LOCAL7")
    public static int LOG_LOCAL7;

//    #define LOG_PID         0x01    /* log the pid with each message */
//    #define LOG_CONS        0x02    /* log on the console if errors in sending */
//    #define LOG_ODELAY      0x04    /* delay open until first syslog() (default) */
//    #define LOG_NDELAY      0x08    /* don't delay open */
//    #define LOG_NOWAIT      0x10    /* don't wait for console forks: DEPRECATED */
//    #define LOG_PERROR      0x20    /* log to stderr as well */

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_PID;

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_CONS;

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_ODELAY;

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_NDELAY;

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_NOWAIT;

    @JniField(flags = {FieldFlag.CONSTANT})
    public static int LOG_PERROR;

    public static enum LogOption {
        PID() { public int constant() { return LOG_PID; } },
        CONS() {public int constant() { return LOG_CONS; }},
        ODELAY() {public int constant() { return LOG_ODELAY; }},
        NDELAY() {public int constant() { return LOG_NDELAY; }},
        NOWAIT() {public int constant() { return LOG_NOWAIT; }},
        PERROR() {public int constant() { return LOG_PERROR; }};

        public abstract int constant();
    }

}
