package com.test.controllers;

import com.test.entities.Cart;
import com.test.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.GET)
    public ResponseEntity getCart(@PathVariable long id) throws Exception{
        Cart cart = cartRepository.findOne(id);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public ResponseEntity saveCart(@RequestBody Cart cart) throws Exception {
        cartRepository.save(cart);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCart(@PathVariable long id) throws Exception {

        cartRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateCart(@PathVariable long id, @RequestBody Cart cart) throws Exception {

        Cart getCategory = cartRepository.findOne(id);

        cartRepository.save(getCategory);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
