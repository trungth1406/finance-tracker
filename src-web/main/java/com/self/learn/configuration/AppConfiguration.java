package com.self.learn.configuration;


import com.self.learn.file.CompositeJsonWriter;
import com.self.learn.file.DailyJsonWriter;
import com.self.learn.file.DailyXMLWriter;
import com.self.learn.file.MonthlyJsonWriter;
import com.self.learn.file.base.Writer;
import com.self.learn.transaction.tracker.base.Trackable;
import com.self.learn.transaction.tracker.base.Tracker;
import com.self.learn.transaction.tracker.impl.CompositeTracker;
import com.self.learn.transaction.tracker.impl.DailyTracker;
import com.self.learn.transaction.tracker.impl.MonthlyTracker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class AppConfiguration {


    @Bean
    public Tracker composite() {
        Tracker composite = new CompositeTracker(new ArrayList<>(Arrays.asList(daily(), monthlyWriter())));
        return composite;
    }

    @Bean
    public Trackable daily() {
        return new DailyTracker(dailyWriter());
    }

    @Bean
    public Trackable monthlyWriter() {
        return new MonthlyTracker(monthlyJsonWriter());
    }

    @Bean
    public Writer dailyWriter(){
        CompositeJsonWriter composite = new CompositeJsonWriter();
        composite.setChildrenWriter(dailyJsonWriter(),dailyXMLWriter());
        return composite;
    }

    @Bean
    public  Writer dailyJsonWriter(){
        return new DailyJsonWriter();
    }

    @Bean
    public  Writer dailyXMLWriter(){
        return new DailyXMLWriter();
    }

    @Bean
    public  Writer monthlyJsonWriter(){
        return new MonthlyJsonWriter();
    }


}
