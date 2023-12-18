package com.effectivemobile.taskmanager.repository;

import com.effectivemobile.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(nativeQuery = true, value = "select * from tasks where assignee_id in(:id)")
    List<Task> getTasksByAssigneeUser(Long id);

    @Query(nativeQuery = true, value = "select * from tasks where reporter_id in(:id)")
    List<Task> getTasksByReporter(Long id);
}
