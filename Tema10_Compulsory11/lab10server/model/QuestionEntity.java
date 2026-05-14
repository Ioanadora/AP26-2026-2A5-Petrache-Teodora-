package com.compulsory10.lab10server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private String answer;
    public QuestionEntity() {}
    public QuestionEntity(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}
