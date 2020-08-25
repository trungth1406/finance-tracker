package com.self.learn.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TransactionDTO {

    private LocalDate performedDate;
    private Map<String,String> props;

    public LocalDate getPerformedDate() {
        return performedDate;
    }

    public void setPerformedDate(LocalDate performedDate) {
        this.performedDate = performedDate;
    }


    public Map<String, String> getProps() {
        return props;
    }

    public void setProps(Map<String, String> props) {
        this.props = props;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransactionDTO{");
        sb.append("performedDate=").append(performedDate);
        sb.append(", props=").append(props);
        sb.append('}');
        return sb.toString();
    }
}
