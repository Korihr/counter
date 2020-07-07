package org.example.form;

import javax.validation.constraints.Min;

public class CounterIncrementForm {
    @Min(0)
    private long increment;

    public CounterIncrementForm() {
    }

    public long getIncrement() {
        return increment;
    }

    public void setIncrement(long increment) {
        this.increment = increment;
    }
}
