package com.system.travelmanagement.service.impl;
import com.system.travelmanagement.Entity.Book;
import com.system.travelmanagement.Entity.User;
import com.system.travelmanagement.Pojo.BookPojo;
import com.system.travelmanagement.Repo.BookRepo;
import com.system.travelmanagement.Repo.DestinationRepo;
import com.system.travelmanagement.Repo.UserRepo;
import com.system.travelmanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class   BookingImpl implements BookService {
private final DestinationRepo destinationRepo;
private final UserRepo userRepo;
private final BookRepo bookRepo;



    @Override
    public String save(BookPojo bookPojo) throws IOException{
        Book book=new Book();
        book.setId(bookPojo.getId());
        book.setCheckin(bookPojo.getCheckin());
        book.setCheckout(bookPojo.getCheckout());
        book.setPeople(bookPojo.getPeople());
        book.setUserId(userRepo.findById(bookPojo.getUserId()).orElseThrow());
        book.setDestId(destinationRepo.findById(bookPojo.getDestId()).orElseThrow());
        bookRepo.save(book);
        return "Created";
    }

    @Override
    public List<Book> fetchAll() {
        return this.bookRepo.findAll();
    }

    @Override
    public void deletebyid(Integer id) {
        bookRepo.deleteById(id);
    }

    @Override
    public List findBookingById(Integer id) {
        return findAllInList(bookRepo.findBookingById(id));
    }

    public List<Book> findAllInList(List<Book> list) {
        Stream<Book> allBooking = list.stream().map(booking ->
                Book.builder()
                        .id(booking.getId())
                        .checkin(booking.getCheckin())
                        .checkout(booking.getCheckout())
                        .People(booking.getPeople())
                        .userId(booking.getUserId())
                        .destId(booking.getDestId())
                        .build());
        list = allBooking.toList();
        return list;
    }
    @Override
    public Book fetchById(Integer id) {
        Book book=bookRepo.findById(id).orElseThrow(()->
                new RuntimeException("notfound"));
        book=Book.builder().checkout(book.getCheckin())
                .id(book.getId())
                .checkin(book.getCheckin())
                .checkout(book.getCheckout())
                .People(book.getPeople())
                .userId(book.getUserId())
                .destId(book.getDestId())
                .build();
        return book;

    }
}
