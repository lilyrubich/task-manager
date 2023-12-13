package com.effectivemobile.taskmanager.controller;


import com.effectivemobile.taskmanager.model.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @PostMapping("/add")
    public Comment createComment(@RequestBody Comment comment) {
        return null;
    }

    @GetMapping("/getComments")
    public List<Comment> getCommentsByTaskId(@RequestParam Long id) {
        return null;
    }
}
