package com.example.task.entity;

public enum Degree {

    ASSISTANT("ASSISTANT"),
    ASSOCIATE_PROFESSOR("ASSOCIATE PROFESSOR"),
    PROFESSOR("PROFESSOR");

    private final String name;

    Degree(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
