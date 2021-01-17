package com.daniel.rooms.model;

public enum EDaysOfWeek {
    Segunda("Segunda"), Terça("Terça"), Quarta("Quarta"), Quinta("Quinta"), Sexta("Sexta"), Sábado("Sábado"), Domingo("Domingo");

    private final String dayOfWeekName;

    EDaysOfWeek(String dayOfWeekName) {
        this.dayOfWeekName = dayOfWeekName;
    }

    public String getDayOfWeekName() {
        return dayOfWeekName;
    }
}
