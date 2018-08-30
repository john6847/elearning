package com.sorbSoft.CabAcademie.Controladores;

import com.sorbSoft.CabAcademie.Entities.Usuario;
import com.sorbSoft.CabAcademie.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by Dany on 20/05/2018.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UsuarioServices userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> getUser(@PathVariable Long id){
        if(id<0)
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Usuario user= userService.buscarUnUsuario(id);
        if(user==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/loggedIn")
    public ResponseEntity<Usuario> getLoggedInUser(Principal user){
        System.out.println("Printing user");
        System.out.println(user.getName());
        Usuario loadedUser= userService.buscarUsuarioPorUsername(user.getName());

        return new ResponseEntity<>(loadedUser, HttpStatus.OK);

    }


    @GetMapping()
    public ResponseEntity<List<Usuario>> getAllUser(){
        List<Usuario> users= userService.buscarTodosUsuarios();
        if(users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping()
    public  ResponseEntity<Usuario> saveUsers(@Valid @RequestBody Usuario user){
        Usuario currentUser= userService.guardarUsuario(user);
        if(currentUser==null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario user){
        if(id<0)
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(userService.buscarUnUsuario(id)==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Usuario currentUsuario= userService.updateUsuario(user);
        if (currentUsuario==null)
            return  new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        return new ResponseEntity<>(currentUsuario, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id ){
        if(id<0)
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(userService.buscarUnUsuario(id)==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        userService.eliminarUsuario(id);
        return new ResponseEntity<>("User with "+ id+" Deleted!", HttpStatus.OK);
    }
}
