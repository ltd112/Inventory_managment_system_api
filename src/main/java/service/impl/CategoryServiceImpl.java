package service.impl;

import dto.CategoryDTO;
import entity.Category;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;
import service.CategoryService;

import java.lang.module.ResolutionException;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private  CategoryRepository categoryRepository;


    @Override
    public CategoryDTO rewriteCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryDTO.getCategoryId()).
                orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryDTO.getCategoryId()));
        category.setId(categoryDTO.getCategoryId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        Category saveCategory = categoryRepository.save(category);

        return mapToDTO(saveCategory);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = mapToEntity(categoryDTO);
        Category newCategory = categoryRepository.save(category);
        return mapToDTO(newCategory);
    }

    @Override
    public CategoryDTO getCategoryById(String id) {
        return mapToDTO(categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id)));
    }

    @Override
    public CategoryDTO updateCategoryByID(CategoryDTO categoryDTO, String id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
        category.setId(categoryDTO.getCategoryId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setProducts(categoryDTO.getProducts());
        return mapToDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategoryByID(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);  

    }

    private CategoryDTO mapToDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }

    private Category mapToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getCategoryId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
}
