package br.com.upmasters.domain.service.impl;

import br.com.upmasters.domain.entity.Usuario;
import br.com.upmasters.domain.repository.UsuarioRepository;
import br.com.upmasters.domain.service.UsuarioService;
import br.com.upmasters.exception.NotFoundDataException;
import br.com.upmasters.exception.RegraNegocioException;
import br.com.upmasters.exception.SenhaInvalidaException;
import br.com.upmasters.rest.controller.dto.CredenciaisDTO;
import br.com.upmasters.rest.controller.dto.RequestUsuarioDto;
import br.com.upmasters.rest.controller.dto.ResponseUsuarioDto;
import br.com.upmasters.rest.controller.dto.TokenDTO;
import br.com.upmasters.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

  private final PasswordEncoder encoder;

  private final UsuarioRepository usuarioRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Usuario usuario = usuarioRepository.findByLogin(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    String[] roles = usuario.isAdmin()
        ? new String[]{"ADMIN", "USER"}
        : new String[]{"USER"};

    return User
        .builder()
        .username(usuario.getLogin())
        .password(usuario.getSenha())
        .roles(roles)
        .build();

  }

  @Override
  @Transactional
  public ResponseUsuarioDto salvar(RequestUsuarioDto dto) {

    var userJaExiste = usuarioRepository.findByLogin(dto.getLogin());

    if (userJaExiste.isPresent()) {
      throw new RegraNegocioException("Usuário já existe com esse login");
    }

    Usuario usuario = new Usuario();
    usuario.setLogin(dto.getLogin());
    usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
    usuario.setAdmin(dto.isAdmin());

    Usuario usuarioResponse = usuarioRepository.save(usuario);

    return ResponseUsuarioDto
        .builder()
        .id(usuarioResponse.getId())
        .login(usuarioResponse.getLogin())
        .build();

  }

  @Override
  public ResponseUsuarioDto obterPorId(Integer id) {
    Optional<Usuario> user = usuarioRepository.findById(id);

    if (user.isEmpty())
      throw new NotFoundDataException("Pedido não encontrado");

    return ResponseUsuarioDto
        .builder()
        .login(user.get().getLogin())
        .id(user.get().getId())
        .isAdmin(user.get().isAdmin())
        .build();

  }

  @Override
  public TokenDTO auth(CredenciaisDTO credenciaisDTO) {

    Usuario usuario = new Usuario();
    usuario.setLogin(credenciaisDTO.getLogin());
    usuario.setSenha(credenciaisDTO.getSenha());

    autenticar(usuario);

    String token = jwtService.gerarToken(usuario);

    TokenDTO tokenDTO = new TokenDTO();
    tokenDTO.setToken(token);
    tokenDTO.setLogin(credenciaisDTO.getLogin());

    return tokenDTO;

  }

  private UserDetails autenticar(Usuario usuario) {

    UserDetails user = loadUserByUsername(usuario.getLogin());

    boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());

    if (senhasBatem) {
      return user;
    }

    throw new SenhaInvalidaException();

  }
}