Feature: Aprender Cucumber
  Como um aluno
  Eu quero aprender a utilizar Cucumber
  Para que eu possa automatizar critérios de aceitação

  Scenario: Deve executar especificacao
    Given que cirei o arquivo corretamente
    When executa-lo
    Then a especificacao deve finalizar com sucesso

  Scenario: Deve incrementar contador
    Given que o valor do contador é 15
    When eu incrementar em 3
    Then o valor do contador será 18

  Scenario: Deve calcular atraso no prazo de entrega
    Given que o prazo eh dia 05/04/2018
    When a entrega atrasar em 2 dias
    Then a entrega serah efetuada em "07/04/2018"

  Scenario: Deve criar steps genericos para estes passos
    Given que o ticket eh AF345
    Given que o valor da passagem eh R$ 230,45
    Given que o nome do passageiro eh "Fulano da Silva"
    Given que o telefone do passageiro eh 9999-9999
    When criar os steps
    Then o teste vai funcionar

  Scenario: Deve reaproveitar os steps "Dado" do cenario anterior
    Given que o ticket eh AB167
    Given que o ticket especial eh AB167
    Given que o valor da passagem eh R$ 1120,23
    Given que o nome do passageiro eh "Cicrano de Oliveira"
    Given que o telefone do passageiro eh 9888-8888
    When criar os steps
    Then o teste vai funcionar

  Scenario: Deve negar todos os steps "Dado" dos cenarios anteriores
    Given que o ticket eh CD123
    Given que o ticket eh AG1234
    Given que o valor da passagem eh R$ 11345,56
    Given que o nome do passageiro eh "Beltrano Souza Matos de Alcântara Azevedo"
    Given que o telefone do passageiro eh 1234-5678
    Given que o telefone do passageiro eh 999-2223
    When criar os steps
    Then o teste vai funcionar
