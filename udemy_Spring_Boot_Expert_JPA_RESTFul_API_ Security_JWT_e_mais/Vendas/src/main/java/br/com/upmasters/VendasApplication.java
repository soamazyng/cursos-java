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
      Cliente cliente = new Cliente("Jay Benedicto");
      clientes.save(cliente);

      Cliente cliente2 = new Cliente( "Novo Cliente");
      clientes.save(cliente2);

      var clientesList = clientes.findAll();

      clientesList.forEach(System.out::println);

      clientesList.forEach(c-> {
        c.setNome((c.getNome() + " atualizado"));
        clientes.save(c);
      });

      clientesList = clientes.findAll();

      System.out.println();

      clientesList.forEach(System.out::println);

      System.out.println("Buscando clientes ....");

      clientes.encontrarPorNome("Jay").forEach(c-> System.out.println(c));

      System.out.println();

      clientes.findAll().forEach(c -> clientes.delete(c));

      clientesList = clientes.findAll();

      System.out.println("clientes deletados");

      clientesList.forEach(System.out::println);

    };
  }


  public static void main(String[] args) {
    SpringApplication.run(VendasApplication.class, args);
  }
}
