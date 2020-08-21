package com.self.learn.configuration;


import com.self.learn.file.JsonWriter;
import com.self.learn.file.XmlWriter;
import com.self.learn.file.base.Writer;
import com.self.learn.transaction.tracker.base.Trackable;
import com.self.learn.transaction.tracker.base.Tracker;
import com.self.learn.transaction.tracker.impl.CompositeTracker;
import com.self.learn.transaction.tracker.impl.DailyTracker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class AppConfiguration {

    @Bean
    public Trackable daily() {
        return new DailyTracker(jsonWriter(), xmlWriter());
    }

    @Bean
    public Writer xmlWriter() {
        Writer xmlwriter = new XmlWriter();
        return xmlwriter;
    }

    @Bean
    public Writer jsonWriter() {
        JsonWriter writer = new JsonWriter();
        return writer;
    }


}
