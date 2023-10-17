/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.augustoribeiro7.todolist.task;

import br.com.augustoribeiro7.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gutom
 */

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private ITaskRepository taskRepository;
    
    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel task, HttpServletRequest request)
    {
        //setando o id do usuario na task
        task.setIdUser((UUID) request.getAttribute("idUser"));
        
        //verificando se a data postada não é menor que a atual
        var currentDate = LocalDateTime.now(); //data e hora atual
        
        if(currentDate.isAfter(task.getStartTask()) || currentDate.isAfter(task.getEndTask())) //se a atual é maior que a postada
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio/termino deve ser maior que a atual!");
        }
        //vereficar se a data de fim nao é menor que a data de inicio
        if(task.getEndTask().isBefore(task.getStartTask()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de termino deve ser maior que a data de inicio!");
        }
        
        //salvando task no banco de dados
        var taskCreated = this.taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(taskCreated);
    }
    
    //Visualizar todas as Tasks ja realizadas por um determinado usuario
    @GetMapping("/")
    public List<TaskModel> List(HttpServletRequest request)
    {
        var tasks = this.taskRepository.findByIdUser((UUID) request.getAttribute("idUser"));
        return tasks;
    }
    
    //atualizar a task com uma path variavel
    @PutMapping("/{id}")
    public TaskModel update(@RequestBody TaskModel task, HttpServletRequest request, @PathVariable UUID id)
    {
        var taskNow = this.taskRepository.findById(id).orElse(null);
       
        Utils.copyNonNullProperties(task, taskNow);
        
        return this.taskRepository.save(taskNow);
    }
}
