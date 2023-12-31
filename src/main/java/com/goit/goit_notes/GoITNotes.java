package com.goit.goit_notes;

import com.goit.goit_notes.configuration.ContextFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication(exclude = FlywayAutoConfiguration.class)
public class GoITNotes {

    public static void main(String[] args) {

        new SpringApplicationBuilder()
            .sources(GoITNotes.class)
            .bannerMode(Banner.Mode.OFF)
            .contextFactory(new ContextFactory())
            .build()
            .run(args);
    }
}
