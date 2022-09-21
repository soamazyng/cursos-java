package br.com.upmasters.domain.service;

import br.com.upmasters.rest.controller.dto.CredenciaisDTO;
import br.com.upmasters.rest.controller.dto.RequestUsuarioDto;
import br.com.upmasters.rest.controller.dto.ResponseUsuarioDto;
import br.com.upmasters.rest.controller.dto.TokenDTO;

public interface UsuarioService {

  ResponseUsuarioDto salvar(RequestUsuarioDto dto);

  ResponseUsuarioDto obterPorId(Integer id);

  TokenDTO auth(CredenciaisDTO credenciaisDTO);

}