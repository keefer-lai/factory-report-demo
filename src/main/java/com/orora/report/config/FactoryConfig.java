package com.orora.report.config;

import com.orora.report.factory.ShoeBoxFactory;
import com.orora.report.factory.ShoeFactory;
import com.orora.report.factory.ShoeProductFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.YearMonth;

@Configuration
public class FactoryConfig {

    @Bean
    @Scope("singleton")
    public ShoeFactory shoeFactory() {
        return new ShoeFactory(1000, YearMonth.now());
    }

    @Bean
    @Scope("singleton")
    public ShoeBoxFactory shoeBoxFactory() {
        return new ShoeBoxFactory(800, YearMonth.now());
    }

    @Bean
    @Scope("singleton")
    public ShoeProductFactory shoeProductFactory() {
        return new ShoeProductFactory(700, YearMonth.now());
    }

}
