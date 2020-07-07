package org.example.response;

public class CounterResponse {
    Long value;

    public CounterResponse() {
    }

    public CounterResponse(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
