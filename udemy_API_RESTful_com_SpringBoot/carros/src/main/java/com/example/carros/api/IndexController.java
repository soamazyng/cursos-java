package com.example.carros.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String Get(){
        return "Get Spring";
    }

    @GetMapping("/login")
    public String GetLogin(@RequestParam("username") String login, @RequestParam("password") String senha){
        return "Login Spring " + login + " senha: " + senha;
    }

    @PostMapping("/login")
    public String PostLogin(@RequestParam("username") String login, @RequestParam("password") String senha){
        return "Login POST Spring " + login + " senha: " + senha;
    }

    @GetMapping("/loginpath/{username}/{password}")
    public String GetLoginPath(@PathVariable("username") String login, @PathVariable("password") String senha){
        return "Login Path Spring " + login + " senha: " + senha;
    }

    @GetMapping("/carros/{id}")
    public String getCarrosById(@PathVariable("id") Long id){
        return "Carro id: " + id;
    }

    @GetMapping("/carros/tipos/{tipo}")
    public String getCarrosByTipo(@PathVariable("tipo") String tipo){
        return "Carro tipo " + tipo;
    }

    @PostMapping()
    public String Post(){
        return "Post Spring";
    }

    @PutMapping()
    public String Put(){
        return "Put Spring";
    }

    @DeleteMapping()
    public String Delete(){
        return "Delete Spring";
    }


}
