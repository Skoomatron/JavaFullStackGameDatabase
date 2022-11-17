package com.practice.javapractice2.controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
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

    @GetMapping(value="/updateEntry/{title}")
    public String updateEntry(@PathVariable("title") String title) {
        return "views/updateGame";
    }

    @RequestMapping(value="/submitUpdate/{title}")
    public String submitUpdate(@PathVariable("title") String title) {
        MongoClient mongo = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase db = mongo.getDatabase("game");

        BasicDBObject query = new BasicDBObject();
        query.put("title", title);
        BasicDBObject newDoc = new BasicDBObject();
        newDoc.put("title", "test");
        BasicDBObject updated = new BasicDBObject();
        updated.put("$set", newDoc);

        db.getCollection("game").updateOne(query, updated);
        return "redirect:/listGames";
    }

}
