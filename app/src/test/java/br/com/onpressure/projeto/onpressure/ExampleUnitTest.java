package br.com.onpressure.projeto.onpressure;

import android.content.Context;

import org.junit.Test;
import org.mockito.Mockito;

import java.net.ContentHandler;
import java.util.Calendar;
import java.util.Date;

import br.com.onpressure.projeto.onpressure.Activities.PressaoArterialActivity;
import br.com.onpressure.projeto.onpressure.Model.PressaoArterial.PressaoArterial;
import br.com.onpressure.projeto.onpressure.Model.PressaoArterial.PressaoArterialDAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ExampleUnitTest {
    PressaoArterialDAO pressaoArterialDAO;

    @Test
    public void teste() {

        pressaoArterialDAO = mock(PressaoArterialDAO.class);
        Mockito.when(pressaoArterialDAO.salvar(80, 80,
                80,"teste", "18/08/2019")).thenReturn(true);

    }

}