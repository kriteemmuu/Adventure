package com.system.travelmanagement.Controller;

import com.system.travelmanagement.Entity.Contact;
import com.system.travelmanagement.Entity.User;
import com.system.travelmanagement.Pojo.ContactPojo;
import com.system.travelmanagement.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;
    @GetMapping("/cont")
    public String adminPage(Model model){
        model.addAttribute("conts", new ContactPojo());
        return "contact";
    }
    @PostMapping("/savecont")
    public String bookDesti(@Valid ContactPojo contactPojo) {
        contactService.save(contactPojo);
        return "redirect:/contact/cont";
    }


}
