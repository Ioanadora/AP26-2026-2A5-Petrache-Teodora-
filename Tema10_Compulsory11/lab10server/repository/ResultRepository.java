package com.compulsory10.lab10server.repository;

import com.compulsory10.lab10server.model.ResultEntity;

public class ResultRepository extends AbstractRepository<ResultEntity> {
    public ResultRepository() {
        super(ResultEntity.class);
    }
}
