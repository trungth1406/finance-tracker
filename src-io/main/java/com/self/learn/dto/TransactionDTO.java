package com.self.learn.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TransactionDTO {

    private LocalDate performedDate;
    private Map<String,Integer> props;

    public LocalDate getPerformedDate() {
        return performedDate;
    }

    public void setPerformedDate(LocalDate performedDate) {
        this.performedDate = performedDate;
    }


    public Map<String, Integer> getProps() {
        return props;
    }

    public void setProps(Map<String, Integer> props) {
        this.props = props;
    }
}
