package com.system.travelmanagement.Controller;

import com.system.travelmanagement.Entity.Destination;
import com.system.travelmanagement.Pojo.DestinationPojo;
import com.system.travelmanagement.service.Adddestination;
import com.system.travelmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dest")

public class DestinationController {
    private final Adddestination adddestination;

    @PostMapping("/saveDest")
    public String saveDest(DestinationPojo destinationPojo, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/add";
        }
        adddestination.saveDestination(destinationPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/admin/add";
    }
    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }

    @GetMapping("/dest")
    public String GetDesti(Model model){
        List<Destination> rooms = adddestination.fetchAll();
        model.addAttribute("desti", rooms);
        return  "destination";
    }






}
