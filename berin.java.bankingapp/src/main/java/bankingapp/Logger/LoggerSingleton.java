package bankingapp.Logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class LoggerSingleton {

    private static Logger jdbcLogger;

    private LoggerSingleton() {
        super();
    }

    public static Logger GetSingleton() {

        if (jdbcLogger == null)
            LoggerSingleton.jdbcLogger = LogManager.getRootLogger();

        return jdbcLogger;

    }

}
