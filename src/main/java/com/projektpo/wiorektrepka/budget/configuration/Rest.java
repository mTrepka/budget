package com.projektpo.wiorektrepka.budget.configuration;


import com.projektpo.wiorektrepka.budget.domain.Money;
import com.projektpo.wiorektrepka.budget.service.MoneyService;
import com.projektpo.wiorektrepka.budget.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class Rest {
    private final UserService userService;
    private final MoneyService moneyService;


    @GetMapping("/money/")
    public List<Money> getUserMoney(String category){
        List<Money> moneys = userService.getCurrentUser().getMoneyList();
        if(category==null)
        return moneys;
        return moneys.stream().filter(e -> e.getCategory().getName().equals(category)).collect(Collectors.toList());
    }
    @GetMapping("/money/{id}")
    public Money getUserMoneyById(@PathVariable("id") Integer id){
        return moneyService.getUserMoneyById(id);
    }
    @PostMapping("/money/")
    public void addNewMoney(String name,String type,Integer categoryId){
        moneyService.addNewMoney(name,type,categoryId);
    }
    @PostMapping("/money/edit/{id}")
    public void editMoney(@PathVariable("id") Integer id, String name,String type,Integer categoryId){
        moneyService.editMoney(id,name,type,categoryId);
    }
    @PostMapping("/money/delete/{id}")
    public void deleteMoney(@PathVariable("id") Integer id){
        moneyService.deleteMoney(id);
    }
}
