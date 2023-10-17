/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.augustoribeiro7.todolist.filter;


import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.augustoribeiro7.todolist.users.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author gutom
 */

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        // VERIFICANDO A ROTA
        var servlePath = request.getServletPath();
        
        if(servlePath.startsWith("/tasks/"))
        {
            // RECEBER USUARIO E SENHA
            var authorization = request.getHeader("Authorization");

            //removendo a palavra basic e os espaços
            var authEncoded = authorization.substring("Basic".length()).trim();

            // decodificando para um array de bytes
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            // decodificando de bytes para string o user e senha
            var authString = new String(authDecode);

            // separando o usario e a senha um do outro
            String[] auth = authString.split(":");

            String username = auth[0];
            String password = auth[1];

            //VALIDAR USUARIO
            var user = this.userRepository.findByUsername(username);
            if(user == null)
            {
                //retornar erro de não autorizado
                response.sendError(401);
            }
            else
            {
                //VALIDAR SENHA
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                if(passwordVerify.verified)
                {
                    //setando o id do usuario nas requisição
                    request.setAttribute("idUser", user.getId());
                   //SEGUE
                    filterChain.doFilter(request, response);
                }
                else
                {
                    //retornar erro de não autorizado
                    response.sendError(401);
                }
            }
        }
        else
        {
            //SEGUE
            filterChain.doFilter(request, response); 
        }

        
    }

   
}
