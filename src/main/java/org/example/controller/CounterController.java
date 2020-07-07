package org.example.controller;

import org.example.response.CounterResponse;
import org.example.form.CounterIncrementForm;
import org.example.service.CounterService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("counter")
public class CounterController {

    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping
    public CounterResponse getCounter() {
        return new CounterResponse(counterService.getCounter());
    }

    @PostMapping
    public CounterResponse incrementCounter(@Valid @RequestBody CounterIncrementForm incrementForm) {
        counterService.increment(incrementForm.getIncrement());
        return new CounterResponse(counterService.getCounter());
    }
}
