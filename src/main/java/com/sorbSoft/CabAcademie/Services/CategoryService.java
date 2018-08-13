package com.sorbSoft.CabAcademie.Services;


import com.sorbSoft.CabAcademie.Entities.Category;
import com.sorbSoft.CabAcademie.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dany on 15/05/2018.
 */
@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> fetchAllCategories(){
        return categoryRepository.findAll();
    }

    public Category fetchCategory(Long id){
        return categoryRepository.findOne(id);
    }

    public Category updateCategory (Category category){
        Category currentCategory= categoryRepository.findOne(category.getId());
        currentCategory.setDescription(category.getDescription());
        currentCategory.setName(category.getName());
        currentCategory.setParentCategory(category.getParentCategory());
        return categoryRepository.save(currentCategory);
    }

    public Category saveCategory (Category category){
        return categoryRepository.save(category);
    }
    public void deleteCategory(Long id){
        categoryRepository.delete(id);
    }
    //other delete methods
    //other fetching methods

}
