package service;

import dto.CategoryDTO;

public interface CategoryService {
    CategoryDTO rewriteCategory(CategoryDTO categoryDTO); //put

    CategoryDTO createCategory(CategoryDTO categoryDTO); //post

    CategoryDTO getCategoryById(String id); //get

    CategoryDTO updateCategoryByID(CategoryDTO categoryDTO, String id); //patch

    void deleteCategoryByID(String id); //delete
}
