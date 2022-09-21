package br.com.upmasters.rest.controller;

import br.com.upmasters.domain.service.UsuarioService;
import br.com.upmasters.rest.controller.dto.CredenciaisDTO;
import br.com.upmasters.rest.controller.dto.RequestUsuarioDto;
import br.com.upmasters.rest.controller.dto.ResponseUsuarioDto;
import br.com.upmasters.rest.controller.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

  private final UsuarioService usuarioService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseUsuarioDto salvar(@RequestBody @Valid RequestUsuarioDto requestUsuarioDto) {

    return usuarioService.salvar(requestUsuarioDto);

  }

  @PostMapping("/auth")
  public TokenDTO autenticar(@RequestBody @Valid CredenciaisDTO credenciaisDTO) {

    return usuarioService.auth(credenciaisDTO);

  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseUsuarioDto obterPorId(@PathVariable Integer id) {

    ResponseUsuarioDto responseUsuarioDto = usuarioService.obterPorId(id);
    return responseUsuarioDto;

  }


}