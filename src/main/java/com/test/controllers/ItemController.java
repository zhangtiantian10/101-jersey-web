package com.test.controllers;

import com.test.entities.Item;
import com.test.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    public ResponseEntity getItem(@PathVariable long id) throws Exception{
        Item item = itemRepository.findOne(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public ResponseEntity saveItem(@RequestBody Item item) throws Exception {
        itemRepository.save(item);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteItem(@PathVariable long id) throws Exception {

        itemRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateItem(@PathVariable long id, @RequestBody Item item) throws Exception {

        Item getItem = itemRepository.findOne(id);

        getItem.setName(item.getName());
        getItem.setPrice(item.getPrice());
        itemRepository.save(getItem);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
