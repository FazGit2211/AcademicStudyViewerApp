package com.fzuleta.academicstudyviewerapp.models;

public class Career {
    private String name;
    private Integer duration;

    public Career(String name, Integer duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public Integer getDuration() {
        return duration;
    }
}
