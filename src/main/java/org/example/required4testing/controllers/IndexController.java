package org.example.required4testing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index.xhtml";
    }
}
