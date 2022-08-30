package br.com.upmasters;


import br.com.upmasters.configurations.ProjectConfiguration;
import br.com.upmasters.domain.entity.Cliente;
import br.com.upmasters.domain.entity.ItemPedido;
import br.com.upmasters.domain.entity.Pedido;
import br.com.upmasters.domain.repository.ClientesRepository;
import br.com.upmasters.domain.repository.PedidosRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

  @Autowired
  ProjectConfiguration configuration;

  ObjectMapper mapper = new ObjectMapper();

  @Bean
  public CommandLineRunner init(
      @Autowired ClientesRepository clientes,
      @Autowired PedidosRepository pedidos
  ){

    configuration.logger().info("Acesso o Bean");

    return args -> {
      Cliente cliente = new Cliente("Jay Benedicto");
      clientes.save(cliente);

      Cliente cliente2 = new Cliente("Jaqueline Benedicto");
      clientes.save(cliente2);

      Pedido p = new Pedido();
      p.setCliente(cliente);
      p.setDataPedido(LocalDateTime.now());
      p.setTotal(BigDecimal.valueOf(100));
      pedidos.save(p);

      Cliente clienteComPedidos = clientes.findClienteFetchPedidos(cliente.getId());
      System.out.println(clienteComPedidos);
      System.out.println(clienteComPedidos.getPedidos());

      Pedido p2 = new Pedido();
      p2.setCliente(cliente2);
      p2.setDataPedido(LocalDateTime.now());
      p2.setTotal(BigDecimal.valueOf(100));
      pedidos.save(p2);

      Pedido p3 = new Pedido();
      p3.setCliente(cliente2);
      p3.setDataPedido(LocalDateTime.now());
      p3.setTotal(BigDecimal.valueOf(100));
      pedidos.save(p3);

      pedidos.findByCliente(cliente2).forEach(c-> System.out.println(c.getCliente() + " " +c));

    };
  }


  public static void main(String[] args) {
    SpringApplication.run(VendasApplication.class, args);
  }
}
