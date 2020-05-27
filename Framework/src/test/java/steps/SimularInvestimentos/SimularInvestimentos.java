package steps.SimularInvestimentos;

import Servicos.Funcionalidades;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pageobject.SimuladorInvestimentosPage;

public class SimularInvestimentos {

    @Given("Que o formulário de simulação do Sicred esteja aberto")
    public void queOFormulárioDeSimulaçãoDoSicredEstejaAberto() throws Exception {
            Funcionalidades.Inicializar("https://www.sicredi.com.br/html/ferramenta/simulador-investimento-poupanca/");
    }

    @When("Preencher todos os campos (.*), (.*), (.*)")
    public void preencherTodosOsCamposValorAplicarValorInvestirTempo(String valorAplicar, String valorInvestir, String tempo) {
        Funcionalidades.EsperarObjetoCarregar(SimuladorInvestimentosPage.txtValorAplicar());
        Funcionalidades.EnviarTexto(valorAplicar, SimuladorInvestimentosPage.txtValorAplicar());
        Funcionalidades.EnviarTexto(valorInvestir, SimuladorInvestimentosPage.txtValorInvestir());
        Funcionalidades.EnviarTexto(tempo, SimuladorInvestimentosPage.txtTempo());
    }

    @And("Submeter o fomulário")
    public void submeterOFomulário() {
        Funcionalidades.Clicar(SimuladorInvestimentosPage.btnSimular());
    }

    @Then("É exibida a tabela de valores, referente aos meses de investimento")
    public void éExibidaATabelaDeValoresReferenteAosMesesDeInvestimento() throws InterruptedException {
        //Funcionalidades.EsperarObjetoCarregar(SimuladorInvestimentosPage.lblValorTotal());
        Thread.sleep(5000);
        String ValorTotal = Funcionalidades.CapturarTexto(SimuladorInvestimentosPage.lblValorTotal());
        //Assert.assertEquals(mensagem, texto);
    }

    @Then("É exibida a mensagem (.*)")
    public void éExibidaAMensagemMensagem(String mensagem) {
        Funcionalidades.EsperarObjetoCarregar(SimuladorInvestimentosPage.msgErro());
        String[] splTexto;
        String texto = Funcionalidades.CapturarTexto(SimuladorInvestimentosPage.msgErro());
        splTexto = texto.split("\n");

        Assert.assertEquals(mensagem, splTexto[2]);
    }
}
