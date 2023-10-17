/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.augustoribeiro7.todolist.users;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gutom
 */
public interface IUserRepository extends JpaRepository<UserModel, UUID>{
   UserModel findByUsername(String username); //criando metodo para buscar username
}
