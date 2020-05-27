Feature: Simular um Investimento na Poupança como um Associado
  COMO: Um Associado
  EU QUERO: Preencher o formulário de simulação
  PARA: Ver a tabela de resultado com Mês e Valor.

 Scenario Outline: Simular um investimento preenchendo o formulário corretamente
   Given Que o formulário de simulação do Sicred esteja aberto
   When Preencher todos os campos <valorAplicar>, <valorInvestir>, <tempo>
   And Submeter o fomulário
   Then É exibida a tabela de valores, referente aos meses de investimento
   Examples:
   |valorAplicar|valorInvestir|tempo|
   |3000        |5000         |3    |

  Scenario Outline: Simular um investimento preenchendo o formulário com valores menor que 20.00
    Given Que o formulário de simulação do Sicred esteja aberto
    When Preencher todos os campos <valorAplicar>, <valorInvestir>, <tempo>
    And Submeter o fomulário
    Then É exibida a mensagem <mensagem>
    Examples:
    |valorAplicar|valorInvestir|tempo|mensagem             |
    |10000       |10           |5    |Valor mínimo de 20.00|
    |5           |21000        |5    |Valor mínimo de 20.00|