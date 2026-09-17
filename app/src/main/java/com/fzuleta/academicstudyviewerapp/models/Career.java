package com.fzuleta.academicstudyviewerapp.models;

import java.util.ArrayList;

public class Career {
    private String name;
    private Integer duration;
    private ArrayList<Asignature> asignatures;

    public Career(String name, Integer duration) {
        this.name = name;
        this.duration = duration;
        this.asignatures = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Integer getDuration() {
        return duration;
    }

    public ArrayList<Asignature> getAsignatures() {
        return asignatures;
    }

    public void setAsignatures(ArrayList<Asignature> asignatures) {
        this.asignatures = asignatures;
    }
}
