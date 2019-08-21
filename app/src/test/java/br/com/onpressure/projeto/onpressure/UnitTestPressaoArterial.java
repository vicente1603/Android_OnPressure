package br.com.onpressure.projeto.onpressure;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.com.onpressure.projeto.onpressure.Model.PressaoArterial.PressaoArterial;
import br.com.onpressure.projeto.onpressure.Model.PressaoArterial.PressaoArterialDAO;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class UnitTestPressaoArterial {

    private static PressaoArterial pressaoArterial;
    private static PressaoArterialDAO mockPressaoArterialDAO;

    @Before
    public void oneTimeSetUp() throws Exception {

        mockPressaoArterialDAO = mock(PressaoArterialDAO.class);

    }

    @Test
    public void testeSalvarPressao() {

          when(mockPressaoArterialDAO.salvar(120, 80, 75, "Teste", "18/08/2019")).thenReturn(true);
          assertEquals(true, mockPressaoArterialDAO.salvar(120, 80, 75, "Teste", "18/08/2019"));
    }

    @Test
    public void testeRemoverPressao() {

        when(mockPressaoArterialDAO.excluir(2)).thenReturn(true);
        assertEquals(true, mockPressaoArterialDAO.excluir(2));
    }

    @Test
    public void testeRetornarUltimaPressao() {

        PressaoArterial pressaoArterialUltima = new PressaoArterial(2,80, 80, 80,
                "Continue monitorando sua pressão arterial.", "21/08/2019");
        PressaoArterial pressaoArterial = new PressaoArterial(1,120, 85, 75,
                "Sua classificação de risco é: Normal.", "21/08/2019");

        when(mockPressaoArterialDAO.retornarUltimo()).thenReturn(pressaoArterialUltima);
        assertEquals(pressaoArterialUltima, pressaoArterial);
    }

    @Test
    public void testeRetornarTodasPressao() {

        ArrayList<PressaoArterial> todasBanco = new ArrayList<>();

        ArrayList<PressaoArterial> todas = new ArrayList<>();
        todas.add(new PressaoArterial(2,80, 80, 80,
                "Continue monitorando sua pressão arterial.", "21/08/2019"));
        todas.add(new PressaoArterial(3,120, 85, 75,
                "Sua classificação de risco é: Normal.", "21/08/2019"));

        Mockito.when(mockPressaoArterialDAO.retornarTodas()).thenReturn(todasBanco);

        List<PressaoArterial> result = mockPressaoArterialDAO.retornarTodas();

        verify(mockPressaoArterialDAO).retornarTodas();

        Assert.assertEquals(todasBanco, result);
    }
}

