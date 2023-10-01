package com.chris.pongsql.service;

import com.chris.pongsql.model.GameState;
import com.chris.pongsql.repository.GameStateRepository;
import com.chris.pongsql.viewmodel.GameStateViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLayer {

    @Autowired
    private GameStateRepository gameStateRepository;

    @Autowired
    public ServiceLayer(GameStateRepository gameStateRepository) { this.gameStateRepository = gameStateRepository }

    // helper function
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
}
