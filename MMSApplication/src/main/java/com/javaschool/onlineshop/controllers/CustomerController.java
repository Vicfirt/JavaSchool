package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.model.dto.CustomerAddressDTO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.service.CartService;
import com.javaschool.onlineshop.service.CustomerAddressService;
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
import java.security.Principal;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerAddressService customerAddressService;

    public CustomerController(CustomerService customerService, CustomerAddressService customerAddressService) {
        this.customerService = customerService;
        this.customerAddressService = customerAddressService;
    }

    @GetMapping("/")
    public String handler(Authentication authentication) {
        if (authentication == null || authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))) {
            return "forward:/home";
        } else {
            return "forward:/product/employee/new";
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String createSignupForm(CustomerDTO customer, Model model) {
        model.addAttribute("customer", customer);
        return "/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("customer") CustomerDTO customer,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        CustomerDTO customerExists = customerService.getByUsername(customer.getCustomerEmailAddress());
        if (customerExists != null) {
            bindingResult.rejectValue("customerEmailAddress", "", "This email is already in use");
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

    @GetMapping("/profile")
    public String showProfile(Model model) {
        CustomerDTO customer = customerService.getCustomer();
        model.addAttribute("customer", customer);
        model.addAttribute("address", customer.getCustomerAddress());
        return "/profile_info";
    }

    @GetMapping("/profile/edit")
    public String showEditForm(Model model) {
        CustomerDTO customer = customerService.getCustomer();
        model.addAttribute("customer", customer);
        return "/edit_profile";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@Valid @ModelAttribute("customer") CustomerDTO customer, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("customer", customer);
            return "/edit_profile";
        }
        customerService.update(customer, principal);
        return "redirect:/";
    }

    @GetMapping("/profile/address/new")
    public String showAddAddressForm(Model model, CustomerAddressDTO customerAddress) {
        CustomerDTO customer = customerService.getCustomer();
        model.addAttribute("customer", customer);
        model.addAttribute("address", customerAddress);
        return "add_address";
    }

    @PostMapping("/profile/address/new")
    public String addAddress(@Valid @ModelAttribute("address") CustomerAddressDTO customerAddress,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("address", customerAddress);
            return "/add_address";
        }
        CustomerDTO customer = customerService.getCustomer();
        customerAddressService.addCustomerAddress(customerAddress, customer);
        return "redirect:/profile";
    }
}
