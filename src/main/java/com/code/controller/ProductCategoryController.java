package com.code.controller;

import com.code.model.ProductCategory;
import com.code.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product/category")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService categoryService;

    @RequestMapping
    public String getAllCategory(Model model)
    {
        List<ProductCategory> categoryList = categoryService.getAll();
        model.addAttribute("categoryList", categoryList);
        return "categoryList";
    }

    @RequestMapping(path = {"/add", "/edit/{id}"})
    public String addOrEdit (Model model, @PathVariable("id") Optional<Long> id) {
        if(id.isPresent()) {
            ProductCategory category = categoryService.getCategoryById(id.get());
            model.addAttribute("category", category);
        } else {
            ProductCategory category = new ProductCategory();
            category.setActive(true);
            model.addAttribute("category", category);
        }
        return "categoryForm";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save (ProductCategory category) {
        categoryService.saveCategory(category);
        return "redirect:/product/category";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteById(Model model, @PathVariable("id") Long id)
    {
        categoryService.deleteById(id);
        return "redirect:/product/category";
    }

}
