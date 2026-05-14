package com.compulsory10.lab10server.repository;

import com.compulsory10.lab10server.model.GameEntity;

public class GameRepository extends AbstractRepository<GameEntity> {
    public GameRepository() {
        super(GameEntity.class);
    }
}
