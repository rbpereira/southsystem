package pageobject;

import org.openqa.selenium.By;

public class SimuladorInvestimentosPage {
    public static By txtValorAplicar()
    {
        By txt = (By.id("valorAplicar"));
        return txt;
    }

    public static By txtValorInvestir()
    {
        By txt = (By.id("valorInvestir"));
        return txt;
    }

    public static By txtTempo()
    {
        By txt = (By.id("tempo"));
        return txt;
    }

    public static By btnSimular()
    {
        By btn = (By.className("btnSimular"));
        return btn;
    }

    public static By msgErro()
    {
        By msg = (By.className("erro"));
        return msg;
    }

    public static By lblValorTotal()
    {
        By lbl = (By.xpath("//span[contains(@class, 'valor')]"));
        return lbl;
    }
}
