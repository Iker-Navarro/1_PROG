package pruebas_JUNIT;

import static org.junit.Assert.*;

import org.junit.Test;


public class DivTest {

	@Test (expected = java.lang.ArithmeticException.class)
	public void testDivide0() {
		Div d = new Div(20, 0);
		int res = d.divide0();
	}

}
