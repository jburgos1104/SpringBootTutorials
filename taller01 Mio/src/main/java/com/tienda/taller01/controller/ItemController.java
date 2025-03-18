package com.tienda.taller01.controller;

import com.tienda.taller01.models.Item;
import com.tienda.taller01.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "items";
    }

    @GetMapping("/new")
    public String newItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "item-form";
    }

    @PostMapping
    public String saveItem(@ModelAttribute Item item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return "redirect:/items";
    }

    @GetMapping("/{id}")
    public String viewItem(@PathVariable Long id, Model model) {
        Optional<Item> item = itemService.getItemById(id);
        if (item.isPresent()) {
            model.addAttribute("item", item.get());
            return "show";
        } else {
            return "redirect:/items";
        }
    }


}