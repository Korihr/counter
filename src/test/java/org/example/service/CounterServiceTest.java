package org.example.service;

import org.example.entity.CounterIncrement;
import org.example.repository.CounterIncrementRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CounterServiceTest {

    private final CounterIncrementRepository counterIncrementRepository = mock(CounterIncrementRepository.class);
    private final CounterService counterService = new CounterService(counterIncrementRepository);

    @Test
    void increment() {
        long increment = 1L;
        counterService.increment(increment);

        verify(counterIncrementRepository, times(1)).save(new CounterIncrement(increment));
    }

    @Test
    void getCounter() {
        List<CounterIncrement> counterIncrements = List.of(
                new CounterIncrement(1L),
                new CounterIncrement(2L),
                new CounterIncrement(3L)
        );
        when(counterIncrementRepository.findAll()).thenReturn(counterIncrements);

        assertEquals(6L, counterService.getCounter());
    }
}