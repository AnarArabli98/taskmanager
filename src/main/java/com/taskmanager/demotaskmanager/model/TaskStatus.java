package com.taskmanager.demotaskmanager.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {

    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED");

    private String value;

    TaskStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;

    }

    @JsonCreator
    public static TaskStatus forValue(String value) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;

            }


        }

        throw new IllegalArgumentException("Unknown TaskStatus value: " + value);
    }
}

