package org.example.service;

import org.example.entity.CounterIncrement;
import org.example.repository.CounterIncrementRepository;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

    private final CounterIncrementRepository counterIncrementRepository;

    public CounterService(CounterIncrementRepository counterIncrementRepository) {
        this.counterIncrementRepository = counterIncrementRepository;
    }

    public void increment(long increment) {
        CounterIncrement counterIncrement = new CounterIncrement(increment);
        counterIncrementRepository.save(counterIncrement);
    }

    public Long getCounter() {
        return counterIncrementRepository.findAll().stream()
                .map(CounterIncrement::getIncrement)
                .reduce(0L, Long::sum);
    }

}
