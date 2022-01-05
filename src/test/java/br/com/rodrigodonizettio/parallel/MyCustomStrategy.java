package br.com.rodrigodonizettio.parallel;

import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;

public class MyCustomStrategy implements ParallelExecutionConfigurationStrategy {
    @Override
    public ParallelExecutionConfiguration createConfiguration(ConfigurationParameters configurationParameters) {
        // e.g. -Djunit.jupiter.execution.parallel.config.rodrigodonizettio.parallel.someconfig
        Integer someConfig = Integer.valueOf(configurationParameters.get("rodrigodonizettio.parallel.someconfig")
                .orElseThrow(IllegalArgumentException::new));

        System.out.println("SomeConfig: " + someConfig);

        return new ParallelExecutionConfiguration() {

            @Override
            public int getParallelism() {
                return someConfig;
            }

            @Override
            public int getMinimumRunnable() {
                return someConfig;
            }

            @Override
            public int getMaxPoolSize() {
                return someConfig;
            }

            @Override
            public int getCorePoolSize() {
                return someConfig;
            }

            @Override
            public int getKeepAliveSeconds() {
                return someConfig;
            }
        };
    }
}
