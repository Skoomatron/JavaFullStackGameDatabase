package com.practice.javapractice2.controllers;

import com.practice.javapractice2.models.Game;
import com.practice.javapractice2.repositories.GameRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final GameRepository repository;
    private ObjectId id;
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
        System.out.println(game);
        repository.save(new Game(game.getId(), game.getTitle(), game.getGenre()));
        return "views/postSuccess";
    }

    @GetMapping("/listGames")
    public String listGames(Model model) {
        model.addAttribute("games", repository.findAll());
        return "views/listGames";
    }

    @RequestMapping(value="/deleteEntry/{id}")
    public String deleteEntry(@PathVariable("id") String id, Model model) {
        repository.deleteById(id);
        return "redirect:/listGames";
    }

    @GetMapping(value="/updateEntry/{id}")
    public String updateEntry(@PathVariable("id") String id, @ModelAttribute("game") Game game) {
        this.id = game.getId();
        return "views/updateGame";
    }

    @PostMapping(value="/submitUpdate")
    public String submitUpdate(@ModelAttribute("game") Game game) {
        repository.save(new Game(this.id, game.getTitle(), game.getGenre()));
        return "redirect:/listGames";
    }

}
