package be.ucll.cityquest.games.controllers;

import be.ucll.cityquest.games.GameRepository;
import be.ucll.cityquest.games.model.Game;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class GameController {

    private GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public Game createGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
    public Game updateGame(@PathVariable UUID id, @RequestBody Game game) {
        if(! game.getId().equals(id)) throw new IllegalArgumentException("Wellwell, what are you trying to do?");

        return gameRepository.save(game);
    }

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public List<Game> getAllGames() {
        return StreamSupport.stream(gameRepository.findAll().spliterator(), false)
                .map(game -> {game.setQuestions(new ArrayList<>()); return game;})
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    public Game getGameById(@PathVariable UUID id) {
        return gameRepository.findById(id).get();
    }
}