package com.practice.javapractice2.controllers;

import com.practice.javapractice2.models.Game;
import com.practice.javapractice2.repositories.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final GameRepository repository;

    public MainController(GameRepository gameRepository) {
        this.repository = gameRepository;
    }

    @GetMapping("/main")
    public String mainController(Model model) {
        model.addAttribute("game", new Game());
        return "views/main";
    }


    @PostMapping("/gameSubmission")
    public String gameSubmit(@ModelAttribute("game") Game game) {
        this.repository.save(new Game(game.getTitle(), game.getGenre()));
        return "views/postSuccess";
    }

    @GetMapping("/listGames")
    public String listGames(Model model) {
        model.addAttribute("games", repository.findAll());
        return "views/listGames";
    }

    @RequestMapping(value="/deleteEntry", method= RequestMethod.DELETE)
    public String deleteEntry(@ModelAttribute("game") Game game) {

        return "redirect:/views/listGames";
    }
}
