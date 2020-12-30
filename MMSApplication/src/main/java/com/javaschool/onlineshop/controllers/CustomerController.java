package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.service.CartService;
import com.javaschool.onlineshop.service.CustomerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CustomerController {

    CartService cartService;

    CustomerService customerService;

    public CustomerController(CartService cartService, CustomerService customerService) {
        this.cartService = cartService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String handler(Authentication authentication) {
        if (authentication == null || authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))) {
            return "forward:/home";
        } else {
            return "forward:/employee";
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String createSignupForm(Model model, CustomerDTO customer) {

        model.addAttribute("customer", customer);
        return "/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("customer") CustomerDTO customer,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        CustomerDTO customerExists = customerService.getByEmail(customer.getCustomerEmailAddress());
        if (customerExists != null) {
            bindingResult.rejectValue("email", "This email is already in use");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("customer", customer);
            return "signup";
        }
        customerService.addCustomer(customer);
        return "redirect:/login";
    }

    @GetMapping("/access_denied")
    public String accessDeny(Model model) {
        model.addAttribute("message", "Access denied!");
        return "/myerror";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("messge", "Not found");
        return "/myerror";
    }
}
