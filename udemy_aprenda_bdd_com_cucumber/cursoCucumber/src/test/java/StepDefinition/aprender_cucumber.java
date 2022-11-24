package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class aprender_cucumber {

  @Given("que cirei o arquivo corretamente")
  public void queCireiOArquivoCorretamente() {
    // Write code here that turns the phrase above into concrete actions
  }
  @When("executa-lo")
  public void executaLo() {
    // Write code here that turns the phrase above into concrete actions
  }
  @Then("a especificacao deve finalizar com sucesso")
  public void aEspecificacaoDeveFinalizarComSucesso() {
    // Write code here that turns the phrase above into concrete actions
  }

private int contador = 0;

  @Given("que o valor do contador é {int}")
  public void queOValorDoContadore(Integer int1) {
    contador = int1;
  }
  @When("eu incrementar em {int}")
  public void euIncrementarEm(Integer int1) {
    contador = contador+int1;
  }
  @Then("o valor do contador será {int}")
  public void oValorDoContadorSera(Integer int1) {
    assertEquals((int)int1, contador);
  }

  Date entrega = new Date();

  @Given("que o prazo eh dia {int}\\/{int}\\/{int}")
  public void queOPrazoEhDia(Integer dia, Integer mes, Integer ano) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, dia);
    cal.set(Calendar.MONTH, mes - 1);
    cal.set(Calendar.YEAR, ano);
    entrega = cal.getTime();
  }
  @When("a entrega atrasar em {int} dias")
  public void aEntregaAtrasarEmDias(Integer int1) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(entrega);
    cal.add(Calendar.DAY_OF_MONTH, int1);
    entrega = cal.getTime();
  }
  @Then("a entrega serah efetuada em {int}\\/{int}\\/{int}")
  public void aEntregaSerahEfetuadaEm(Integer dia, Integer mes, Integer ano) {
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    String dataFormatada = format.format(entrega);

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, dia);
    cal.set(Calendar.MONTH, mes - 1);
    cal.set(Calendar.YEAR, ano);

    String dataEntregaFormatada = format.format(cal.getTime());

    assertEquals(dataEntregaFormatada, dataFormatada);
  }

}
