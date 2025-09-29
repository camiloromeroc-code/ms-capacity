package com.bootcamps.ms_technologies.infrastructure.config;


import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.bootcamps.ms_technologies.infrastructure.repository")
public class R2dbcConfig extends AbstractR2dbcConfiguration {

    @Bean
    @Override
    public ConnectionFactory connectionFactory() {

        return io.r2dbc.spi.ConnectionFactories.get(
                io.r2dbc.spi.ConnectionFactoryOptions.builder()
                        .option(io.r2dbc.spi.ConnectionFactoryOptions.DRIVER, "mysql")
                        .option(io.r2dbc.spi.ConnectionFactoryOptions.HOST, "localhost")
                        .option(io.r2dbc.spi.ConnectionFactoryOptions.PORT, 3306)
                        .option(io.r2dbc.spi.ConnectionFactoryOptions.USER, "root")
                        .option(io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD, "0000")
                        .option(io.r2dbc.spi.ConnectionFactoryOptions.DATABASE, "ms_capacities")
                        .build()
        );
    }
}
