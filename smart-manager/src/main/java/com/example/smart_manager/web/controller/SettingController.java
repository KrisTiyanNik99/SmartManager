package com.example.smart_manager.web.controller;

import com.example.smart_manager.chanel.service.ChannelBotService;
import com.example.smart_manager.web.dto.RegisterBotRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SettingController {
    private final ChannelBotService channelBotService;

    @Autowired
    public SettingController(ChannelBotService channelBotService) {
        this.channelBotService = channelBotService;
    }

    @GetMapping("/settings")
    public ModelAndView getSettings() {
        ModelAndView settingView = new ModelAndView();
        settingView.setViewName("settings.html");
        settingView.addObject("registerBotRequest", new RegisterBotRequest());

        return settingView;
    }

    @PostMapping("/channels/register")
    public ModelAndView registerBot(@Valid RegisterBotRequest registerBotRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView settingView = new ModelAndView();
            settingView.setViewName("settings.html");
            settingView.addObject("registerBotRequest", new RegisterBotRequest());

            return settingView;
        }

        channelBotService.registerBot(registerBotRequest);
        return new ModelAndView("settings.html");
    }
}
