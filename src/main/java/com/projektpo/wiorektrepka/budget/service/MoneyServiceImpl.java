package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("moneyService")
@RequiredArgsConstructor
public class MoneyServiceImpl implements MoneyService{
    private final MoneyRepository moneyRepository;
}
