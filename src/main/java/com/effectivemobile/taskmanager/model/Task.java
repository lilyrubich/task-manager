package com.effectivemobile.taskmanager.model;

import com.effectivemobile.taskmanager.enums.TaskPriority;
import com.effectivemobile.taskmanager.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@RequiredArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(40)", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "VARCHAR(40)", nullable = false)
    private String description;

    @Column(name = "status", columnDefinition = "VARCHAR(40) default 'BACKLOG'", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name = "priority", columnDefinition = "VARCHAR(40)", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "reporter_id", nullable = false)
    private User userReporter;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "assignee_id")
    private User userAssignee;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> comments;
}
