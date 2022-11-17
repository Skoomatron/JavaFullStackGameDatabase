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

    @RequestMapping(value="/deleteEntry/{title}")
    public String deleteEntry(@PathVariable("title") String title, Model model) {
        repository.deleteGameByTitle(title);
        return "redirect:/listGames";
    }

    @PostMapping(value="/updateGame")
    public String updateEntry() {
        return "views/updateGame";
    }

    @RequestMapping(value="/updateEntry/{title}")
    public String submitUpdate(@PathVariable("title") String title, Model model) {

    }

}
