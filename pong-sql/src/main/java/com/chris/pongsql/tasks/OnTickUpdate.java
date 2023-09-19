package com.chris.pongsql.tasks;

import com.chris.pongsql.repository.GameStateRepository;
import com.chris.pongsql.service.ServiceLayer;
import com.chris.pongsql.viewmodel.GameStateViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class OnTickUpdate {
    public GameStateViewModel currentGame;

    @Autowired
    ServiceLayer serviceLayer;

    public void setGameState( GameStateViewModel gameStateViewModel ) {
        currentGame = gameStateViewModel;
    }

    @Scheduled(fixedRate = 1000)
    public void onTickUpdate() {
        currentGame.setBallPositionX(currentGame.getBallPositionX() + currentGame.getBallVelocityX());
        currentGame.setBallPositionY(currentGame.getBallPositionY() + currentGame.getBallVelocityY());

        serviceLayer.updateGame(currentGame);
    }
}
