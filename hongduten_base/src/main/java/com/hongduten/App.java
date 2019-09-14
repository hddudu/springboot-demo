package com.hongduten;

import com.hongduten.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {
        SpringApplication application = new SpringApplication(App.class);
        application.run(args);
    }


    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 2);
    }
}
