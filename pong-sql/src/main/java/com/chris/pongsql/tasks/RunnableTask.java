package com.chris.pongsql.tasks;

import com.chris.pongsql.repository.GameStateRepository;
import com.chris.pongsql.service.ServiceLayer;
import com.chris.pongsql.viewmodel.GameStateViewModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class RunnableTask implements Runnable {
    private String message;

    private GameStateViewModel gameStateViewModel = new GameStateViewModel();

    @Autowired
    private GameStateRepository gameStateRepository;

    @Autowired
    public ServiceLayer serviceLayer = new ServiceLayer(this.gameStateRepository);

    public RunnableTask(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        //serviceLayer.updateGame(gameStateViewModel);

        System.out.println(new Date() + "Runnable Task with " + message
        + "on thread " + Thread.currentThread().getName());
    }

    public void setGameStateRepository(GameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    public void setGameStateViewModel(GameStateViewModel gameStateViewModel) {
        this.gameStateViewModel = gameStateViewModel;
    }
}
