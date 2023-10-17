/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.augustoribeiro7.todolist.task;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author gutom
 */

@Entity(name = "tb_tasks")
public class TaskModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(length = 50) //limitar o titulo a 50 caracteres
    private String title;
    private String description;
    private LocalDateTime startTask;
    private LocalDateTime endTask;
    private String priority;
    
    //quando a tarefa foi criada
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    //id do usuario
    private UUID idUser;
    
    
    
    //getters and seters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTask() {
        return startTask;
    }

    public void setStartTask(LocalDateTime startTask) {
        this.startTask = startTask;
    }

    public LocalDateTime getEndTask() {
        return endTask;
    }

    public void setEndTask(LocalDateTime endTask) {
        this.endTask = endTask;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    
    

}
