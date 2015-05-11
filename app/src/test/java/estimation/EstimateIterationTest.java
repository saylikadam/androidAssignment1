package estimation;

import org.junit.Test;

import static org.junit.Assert.*;

public class EstimateIterationTest {
    @Test
    public void adfsa(){
        EstimateIteration es = new EstimateIteration();
        assertEquals(es.getIteration(30,6),5);
    }

}