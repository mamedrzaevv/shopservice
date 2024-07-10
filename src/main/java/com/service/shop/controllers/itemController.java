package com.service.shop.controllers;

import com.service.shop.models.items;
import com.service.shop.repo.itemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class itemController {

    @Autowired
    private itemsRepository itemsRepository;

    @GetMapping("/item")
    public String item(Model model) {
        return "item";
    }

    @PostMapping("/item")
    public String itemAdd(@RequestParam String name, @RequestParam Integer price, Model model){
        items item = new items(name, price);
        itemsRepository.save(item);
        return "redirect:/";
    }
}
