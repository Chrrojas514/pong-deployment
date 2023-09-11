package com.chris.pongsql.controller;

import com.chris.pongsql.model.GameState;
import com.chris.pongsql.repository.GameStateRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GameStateController {
    @Autowired
    private GameStateRepository gameStateRepository;

    @RequestMapping(value = "/echo/{input}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String echo(@PathVariable String input) {
        return input;
    }

    @GetMapping(value = "/welcome")
    public String index() {
        return "HELLO";
    }

    @GetMapping(value = "/answer")
    public String getAnswer(){
        return "The answer is 42!";
    }

    /* --------------------------------------------------------------------------------------------- */

    //Should be void?
    @PostMapping("/createRoom")
    @ResponseStatus(HttpStatus.CREATED)
    public void createGameState(@RequestBody GameState gameState) {
        gameStateRepository.save(gameState);
    }

    @GetMapping("/gameState/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameState getGameState(@PathVariable int id) {
        Optional<GameState> target = gameStateRepository.findById(id);

        return target.isPresent() ? target.get() : null;
    }

    @GetMapping("/gameStates")
    @ResponseStatus(HttpStatus.OK)
    public List<GameState> getGameStates() {
        List<GameState> allGames = gameStateRepository.findAll();

        return allGames;
    }

    @PutMapping("/paddlePositionUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void updatePaddlePositions(@RequestBody ObjectNode gameUpdateInfo) {
        Integer gameStateId = gameUpdateInfo.get("id").asInt();
        String playerName = gameUpdateInfo.get("playerName").asText();
        Integer paddlePos = gameUpdateInfo.get("paddlePos").asInt();

        Optional<GameState> target = gameStateRepository.findById(gameStateId);
        if (target.isEmpty()) {
            System.out.println("ERROR DOES NOT EXIST");
            return;
        }

        GameState currentGame = target.get();

        if (currentGame.getPlayerA().equals(playerName)) {
            currentGame.setPlayerAPaddlePos(paddlePos);
        }

        else if (currentGame.getPlayerB().equals(playerName)) {
            currentGame.setPlayerBPaddlePos(paddlePos);
        }


        gameStateRepository.save(currentGame);
    }

    //just for testing
    @DeleteMapping("/clearDB")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearDB() {
        gameStateRepository.deleteAll();
    }

    @PostMapping("/joinRoom")
    @ResponseStatus(HttpStatus.OK)
    public GameState joinRoom(@RequestBody ObjectNode roomInfo) throws Exception {
        Integer id = roomInfo.get("id").asInt();
        String playerName = roomInfo.get("playerName").asText();

        Optional<GameState> target = gameStateRepository.findById(id);

        if (!target.isPresent()) {
            return null;
        }

        GameState currentGame = target.get();

        if (currentGame.getPlayerA().isEmpty()) {
            currentGame.setPlayerA(playerName);
            gameStateRepository.save(currentGame);
            return currentGame;
        }


        if (currentGame.getPlayerB().isEmpty()) {
            currentGame.setPlayerB(playerName);
            gameStateRepository.save(currentGame);
            return currentGame;
        }

        // TODO:  RESPOND WITH ERROR WHEN BOTH PLAYER NAMES ARE FILLED OUT
        return null;
    }

    @PostMapping("/leaveRoom")
    @ResponseStatus(HttpStatus.OK)
    public GameState leaveRoom(@RequestBody ObjectNode roomInfo) {
        Integer id = roomInfo.get("id").asInt();
        String playerName = roomInfo.get("playerName").asText();
        Optional<GameState> target = gameStateRepository.findById(id);

        if (!target.isPresent()) {
            return null;
        }

        GameState currentGame = target.get();

        if (!currentGame.getPlayerA().isEmpty() && currentGame.getPlayerA().equals(playerName)) {
            currentGame.setPlayerA("");

            gameStateRepository.save(currentGame);
            return currentGame;
        }

        if (!currentGame.getPlayerB().isEmpty() && currentGame.getPlayerB().equals(playerName)) {
            currentGame.setPlayerB("");

            gameStateRepository.save(currentGame);
            return currentGame;
        }


        //TODO: SIMILAR TO LINE 102 when no names match
        return null;
    }

    @PostMapping("/startGame/{gameStateId}")
    @ResponseStatus(HttpStatus.CREATED)
    public GameState startGame(@PathVariable int gameStateId) {
        Optional<GameState> target = gameStateRepository.findById(gameStateId);

        if (!target.isPresent()) {
            return null;
        }

        GameState currentGame = target.get();

        currentGame.setGameStarted(true);

        gameStateRepository.save(currentGame);
        return currentGame;
    }

    //TODO: endGame endpoint
    @PostMapping("/endGame/{gameStateId}")
    @ResponseStatus(HttpStatus.CREATED)
    public GameState endGame(@PathVariable int gameStateId) {
        Optional<GameState> target = gameStateRepository.findById(gameStateId);

        if (!target.isPresent()) {
            return null;
        }

        GameState currentGame = target.get();

        currentGame.setGameOver(true);

        gameStateRepository.save(currentGame);
        return currentGame;
    }
}