package com.code.service;

import com.code.model.ProductCategory;
import com.code.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getAll() {
        List<ProductCategory> categoryList = (List<ProductCategory>) productCategoryRepository.findAll();
        if (categoryList.size() > 0) {
            return categoryList;
        } else {
            return new ArrayList<ProductCategory>();
        }
    }

    public ProductCategory getCategoryById(Long id) {
        Optional<ProductCategory> category = productCategoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        } else {
            return new ProductCategory();
        }
    }

    public ProductCategory saveCategory (ProductCategory entity) {
        if (entity.getId() == null) {
            entity = productCategoryRepository.save(entity);
            return entity;
        } else {
            Optional<ProductCategory> category = productCategoryRepository.findById(entity.getId());
            if (category.isPresent()) {
                ProductCategory newCategory = category.get();
                newCategory.setName(entity.getName());
                newCategory.setDescription(entity.getDescription());
                newCategory.setActive(entity.isActive());

                newCategory = productCategoryRepository.save(newCategory);

                return newCategory;
            } else {
                entity = productCategoryRepository.save(entity);

                return entity;
            }
        }
    }

    public void deleteById(Long id)
    {
        Optional<ProductCategory> category = productCategoryRepository.findById(id);

        if(category.isPresent())
        {
            productCategoryRepository.deleteById(id);
        }
    }
}
