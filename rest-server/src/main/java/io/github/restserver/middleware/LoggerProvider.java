package io.github.restserver.middleware;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerProvider {
    private Class<?> className;

    public LoggerProvider(Class<?> className) {
        this.className = className;
    }

    public Logger provideLoggerInstance() {
        Logger logger = LogManager.getLogger(className);
        logger.trace("configuration file defined to be :: " + System.getProperty("log4j.configurationFile"));
        return logger;
    }
}

/**
private static Logger logger;
logger = new LoggerProvider(new GrpcServer()).provideLoggerInstance();


 */