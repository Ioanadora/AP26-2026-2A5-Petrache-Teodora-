package com.compulsory10.lab10server.repository;

import com.compulsory10.lab10server.model.QuestionEntity;

public class QuestionRepository extends AbstractRepository<QuestionEntity> {
    public QuestionRepository() {
        super(QuestionEntity.class);
    }
}
