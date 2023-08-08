package com.system.travelmanagement.Controller;

import com.system.travelmanagement.Entity.Book;
import com.system.travelmanagement.Entity.Contact;
import com.system.travelmanagement.Entity.Destination;
import com.system.travelmanagement.Entity.User;
import com.system.travelmanagement.Pojo.BookPojo;
import com.system.travelmanagement.Pojo.DestinationPojo;
import com.system.travelmanagement.service.Adddestination;
import com.system.travelmanagement.service.BookService;
import com.system.travelmanagement.service.ContactService;
import com.system.travelmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final Adddestination adddestination;
    private final UserService userService;
    private final BookService bookService;
    private final ContactService contactService;

    @GetMapping("/adminp")
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/userss")
    public String GetRevs(Model model) {
        List<User> users = userService.fetchAll();
        model.addAttribute("userlist", users);
        return "users";
    }
    @GetMapping("/delete/{id}")
    public String DelUser(@PathVariable("id")Integer id){
        userService.deletebyid(id);

        return "redirect:/user/userss";
    }

    @GetMapping("/add")
    public String addPage(Model model){
        model.addAttribute("desti",new DestinationPojo());
        return "add";
    }

    @GetMapping("/destlist")
    public String GetDestiList(Model model){
        List<Destination> destinations = adddestination.fetchAll();
        model.addAttribute("destis", destinations);
        return  "destinationlist";
    }

    @GetMapping("/deletes/{id}")
    public String DelDest(@PathVariable("id")Integer id){
        adddestination.deletebyid(id);
        return "redirect:/admin/destlist";
    }

    @GetMapping("/edit/{id}")
    public String editDest(@PathVariable("id") Integer id, Model model){
        Destination destination = adddestination.fetchById(id);
        model.addAttribute("clickedddest", new DestinationPojo(destination));
        return "redirect:/dest/destlist";
    }
    @GetMapping("/productss/{id}")
    public String getDestProgile(@PathVariable("id") Integer id, Model model ){
        Destination destination = adddestination.fetchById(id);
        model.addAttribute("destinations", new DestinationPojo(destination));
        model.addAttribute("clickeddest", destination);
        return "edit";
    }

    @GetMapping("/boook")
    public String GetBook(Model model) {
        List<Book> book = bookService.fetchAll();
        model.addAttribute("booklist", book.stream().map(books->
                Book.builder()
                        .id(books.getId())
                        .checkin(books.getCheckin())
                        .checkout(books.getCheckout())
                        .People(books.getPeople())
                        .userId(books.getUserId())
                        .destId(books.getDestId())
                        .build()
        ));
        return "bookinglist";
    }





    @GetMapping("/conlis")
    public String GetRevss(Model model) {
        List<Contact> contact = contactService.fetchAll();
        model.addAttribute("contactlist", contact);
        return "contactlist";
    }
}
