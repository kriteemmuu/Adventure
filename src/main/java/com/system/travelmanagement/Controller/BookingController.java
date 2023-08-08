package com.system.travelmanagement.Controller;
import com.system.travelmanagement.Entity.Book;
import com.system.travelmanagement.Entity.Destination;
import com.system.travelmanagement.Pojo.BookPojo;
import com.system.travelmanagement.Pojo.DestinationPojo;
import com.system.travelmanagement.service.Adddestination;
import com.system.travelmanagement.service.BookService;
import com.system.travelmanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
@Builder
@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookingController {
    private final Adddestination adddestination;
    private final UserService userService;

    private final BookService bookService;
    @GetMapping("/books/{id}")
    public String GetBook(@PathVariable Integer id, Model model, Principal principal){
        Destination desttt=adddestination.fetchById(id);
        model.addAttribute("dest",new DestinationPojo(desttt));
        model.addAttribute("bookingss" ,new BookPojo());
        model.addAttribute("userlog",userService.findByEmail(principal.getName()));
        return "form";
    }
    @PostMapping("/savebook")
    public String bookDesti(@Valid BookPojo bookingPojo) throws IOException {
       bookService.save(bookingPojo);
        return "redirect:/book/pay";
    }
    @GetMapping("/booked/{id}")
    public String fetchAllbook(@PathVariable("id") Integer id, Model model , Principal principal){
        List<Book> booking= bookService.findBookingById(id);
        model.addAttribute("books",booking);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));

        return "MyBookings";
    }
    @GetMapping("/editbook/{id}")
    public String editBooking(@PathVariable("id") Integer id, Model model){
        Book book = bookService.fetchById(id);
        model.addAttribute("clickeddbook", new BookPojo(book));
        return "redirect:/dest/dest";
    }
    @GetMapping("/products/{id}")
    public String getmybook(@PathVariable("id") Integer id, Model model ){
        Book book = bookService.fetchById(id);
        model.addAttribute("booking", new BookPojo(book));
        model.addAttribute("clickedbook", book);
        return "editbooking";
    }
    @GetMapping("/pay")
    public String payment(){
        return "payemnt";
    }
}
