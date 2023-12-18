package com.effectivemobile.taskmanager.controller;

import com.effectivemobile.taskmanager.service.CommentService;
import com.effectivemobile.taskmanager.transportObject.CommentJsonBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/taskmanager/comment")
public class CommentController {

    @Autowired
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public CommentJsonBody createComment(@RequestBody CommentJsonBody commentJsonBody) {
        try {
            return commentService.createComment(commentJsonBody);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/getComments")
    public List<CommentJsonBody> getCommentsByTaskId(@RequestParam Long id) {
        return commentService.getCommentsByTaskId(id);
    }
}
