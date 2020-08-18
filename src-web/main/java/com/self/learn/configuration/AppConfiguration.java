package com.self.learn.configuration;


import com.self.learn.financemodel.Saving;
import com.self.learn.transaction.tracker.base.Trackable;
import com.self.learn.transaction.tracker.base.Tracker;
import com.self.learn.transaction.tracker.impl.CompositeTracker;
import com.self.learn.transaction.tracker.impl.DailyTracker;
import com.self.learn.transaction.tracker.impl.MonthlyTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class AppConfiguration {


    @Bean
    public Tracker composite(){
        Tracker composite = new CompositeTracker(new ArrayList<>(Arrays.asList(daily(),monthly())));
        return composite;
    }

    @Bean
    public Trackable daily(){
       return new DailyTracker();
    }

    @Bean
    public Trackable monthly(){
        return new MonthlyTracker();
    }


}
