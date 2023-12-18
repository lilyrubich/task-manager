package com.effectivemobile.taskmanager.transportObject;

import com.effectivemobile.taskmanager.model.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentJsonBody {

    private Long id;

    @NotBlank(message = "Title must be specified")
    @Size(max = 100, message = "Content should not be greater than 100")
    private String content;

    private Timestamp creationTime;

    @NotNull(message = "Task cannot be null")
    private Long task;

    public CommentJsonBody(Builder builder) {
        this.id = builder.getId();
        this.content = builder.getContent();
        this.creationTime = builder.getCreationTime();
        this.task = builder.getTask();
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    @Getter
    public static class Builder {

        private Long id;
        private String content;
        private Timestamp creationTime;
        private Long task;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setCreationTime(Timestamp creationTime) {
            this.creationTime = creationTime;
            return this;
        }

        public Builder setTask(Long task) {
            this.task = task;
            return this;
        }

        public CommentJsonBody build() {
            return new CommentJsonBody(this);
        }
    }
}
