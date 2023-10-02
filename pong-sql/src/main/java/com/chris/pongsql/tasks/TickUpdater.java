package com.chris.pongsql.tasks;

import com.chris.pongsql.service.ServiceLayer;
import com.chris.pongsql.viewmodel.GameStateViewModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class TickUpdater implements CommandLineRunner {
    //@Autowired
    private ServiceLayer serviceLayer;

    public TickUpdater(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @Override
    public void run(String... args) throws Exception {
        CompletableFuture<GameStateViewModel> updateGame = serviceLayer.onTickUpdate("TESTING AGAIN");
    }
}
