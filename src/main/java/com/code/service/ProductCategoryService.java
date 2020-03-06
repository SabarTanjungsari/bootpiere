package com.code.service;

import com.code.exception.RecordNotFoundException;
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
    ProductCategoryRepository categoryRepository;

    public List<ProductCategory> getAll() {
        List<ProductCategory> categoryList = (List<ProductCategory>) categoryRepository.findAll();
        if (categoryList.size() > 0) {
            return categoryList;
        } else {
            return new ArrayList<ProductCategory>();
        }
    }

    public ProductCategory getCategoryById(Long id) throws RecordNotFoundException {
        Optional<ProductCategory> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public ProductCategory saveCategory (ProductCategory entity) {
        if (entity.getId() == null) {
            entity = categoryRepository.save(entity);
            return entity;
        } else {
            Optional<ProductCategory> category = categoryRepository.findById(entity.getId());
            if (category.isPresent()) {
                ProductCategory newCategory = category.get();
                newCategory.setName(entity.getName());
                newCategory.setDescription(entity.getDescription());
                newCategory.setActive(entity.isActive());

                newCategory = categoryRepository.save(newCategory);

                return newCategory;
            } else {
                entity = categoryRepository.save(entity);

                return entity;
            }
        }
    }

    public void deleteById(Long id) throws RecordNotFoundException
    {
        Optional<ProductCategory> category = categoryRepository.findById(id);

        if(category.isPresent())
        {
            categoryRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
