package com.group.libraryapp.controller.calculator.book;

import com.group.libraryapp.dto.request.book.BookCreateRequest;
import com.group.libraryapp.service.book.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public void saveBooke(@RequestBody BookCreateRequest request){
        bookService.saveBook(request);
    }
}
