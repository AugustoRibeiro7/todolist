/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.augustoribeiro7.todolist.task;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gutom
 */
public interface ITaskRepository extends JpaRepository<TaskModel, UUID>{
    List <TaskModel> findByIdUser(UUID idUser);
}
