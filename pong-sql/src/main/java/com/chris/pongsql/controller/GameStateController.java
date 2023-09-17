package com.chris.pongsql.controller;

import com.chris.pongsql.model.GameState;
import com.chris.pongsql.service.ServiceLayer;
import com.chris.pongsql.viewmodel.GameStateViewModel;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class GameStateController {
    @Autowired
    ServiceLayer serviceLayer;

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
    public GameStateViewModel createGameState(@RequestBody @Valid GameStateViewModel gameState) {
        return serviceLayer.saveGame(gameState);
    }

    @GetMapping("/gameState/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameStateViewModel getGameState(@PathVariable @Valid int id) {
        return serviceLayer.findGame(id);
    }

    @GetMapping("/gameStates")
    @ResponseStatus(HttpStatus.OK)
    public List<GameStateViewModel> getGameStates() {
        return serviceLayer.findAllGames();
    }

    @PutMapping("/paddlePositionUpdate")
    @ResponseStatus(HttpStatus.OK)
    public void updatePaddlePositions(@RequestBody @Valid ObjectNode gameUpdateInfo) {
        serviceLayer.updatePaddlePositions(gameUpdateInfo);
    }

    //just for testing
    @DeleteMapping("/clearDB")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearDB() {
        serviceLayer.removeAllGames();
    }

    @PostMapping("/joinRoom")
    @ResponseStatus(HttpStatus.OK)
    public GameStateViewModel joinRoom(@RequestBody @Valid ObjectNode roomInfo) throws Exception {
        return serviceLayer.joinRoom(roomInfo);
    }

    @PostMapping("/leaveRoom")
    @ResponseStatus(HttpStatus.OK)
    public GameStateViewModel leaveRoom(@RequestBody @Valid ObjectNode roomInfo) {
        return serviceLayer.leaveRoom(roomInfo);
    }

    @PostMapping("/startGame/{gameStateId}")
    @ResponseStatus(HttpStatus.CREATED)
    public GameStateViewModel startGame(@PathVariable @Valid int gameStateId) {
        return serviceLayer.startGame(gameStateId);
    }

    @PostMapping("/endGame/{gameStateId}")
    @ResponseStatus(HttpStatus.CREATED)
    public GameStateViewModel endGame(@PathVariable @Valid int gameStateId) {
        return serviceLayer.endGame(gameStateId);
    }
}