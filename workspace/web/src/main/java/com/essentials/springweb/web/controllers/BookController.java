package com.essentials.springweb.web.controllers;

import java.util.Arrays;
import java.util.List;

import com.essentials.springweb.web.model.Book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class BookController {

    @GetMapping("/books")
    @Operation(summary = "Get list of all books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks () {
        return Arrays.asList(new Book(1l, "Mastering Spring 5.0", "Anurag K"));
    } 
    
}
