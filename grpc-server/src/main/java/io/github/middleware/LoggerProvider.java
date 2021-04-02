package io.github.middleware;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerProvider {
    private Object object;

    public LoggerProvider(Object object) {
        this.object = object;
    }

    public Logger provideLoggerInstance() {
        Logger logger = LogManager.getLogger(object.getClass());
        logger.trace("configuration file defined to be :: " + System.getProperty("log4j.configurationFile"));
        return logger;
    }
}
