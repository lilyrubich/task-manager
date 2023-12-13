package com.effectivemobile.taskmanager.enums;

public enum TaskPriority {
    LOW, MIDDLE, HIGH;

    public String toString(TaskPriority taskPriority) {
        return taskPriority.name();
    }
}
