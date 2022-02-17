package com.example.batchnative;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.ResourceHint;

@NativeHint(
        resources = @ResourceHint(patterns = "images/toolbarButtonGraphics/general/Save16.gif")
)
@EnableBatchProcessing
@SpringBootApplication
public class BatchNativeApplication {


    public static void main(String[] args) {
        SpringApplication.run(BatchNativeApplication.class, args);
    }

    @Bean
    Step step(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory
                .get("step")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("hello, world!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    Job job(JobBuilderFactory jbf, Step step) {
        return jbf
                .get("hello")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
}