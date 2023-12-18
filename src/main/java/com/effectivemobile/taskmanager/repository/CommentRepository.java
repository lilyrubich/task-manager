package com.effectivemobile.taskmanager.repository;

import com.effectivemobile.taskmanager.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(nativeQuery = true, value = "select * from comments where task_id in(:id)")
    List<Comment> getCommentsByTaskId(Long id);
}
