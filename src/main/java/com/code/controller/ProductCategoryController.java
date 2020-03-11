package com.code.controller;

import com.code.model.ProductCategory;
import com.code.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.TransactionalException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product/category")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @RequestMapping
    public String getAllCategory(Model model)
    {
        List<ProductCategory> categoryList = productCategoryService.getAll();
        model.addAttribute("categoryList", categoryList);
        return "categoryList";
    }

    @RequestMapping(path = {"/add", "/edit/{id}"})
    public String addOrEdit (Model model, @PathVariable("id") Optional<Long> id) {

        if(id.isPresent()) {
            ProductCategory productCategory = productCategoryService.getCategoryById(id.get());
            model.addAttribute("productCategory", productCategory);
        } else {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setActive(true);
            model.addAttribute("productCategory", productCategory);
        }
        return "categoryForm";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
        public String save(@ModelAttribute @Valid ProductCategory productCategory, BindingResult bindingResult, Model model){
            if(bindingResult.hasErrors()){
                return "categoryForm";
            } else {
                try {
                    productCategoryService.saveCategory(productCategory);
                } catch (DataIntegrityViolationException e){
                    bindingResult.rejectValue("name", "error.productCategory", "Name of category already exists");
                    return "categoryForm";
                }
            }

            return "redirect:/product/category/";
        }

    @RequestMapping(path = "/delete/{id}")
    public String deleteById(Model model, @PathVariable("id") Long id) {
        ProductCategory category = productCategoryService.getCategoryById(id);
        productCategoryService.deleteById(id);
        return "redirect:/product/category";
    }

}
