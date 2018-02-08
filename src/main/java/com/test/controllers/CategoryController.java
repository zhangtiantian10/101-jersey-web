package com.test.controllers;

import com.test.entities.Category;
import com.test.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ResponseEntity category(@PathVariable long id) throws Exception{
        Category category = categoryRepository.findOne(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public ResponseEntity saveItem(@RequestBody Category category) throws Exception {
        categoryRepository.save(category);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteItem(@PathVariable long id) throws Exception {

        categoryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateItem(@PathVariable long id, @RequestBody Category category) throws Exception {

        Category getCategory = categoryRepository.findOne(id);

        getCategory.setType(category.getType());
        categoryRepository.save(getCategory);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
