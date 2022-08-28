package br.com.upmasters;


import br.com.upmasters.configurations.ProjectConfiguration;
import br.com.upmasters.domain.entity.Cliente;
import br.com.upmasters.domain.repository.ClientesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

  @Autowired
  ProjectConfiguration configuration;

  ObjectMapper mapper = new ObjectMapper();

  @Bean
  public CommandLineRunner init(@Autowired ClientesRepository clientes){

    configuration.logger().info("Acesso o Bean");

    return args -> {
      Cliente cliente = new Cliente("Jay");
      clientes.salvar(cliente);

      Cliente cliente2 = new Cliente( "Jay 2");
      clientes.salvar(cliente2);

      var clientesList = clientes.ObterTodos();

      clientesList.forEach(System.out::println);

      clientesList.forEach(c-> {
        c.setNome((c.getNome() + " atualizado"));
        clientes.atualizar(c);
      });

      clientesList = clientes.ObterTodos();

      System.out.println();

      clientesList.forEach(System.out::println);

      clientes.BuscarPeloNome("Jay").forEach(c-> System.out.println(c));

      System.out.println();

      clientes.ObterTodos().forEach(c -> clientes.deletar(c));

      clientesList = clientes.ObterTodos();

      System.out.println("clientes deletados");

      clientesList.forEach(System.out::println);

    };
  }


  public static void main(String[] args) {
    SpringApplication.run(VendasApplication.class, args);
  }
}
