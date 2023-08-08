package com.system.travelmanagement.Pojo;

import com.system.travelmanagement.Entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookPojo {
    private  int id;
    private  String checkin;
    private  String checkout;
    private  String people;

    private  int destId;
    private  int userId;

    public BookPojo(Book book){
        this.id= book.getId();
        this.checkout= book.getCheckout();
        this.checkin= book.getCheckin();
        this.people= book.getPeople();
        this.destId= book.getDestId().getId();
        this.userId= book.getUserId().getId();

    }
}
