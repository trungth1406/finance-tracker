package com.self.learn.configuration;


import com.self.learn.exporter.impl.JsonExporter;
import com.self.learn.exporter.impl.XmlExporter;
import com.self.learn.exporter.base.Exporter;
import com.self.learn.transaction.tracker.base.Trackable;
import com.self.learn.transaction.tracker.impl.DailyTracker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public Trackable daily() {
        return new DailyTracker(jsonWriter(), xmlWriter());
    }

    @Bean
    public Exporter xmlWriter() {
        Exporter xmlwriter = new XmlExporter();
        return xmlwriter;
    }

    @Bean
    public Exporter jsonWriter() {
        JsonExporter writer = new JsonExporter();
        return writer;
    }


}
