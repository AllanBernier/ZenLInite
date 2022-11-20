package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import file.Load;

public class TestFile {
	
	
	@Test()
	public void testLoad()
	{
		Load l = new Load(1);
		 assertEquals(l.getMode(),1);
		 assertEquals(l.getNextPlayer(),-1);
		 assertEquals(l.getNoTurn(),18);
		 assertEquals(l.getPlateau()[0][0],1);
		 assertNotNull(l.getZen());		
	}
}
