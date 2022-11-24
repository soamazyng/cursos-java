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
    Then a entrega serah efetuada em 07/04/2018
