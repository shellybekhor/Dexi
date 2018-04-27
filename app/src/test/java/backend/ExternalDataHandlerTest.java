package backend;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExternalDataHandlerTest {
    ExternalDataHandler handler = new ExternalDataHandler();
    @Test
    public void writeExternalData() {
        ExternalDataHandler handler2 = new ExternalDataHandler();
        handler2.readExternalData();
        Statistics x = handler2.statistics;
    }
}