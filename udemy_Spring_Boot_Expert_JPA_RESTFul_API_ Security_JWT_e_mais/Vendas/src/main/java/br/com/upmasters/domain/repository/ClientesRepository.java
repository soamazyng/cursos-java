package br.com.upmasters.domain.repository;

import br.com.upmasters.configurations.ProjectConfiguration;
import br.com.upmasters.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientesRepository {

  private static String INSERT = "insert into cliente (nome) values (?)";
  private static String UPDATE = "update cliente set nome = ? where id = ?";
  private static String SELECT_ALL = "select * from cliente";
  private static String SELECT_BY_NOME = "select * from cliente where nome like ?";
  private static String DELETE = "delete cliente where id = ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  ProjectConfiguration configuration;

  public Cliente salvar(Cliente cliente){

    configuration.logger().info("salvando os dados");

    jdbcTemplate.update(INSERT, cliente.getNome());

    return cliente;
  }

  public Cliente atualizar(Cliente cliente){
    jdbcTemplate.update(UPDATE, new Object[]{
        cliente.getNome(), cliente .getId()
    });

    return cliente;
  }

  public void deletar(Cliente cliente){
    configuration.logger().info("deletando o cliente: " + cliente.getId());
    jdbcTemplate.update(DELETE, cliente.getId());
  }

  public List<Cliente> ObterTodos(){
    return jdbcTemplate.query(SELECT_ALL, generateRowMapper());
  }

  public List<Cliente> BuscarPeloNome(String nome){
    return jdbcTemplate.query(SELECT_BY_NOME, new Object[]{"%" + nome + "%"}, generateRowMapper());
  }

  private RowMapper<Cliente> generateRowMapper() {
    return new RowMapper<Cliente>() {
      @Override
      public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        return new Cliente(id, nome);
      }
    };
  }

}
