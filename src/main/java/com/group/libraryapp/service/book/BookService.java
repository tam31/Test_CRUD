package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loan.UserLoanHistory;
import com.group.libraryapp.domain.user.loan.UserLoanHistoryRepository;
import com.group.libraryapp.dto.request.book.BookCreateRequest;
import com.group.libraryapp.dto.request.book.BookLoanRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(
            UserRepository userRepository,
            BookRepository bookRepository,
            UserLoanHistoryRepository userLoanHistoryRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }

    public void loanBook(BookLoanRequest reqeust) {
        // 1.해당 책이 있는지 확인
        Book book = bookRepository.findByName(reqeust.getBookName())
                .orElseThrow(IllegalArgumentException::new);


        // 2.대출 가능한지 확인
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(),false)){
            throw new IllegalArgumentException("대출중 입니다");
        }

        // 3.회원아이디 확인
        User user = userRepository.findByName(reqeust.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        // 대출가능
        // 4.대출테이블 안에 데이터 넣기
        userLoanHistoryRepository.save(new UserLoanHistory(user.getId(),book.getName()));
    }
}
