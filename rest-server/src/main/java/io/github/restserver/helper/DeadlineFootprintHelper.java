package io.github.restserver.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class DeadlineFootprintHelper {
    @Autowired
    private Environment env;

    public int computeWorkersRequired(long dimension) {
        long responseTimeInMills = 800;
        String envDeadline = env.getProperty("compute_deadline");
        String envThreshold = env.getProperty("server_threshold");
        String envDelta = env.getProperty("delta");
        int workers = 0;
        if (envDeadline != null && envThreshold != null && envDelta != null) {
            long deadLine = Long.parseLong(envDeadline);
            int threshold = Integer.parseInt(envThreshold);
            long delta = Long.parseLong(envDelta);
            workers = (int) ((responseTimeInMills * dimension * dimension) / deadLine) + 1;
            System.out.println(deadLine + " " + responseTimeInMills + " " + dimension + " " + delta);
            if (threshold < workers) {
                System.out.println("Worker value exceeding threshold.\nSetting its value to threshold.");
                workers = threshold;
            }
        }
        return workers;
    }
}
