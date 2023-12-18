package com.effectivemobile.taskmanager.service;

import com.effectivemobile.taskmanager.model.Comment;
import com.effectivemobile.taskmanager.model.Task;
import com.effectivemobile.taskmanager.model.User;
import com.effectivemobile.taskmanager.repository.CommentRepository;
import com.effectivemobile.taskmanager.repository.TaskRepository;
import com.effectivemobile.taskmanager.repository.UserRepository;
import com.effectivemobile.taskmanager.transportObject.CommentJsonBody;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public CommentJsonBody createComment(CommentJsonBody commentJsonBody) throws EntityNotFoundException {

        Comment comment = new Comment();

        //find reporter user
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User reporter = userRepository.findByUsername(username).get();
        comment.setUser(reporter);

        //set creation time
        comment.setCreationTime(new Timestamp(System.currentTimeMillis()));

        //find task
        Optional<Task> task = taskRepository.findById(commentJsonBody.getTask());
        if (task.isEmpty()) {
            throw new EntityNotFoundException("Task with id = " + commentJsonBody.getTask() + " doesn't exist");
        } else comment.setTask(task.get());

        comment.setContent(commentJsonBody.getContent());


        commentRepository.save(comment);

        Example<Comment> example = Example.of(comment);
        return convertToCommentJsonBody(commentRepository.findOne(example).get());
    }

    public List<CommentJsonBody> getCommentsByTaskId(Long id) {
        List<Comment> comments = commentRepository.getCommentsByTaskId(id);
        return comments.stream().map(c -> convertToCommentJsonBody(c)).toList();
    }

    protected CommentJsonBody convertToCommentJsonBody(Comment comment) {
        return CommentJsonBody.getBuilder().setId(comment.getId())
                .setContent(comment.getContent())
                .setCreationTime(comment.getCreationTime())
                .setTask(comment.getTask().getId())
                .build();
    }
}
