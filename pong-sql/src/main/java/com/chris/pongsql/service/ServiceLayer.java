package com.chris.pongsql.service;

import com.chris.pongsql.model.GameState;
import com.chris.pongsql.repository.GameStateRepository;
import com.chris.pongsql.viewmodel.GameStateViewModel;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
public class ServiceLayer {

    @Autowired
    private GameStateRepository gameStateRepository;

    @Autowired
    public ServiceLayer(GameStateRepository gameStateRepository) { this.gameStateRepository = gameStateRepository; }


    //=================================================================================================================

    public GameStateViewModel buildViewModel(GameState gameState) {
        GameStateViewModel viewModel = new GameStateViewModel();

        viewModel.setGameStateId(gameState.getGameStateId());
        viewModel.setRoomName(gameState.getRoomName());

        viewModel.setPlayerA(gameState.getPlayerA());
        viewModel.setPlayerB(gameState.getPlayerB());

        viewModel.setPlayerAPaddlePos(gameState.getPlayerAPaddlePos());
        viewModel.setPlayerBPaddlePos(gameState.getPlayerBPaddlePos());

        viewModel.setPlayerAScore(gameState.getPlayerAScore());
        viewModel.setPlayerBScore(gameState.getPlayerBScore());

        viewModel.setBallPositionX(gameState.getBallPositionX());
        viewModel.setBallPositionY(gameState.getBallPositionY());

        viewModel.setBallVelocityX(gameState.getBallVelocityX());
        viewModel.setBallVelocityY(gameState.getBallVelocityY());

        viewModel.setGameStarted(gameState.isGameStarted());
        viewModel.setGameOver(gameState.isGameOver());

        return viewModel;
    }


    //=================================================================================================================
    @Transactional
    public GameStateViewModel saveGame(GameStateViewModel viewModel) {
        if (viewModel.equals(null)) {
            throw new IllegalArgumentException("Viewmodel received was null");
        }

        GameState gameState = new GameState();

        gameState.setGameStateId(viewModel.getGameStateId());
        gameState.setRoomName(viewModel.getRoomName());

        gameState.setPlayerA(viewModel.getPlayerA());
        gameState.setPlayerB(viewModel.getPlayerB());

        gameState.setPlayerAPaddlePos(viewModel.getPlayerAPaddlePos());
        gameState.setPlayerBPaddlePos(viewModel.getPlayerBPaddlePos());

        gameState.setPlayerAScore(viewModel.getPlayerAScore());
        gameState.setPlayerBScore(viewModel.getPlayerBScore());

        gameState.setBallPositionX(viewModel.getBallPositionX());
        gameState.setBallPositionY(viewModel.getBallPositionY());

        gameState.setBallVelocityX(viewModel.getBallVelocityX());
        gameState.setBallVelocityY(viewModel.getBallVelocityY());

        gameState.setGameStarted(viewModel.isGameStarted());
        gameState.setGameOver(viewModel.isGameOver());
        gameState = gameStateRepository.save(gameState);

        viewModel.setGameStateId(gameState.getGameStateId());

        return viewModel;
    }

    @Transactional
    public GameStateViewModel updateGame(GameStateViewModel viewModel) {
        if(viewModel.equals(null)) {
            throw new IllegalArgumentException("Viewmodel received was null");
        }

        GameState gameState = new GameState();

        gameState.setGameStateId(viewModel.getGameStateId());
        gameState.setRoomName(viewModel.getRoomName());

        gameState.setPlayerA(viewModel.getPlayerA());
        gameState.setPlayerB(viewModel.getPlayerB());

        gameState.setPlayerAPaddlePos(viewModel.getPlayerAPaddlePos());
        gameState.setPlayerBPaddlePos(viewModel.getPlayerBPaddlePos());

        gameState.setPlayerAScore(viewModel.getPlayerAScore());
        gameState.setPlayerBScore(viewModel.getPlayerBScore());

        gameState.setBallPositionX(viewModel.getBallPositionX());
        gameState.setBallPositionY(viewModel.getBallPositionY());

        gameState.setBallVelocityX(viewModel.getBallVelocityX());
        gameState.setBallVelocityY(viewModel.getBallVelocityY());

        gameState.setGameStarted(viewModel.isGameStarted());
        gameState.setGameOver(viewModel.isGameOver());

        try {
            gameState = gameStateRepository.save(gameState);

            return buildViewModel(gameState);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Transactional
    public void removeGame(int id) { gameStateRepository.deleteById(id); }

    @Transactional
    public void removeAllGames() { gameStateRepository.deleteAll(); }

    public GameStateViewModel findGame(int id) {
        Optional<GameState> target = gameStateRepository.findById(id);

        if (target.isEmpty()) {
            throw new IllegalArgumentException("Game room with that ID not found!");
        }

        return buildViewModel(target.get());
    }

    public List<GameStateViewModel> findAllGames() {
        List<GameState> gameStateList = gameStateRepository.findAll();
        List<GameStateViewModel> viewModelList = new ArrayList<>();

        for (GameState gameState : gameStateList) {
            GameStateViewModel insert = buildViewModel(gameState);
            viewModelList.add(insert);
        }

        return viewModelList;
    }

    public void updatePaddlePositions(ObjectNode gameUpdateInfo) {
        Integer gameStateId = gameUpdateInfo.get("id").asInt();
        String playerName = gameUpdateInfo.get("playerName").asText();
        Integer paddlePos = gameUpdateInfo.get("paddlePos").asInt();

        Optional<GameState> target = gameStateRepository.findById(gameStateId);
        if (target.isEmpty()) {
            System.out.println("ERROR - GAME ROOM DOES NOT EXIST");
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

    public GameStateViewModel joinRoom(ObjectNode roomInfo) {
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
            return buildViewModel(currentGame);
        }


        if (currentGame.getPlayerB().isEmpty()) {
            currentGame.setPlayerB(playerName);
            gameStateRepository.save(currentGame);
            return buildViewModel(currentGame);
        }

        // TODO:  RESPOND WITH ERROR WHEN BOTH PLAYER NAMES ARE FILLED OUT
        throw new IllegalArgumentException("Room is full!");
    }

    public GameStateViewModel leaveRoom(ObjectNode roomInfo) {
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
            return buildViewModel(currentGame);
        }

        if (!currentGame.getPlayerB().isEmpty() && currentGame.getPlayerB().equals(playerName)) {
            currentGame.setPlayerB("");

            gameStateRepository.save(currentGame);
            return buildViewModel(currentGame);
        }


        //TODO: SIMILAR TO LINE 102 when no names match
        throw new IllegalArgumentException("Room is empty!");
    }

    public GameStateViewModel startGame(int gameStateId) throws InterruptedException {
        Optional<GameState> target = gameStateRepository.findById(gameStateId);

        if (!target.isPresent()) {
            return null;
        }

        GameState currentGame = target.get();

        currentGame.setGameStarted(true);

        CompletableFuture<GameStateViewModel> jankDebug = onTickUpdate(currentGame.getRoomName());

        gameStateRepository.save(currentGame);

        return buildViewModel(currentGame);
    }

    public GameStateViewModel endGame(int gameStateId) {
        Optional<GameState> target = gameStateRepository.findById(gameStateId);

        if (!target.isPresent()) {
            return null;
        }

        GameState currentGame = target.get();

        currentGame.setGameOver(true);

        gameStateRepository.save(currentGame);
        return buildViewModel(currentGame);
    }

    @Async
    public CompletableFuture<GameStateViewModel> onTickUpdate(String roomName ) throws InterruptedException {
        GameState target = gameStateRepository.findByroomName(roomName);

        /*
        Need a way to detect collision with paddle, how?
            Board object / variable that contains the boundaries of the game board in pixels NxN.
              Would mean paddle positions, ball positions and ball velocities values are changed to pixel values.
              HARDCODED? [as in resizing the window or diff monitor sizes may screw things up, not sure on this]

              TO BE INCLUDED IN EVERY TICK UPDATE:
              if ball position at Y == top boundary or bottom boundary of board dimension {
                Invert y velocity
              }

              if ball position at X AND Y == Paddle positions {
                Invert x velocity
              }

              if ball position at X == 0 or other x-axis board boundary {
                Depending on whether ball_positionX is at 0 or other boundary, increase score of
                either playerA or playerB and reset ball to the middle position.
              }
         */

        while (!target.isGameOver()) {
            target.setBallPositionX(target.getBallPositionX() + target.getBallVelocityX());
            target.setBallPositionY(target.getBallPositionY() + target.getBallVelocityY());

            gameStateRepository.save(target);
            Thread.sleep(1000);
        }

        return CompletableFuture.completedFuture(buildViewModel(target));
    }
}
