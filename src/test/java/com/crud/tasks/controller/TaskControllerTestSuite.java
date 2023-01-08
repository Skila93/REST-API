package com.crud.tasks.controller;


import com.crud.tasks.domain.TaskDto;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TaskControllerTestSuite {
    @Autowired
    TaskController taskController;

    @Test
    void creationAndDeletingTaskTest(){

        //Given
        TaskDto taskDto = new TaskDto(null, "test", "Test content");

        //When
        //Adding task
        try{
            int amountOfTasks = taskController.getTasks().getBody().size();
            taskController.createTask(taskDto);
            int amountOfTasksAfterCreation = taskController.getTasks().getBody().size();

            //Then
            assertTrue(amountOfTasksAfterCreation > amountOfTasks);
        } catch (Exception e){
            fail("creationAndDeletingTaskTest() threw exception.");
        }

        //RemovingTask
        int amountOfTasks = taskController.getTasks().getBody().size();
        List<TaskDto> tasks = taskController.getTasks().getBody();
        TaskDto addedTask = tasks.get(tasks.size()-1);
        taskController.deleteTask(addedTask.getId());
        int amountOfTasksAfterRemoving = taskController.getTasks().getBody().size();
        assertTrue(amountOfTasks > amountOfTasksAfterRemoving);
    }
    @Test
    void getTasks() {

        //When & Then
        assertDoesNotThrow(()->taskController.getTasks());
    }
}