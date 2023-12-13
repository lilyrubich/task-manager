package com.effectivemobile.taskmanager.enums;

public enum TaskStatus {
    BACKLOG, IN_PROGRESS, CLOSED;

    public String toString(TaskStatus taskStatus) {
        return taskStatus.name();
    }
}
