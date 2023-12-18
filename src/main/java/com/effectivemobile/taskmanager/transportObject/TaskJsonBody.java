package com.effectivemobile.taskmanager.transportObject;

import com.effectivemobile.taskmanager.enums.TaskPriority;
import com.effectivemobile.taskmanager.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TaskJsonBody {

    private Long id;

    @NotBlank(message = "Title must be specified")
    @Size(max = 40, message = "Title should not be greater than 40")
    private String title;

    @NotBlank(message = "Description must be specified")
    @Size(max = 100, message = "Description should not be greater than 100")
    private String description;

    @NotNull(message = "Status must be specified")
    private TaskStatus status;

    @NotNull(message = "Priority must be specified")
    private TaskPriority priority;
    private Long reporter;
    private Long assignee;

    public TaskJsonBody(Builder builder) {
        this.id = builder.getId();
        this.title = builder.getTitle();
        this.description = builder.getDescription();
        this.status = builder.getStatus();
        this.priority = builder.getPriority();
        this.reporter = builder.getReporter();
        this.assignee = builder.getAssignee();
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    @Getter
    public static class Builder {
        private Long id;
        private String title;
        private String description;
        private TaskStatus status;
        private TaskPriority priority;
        private Long reporter;
        private Long assignee;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setStatus(TaskStatus status) {
            this.status = status;
            return this;
        }

        public Builder setPriority(TaskPriority priority) {
            this.priority = priority;
            return this;
        }

        public Builder setReporter(Long reporter) {
            this.reporter = reporter;
            return this;
        }

        public Builder setAssignee(Long assignee) {
            this.assignee = assignee;
            return this;
        }

        public TaskJsonBody build() {
            return new TaskJsonBody(this);
        }
    }
}
