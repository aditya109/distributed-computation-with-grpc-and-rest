package io.github.middleware;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerProvider {
    Logger logger;

    public LoggerProvider(Object object) {
        this.logger = LogManager.getLogger(object.getClass());
    }

    public Logger provideLoggerInstance() {
        logger.trace("configuration file defined to be :: " + System.getProperty("log4j.configurationFile"));
        return this.logger;
    }
}
