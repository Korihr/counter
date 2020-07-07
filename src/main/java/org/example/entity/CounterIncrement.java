package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class CounterIncrement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long increment;

    public CounterIncrement() {
    }

    public CounterIncrement(long increment) {
        this.increment = increment;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getIncrement() {
        return increment;
    }

    public void setIncrement(long increment) {
        this.increment = increment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CounterIncrement that = (CounterIncrement) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(increment, that.increment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, increment);
    }
}
