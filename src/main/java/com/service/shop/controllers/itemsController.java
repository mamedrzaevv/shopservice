package com.service.shop.controllers;

import com.service.shop.models.items;
import com.service.shop.repo.itemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class itemsController {

    @Autowired
    private itemsRepository itemsRepository;

    @GetMapping("/items")
    public String items(Model model) {
        Iterable<items> items = itemsRepository.findAll();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/item/{id}/edit")
    public String itemEdit(@PathVariable(value = "id") Integer id, Model model) {
        Optional<items> item = itemsRepository.findById(id);
        ArrayList<items> res = new ArrayList<>();
        item.ifPresent(res::add);
        model.addAttribute("item", res);
        return "edit";
    }

    @PostMapping("/item/{id}/edit")
    public String itemUpdate(@PathVariable(value = "id") Integer id, @RequestParam String name, @RequestParam Integer price, Model model) {
        items item = itemsRepository.findById(id).orElseThrow();
        item.setName(name);
        item.setPrice(price);
        itemsRepository.save(item);
        return "redirect:/items";
    }

    @PostMapping("/item/{id}/remove")
    public String itemDelete(@PathVariable(value = "id") Integer id, Model model) {
        items item = itemsRepository.findById(id).orElseThrow();
        itemsRepository.delete(item);
        return "redirect:/items";
    }
}
