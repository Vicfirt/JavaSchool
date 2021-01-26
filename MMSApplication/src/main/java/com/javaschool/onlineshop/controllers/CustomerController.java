package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.exception.CustomExceptionHandler;
import com.javaschool.onlineshop.model.dto.CustomerAddressDTO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.service.CustomerAddressService;
import com.javaschool.onlineshop.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.security.Principal;

/**
 * This class is responsible for the actions with user account.
 */
@Controller
public class CustomerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    private final CustomerService customerService;

    private final CustomerAddressService customerAddressService;

    public CustomerController(CustomerService customerService, CustomerAddressService customerAddressService) {
        this.customerService = customerService;
        this.customerAddressService = customerAddressService;
    }

    /**
     * Method responsible for logging in.
     *
     * @return login view
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * This method provides the user with a registration form.
     *
     * @param customer                  user to be registered
     * @param model                     the model that will be sent to the view
     * @return signup view
     */
    @GetMapping("/signup")
    public String createSignupForm(CustomerDTO customer, Model model) {
        model.addAttribute("customer", customer);
        return "/signup";
    }

    /**
     * This method gets the data entered by the user and creates a new customer in the database
     * if the given username is free.
     *
     * @param customer                   customer received from the form with user-filled data
     * @param bindingResult              used for form validation
     * @param redirectAttributes         these attributes will be returned to the previous form in case of validation error
     * @return in case of successful registration the user will be redirected to the login page
     */
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("customer") CustomerDTO customer,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        CustomerDTO customerExists = customerService.getByUsername(customer.getCustomerEmailAddress());
        if (customerExists != null) {
            bindingResult.rejectValue("customerEmailAddress", "", "email "
                    + customerExists.getCustomerEmailAddress() + " is already in use");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("customer", customer);
            return "/signup";
        }
        customerService.addCustomer(customer);
        return "redirect:/login";
    }

    /**
     * Method responsible for redirecting to the error page in case of lack of permission.
     *
     * @param model                        that will be sent to the view
     * @return error page
     */
    @GetMapping("/denied")
    public String accessDeny(Model model) {
        LOGGER.info("Current user does not have permission for this action");
        model.addAttribute("message", "Access denied!");
        return "custom_error";
    }

    /**
     * Method responsible for redirecting to the error page in case of incorrectly entered data.
     *
     * @param model                         that will be sent to the view
     * @return error page
     */
    @GetMapping("/login/error")
    public String loginError(Model model) {
        LOGGER.info("User entered incorrect login information");
        model.addAttribute("message", "Wrong password or username");
        return "custom_error";
    }

    /**
     * The method is responsible for displaying the user information page
     *
     * @param model                         that will be sent to the view
     * @return customer profile information view
     */
    @GetMapping("/profile")
    public String showProfile(Model model) {
        CustomerDTO customer = customerService.getCustomer();
        model.addAttribute("customer", customer);
        model.addAttribute("address", customer.getCustomerAddress());
        return "/profile_info";
    }

    /**
     * This method provides the user with a profile edit form.
     *
     * @param model                         that will be sent to the view
     * @return edit profile view
     */
    @GetMapping("/profile/edition")
    public String showEditForm(Model model) {
        CustomerDTO customer = customerService.getCustomer();
        model.addAttribute("customer", customer);
        return "/edit_profile";
    }

    /**
     * This method is responsible for editing the profile. It gets a new customer from the model to save to the database.
     *
     * @param customer                  modified customer
     * @param bindingResult             used for form validation
     * @param redirectAttributes        these attributes will be returned to the previous form in case of validation error
     * @param principal                 used to get the username of the customer to update
     * @return homepage view
     */
    @PostMapping("/profile/edition")
    public String editProfile(@Valid @ModelAttribute("customer") CustomerDTO customer, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Principal principal) {
        CustomerDTO customerExists = customerService.getByUsername(customer.getCustomerEmailAddress());
        if (customerExists != null) {
            bindingResult.rejectValue("customerEmailAddress", "", "This email is already in use");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("customer", customer);
            return "/edit_profile";
        }
        customerService.updateCustomer(customer, principal);
        return "redirect:/";
    }

    /**
     * This method provides the customer with an address add form.
     *
     * @param model                      that will be sent to the view
     * @return add address form view
     */
    @GetMapping("/profile/address/new")
    public String showAddAddressForm(Model model, CustomerAddressDTO customerAddress) {
        CustomerDTO customer = customerService.getCustomer();
        model.addAttribute("customer", customer);
        model.addAttribute("address", customerAddress);
        return "add_address";
    }

    /**
     * This method is responsible for adding the address. It gets a new customer address from the model to save to the database.
     *
     * @param customerAddress           user entered address
     * @param bindingResult             used for form validation
     * @param redirectAttributes        these attributes will be returned to the previous form in case of validation error
     * @return profile info view
     */
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

    /**
     * This method provides the user with an address edit form.
     *
     * @param model         the model that will be sent to the view
     * @return edit address form view
     */
    @GetMapping("/profile/address")
    public String showEditAddressForm(Model model) {
        CustomerDTO customer = customerService.getCustomer();
        CustomerAddressDTO address = customer.getCustomerAddress();
        model.addAttribute("customer", customer);
        model.addAttribute("address", address);
        return "edit_address";
    }

    /**
     * This method is responsible for editing the address. It gets an edited address from the model to save to the database.
     *
     * @param customerAddress           user edited address
     * @param bindingResult             used for form validation
     * @param redirectAttributes        these attributes will be returned to the previous form in case of validation error
     * @return profile view
     */
    @PostMapping("/profile/address")
    public String editAddress(@Valid @ModelAttribute("address") CustomerAddressDTO customerAddress,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("address", customerAddress);
            return "/edit_address";
        }
        customerAddressService.updateCustomerAddress(customerAddress);
        return "redirect:/profile";
    }
}
