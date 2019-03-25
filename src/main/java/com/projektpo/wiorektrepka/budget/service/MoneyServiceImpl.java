package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Money;
import com.projektpo.wiorektrepka.budget.repository.CategoryRepository;
import com.projektpo.wiorektrepka.budget.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("moneyService")
@RequiredArgsConstructor
public class MoneyServiceImpl implements MoneyService {
    private final MoneyRepository moneyRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;

    @Override
    public Money getUserMoneyById(Integer id) {
        Optional<Money> o = moneyRepository.findById(id);
        if (checkUserMoney(o, id)) {
            Money m = o.get();
            if (m.getOwner().getUName().equals(userService.getCurrentUserNick()))
                return m;
        }
        return null;
    }

    @Override
    public void addNewMoney(String name, String type, Integer categoryId) {
        Money m = generateMoney(name, type, categoryId);
        m.setOwner(userService.getCurrentUser());
        moneyRepository.save(m);
    }

    @Override
    public Money generateMoney(String name, String type, Integer categoryId) {
        Money m = new Money();
        m.setMName(name);
        m.setType(type);
        m.setCategory(categoryRepository.getOne(categoryId));
        return m;
    }

    @Override
    public void editMoney(Integer id, String name, String type, Integer categoryId) {
        Optional<Money> o = moneyRepository.findById(id);
        if (checkUserMoney(o, id)) {
            Money m = o.get();
            m.setMName(name);
            m.setType(type);
            m.setCategory(categoryRepository.getOne(categoryId));
            moneyRepository.save(m);
        }
    }

    @Override
    public void deleteMoney(Integer id) {
        Optional<Money> o = moneyRepository.findById(id);
        if (checkUserMoney(o, id)) {
            Money m = o.get();
            moneyRepository.delete(m);
        }
    }

    public boolean checkUserMoney(Optional<Money> o, Integer id) {
        return id != null && o.isPresent();
    }
}
