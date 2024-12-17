package com.spring.mvcproject.chap2_3.practice.library.controller;

import com.spring.mvcproject.chap2_3.practice.library.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/vi/books")
public class LibraryController {

    private Map<Long, Book> bookList = new HashMap<>();
    private Long idx;

    // 전체 도서 조회
    @GetMapping
    public String searchAllBook() {
        try {
            if (!bookList.isEmpty()) {
                return bookList.toString();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "책이 없습니다.";
    }


    // 특정 도서 조회
    @GetMapping("/{id}")
    public String searchBook(
            @PathVariable("id") Long id
    ) {
        try {
            if (!bookList.isEmpty()) {
//                return "찾은 책 : " + bookList.get(id).getTitle();
                System.out.println("id = " + id);
                System.out.println(bookList.get(id));
                return bookList.get(id).toString();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "책이 없습니다.";
    }


    // 도서 추가
    @PostMapping
    public String addBook(
            String title
            , String author
            , int price
    ) {
        try {
            idx = (bookList.size() == 0) ? 1L : idx + 1;
            bookList.put(idx, new Book(title, author, price));
            return title + " 추가 되었습니다.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //도서 수정
    @PutMapping("/{id}")
    public String modifyBook(
            @PathVariable Long id
            , @RequestParam String title
            , @RequestParam String author
            , @RequestParam int price
    ) {
        try {
            Book selectedBook = bookList.get(id);
            selectedBook.setTitle(title);
            selectedBook.setAuthor(author);
            selectedBook.setPrice(price);
http://localhost:9000/api/vi/books?title=aaa&author=bbb&price=22000
            return "책 정보 변경 되었습니다. - " + title + ", " + author + ", " + price;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // 도서 삭제
    @DeleteMapping("/{id}")
    public String deleteBook(
            @PathVariable Long id
    ) {
        try {

            Book selectedBook = bookList.get(id);
            String deletedTitle = selectedBook.getTitle();
            bookList.remove(id);
            return deletedTitle + " 삭제 되었습니다. - ";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
