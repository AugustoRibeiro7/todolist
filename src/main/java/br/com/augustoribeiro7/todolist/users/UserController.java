/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.augustoribeiro7.todolist.users;


import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author gutom
 */

@RestController
@RequestMapping("/users")
// http://localhost:8080/ ------
public class UserController {
    
    @Autowired //o Spring boot irá gerenciar este atributo, se precisar inicializar, ele o fará, entre outras coisas.
    private IUserRepository userRepository;
    
    //responseEntity permite retornar quanto for sucesso e quando for erro tambem
    
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel user)
    {
        var user_repetido = this.userRepository.findByUsername(user.getUsername());
        
        if(user_repetido != null)
        {
            //retornar mensagem de erro com o codigo do erro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado!");
        }
        //criptografando a senha
        var password_crypt = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(password_crypt);
        
        //salvando no banco de dados
        var userCreated = this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
    
}
