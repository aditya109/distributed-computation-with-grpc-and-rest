package io.github.restserver.helper;

import io.github.restserver.middleware.LoggerProvider;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class DeadlineFootPrinter {
    private final Environment env;
    private Logger logger;

    public DeadlineFootPrinter(Environment env) {
        this.env = env;
        this.logger = new LoggerProvider(DeadlineFootPrinter.class).provideLoggerInstance();
    }

    public int computeWorkersRequired(long dimension) {
        long responseTimeInMills = 800;
        String envDeadline = env.getProperty("compute_deadline");
        String envThreshold = env.getProperty("server_threshold");
        String envDelta = env.getProperty("delta");
        int workers = 0;
        if (envDeadline != null && envThreshold != null && envDelta != null) {
            long deadLine = Long.parseLong(envDeadline);
            int threshold = Integer.parseInt(envThreshold);
            workers = (int) ((responseTimeInMills * dimension * dimension) / deadLine) + 1;
            if (threshold < workers) {
                logger.warn("Worker value exceeding threshold.\nSetting its value to threshold.");
                workers = threshold;
            }
        }
        return workers;
    }
}
