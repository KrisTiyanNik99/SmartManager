package com.example.smart_manager.web.controller;

import com.example.smart_manager.user.service.UserService;
import com.example.smart_manager.web.dto.LoginRequest;
import com.example.smart_manager.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    // ###################################         Controller Endpoints         ###################################
    @GetMapping
    public String getStartPage() {
        return "index";
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView loginPage = new ModelAndView();
        loginPage.setViewName("login");
        loginPage.addObject("loginRequest", new LoginRequest());

        return loginPage;
    }

    /*@PostMapping
        public ModelAndView login(@Valid LoginRequest loginRequest, BindingResult result, HttpSession session) {
            if (result.hasErrors()) {
            return new ModelAndView("login");
            }

            User user = userService.login(loginRequest);
            session.setAttribute("user_id", user.getId());

            return new ModelAndView("redirect:/home");
        }
    */

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView registerPage = new ModelAndView();
        registerPage.setViewName("register");
        registerPage.addObject("registerRequest", new RegisterRequest());

        return registerPage;
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid RegisterRequest registerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }

        userService.register(registerRequest);

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }
}
