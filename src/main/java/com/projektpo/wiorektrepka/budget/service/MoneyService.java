package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Money;

public interface MoneyService {
    Money getUserMoneyById(Integer id);

    void addNewMoney(String name, String type, Integer categoryId);

    Money generateMoney(String name, String type, Integer categoryId);

    void editMoney(Integer id,String name, String type, Integer categoryId);

    void deleteMoney(Integer id);
}
