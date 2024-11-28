package controller;

import dto.CategoryDTO;
import exception.BadRequestException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable(name = "categoryId") String id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> rewriteCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO category = categoryService.rewriteCategory(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        if (categoryDTO.getCategoryId() == null){
            log.error("cant have an ID {}", categoryDTO);
            throw new BadRequestException(CategoryController.class.getSimpleName(), "Id");
        }
        CategoryDTO category = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);

    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategoryByID(@RequestBody CategoryDTO categoryDTO, @PathVariable(name = "categoryId") String id){
        CategoryDTO category = categoryService.updateCategoryByID(categoryDTO, id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategoryByID(@PathVariable(name = "categoryId") String id){
        categoryService.deleteCategoryByID(id);
        return new ResponseEntity<>("deleted complete", HttpStatus.OK);
    }
}
