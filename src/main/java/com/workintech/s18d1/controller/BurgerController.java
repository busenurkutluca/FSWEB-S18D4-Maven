package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDaoImpl;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.util.BurgerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/burger")
public class BurgerController {

    private final BurgerDaoImpl burgerDao;

    @Autowired
    public BurgerController(BurgerDaoImpl burgerDao) {
        this.burgerDao = burgerDao;
    }

    // Save a new burger
    @PostMapping
    public Burger save(@RequestBody Burger burger) {
        BurgerValidation.checkName(burger.getName());
        return burgerDao.save(burger);
    }

    // Get all burgers
    @GetMapping
    public List<Burger> findAll() {
        return burgerDao.findAll();
    }
    @GetMapping("/price/{price}")
    public List <Burger> getByPrice(@PathVariable("price") Integer price){
        return burgerDao.findByPrice(price);
    }
    @GetMapping("/content/{content}")
    public List <Burger> getByContent(@PathVariable("content") String content){
        return burgerDao.findByContent(content);
    }

    // Get burger by ID
    @GetMapping("/{id}")
    public Burger find(@PathVariable long id) {
        return burgerDao.findById(id);
    }

    // Update an existing burger
    @PutMapping
    public Burger update(@RequestBody Burger burger) {
        BurgerValidation.checkName(burger.getName());
        return burgerDao.update(burger);
    }

    // Delete a burger by ID
    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id) {
        burgerDao.remove(id);
    }

    // Find burgers by bread type
    @GetMapping("/breadType/{breadType}")
    public List<Burger> getByBreadType(@PathVariable("breadType") String breadType) {
        BreadType btEnum = BreadType.valueOf(breadType);
        return burgerDao.findByBreadType(btEnum);
    }


}
