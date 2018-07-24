package es.dc.javi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BillTest {
	
	Bill note;
	Buddy person;
	
	@Before
	public void setUp() throws BuddiesBillExceptions{
		person = new Buddy("Rose",0.0f);
		note = new Bill("Cena",1.0f,person);
	}

	@Test
	public void testBillBill() throws BuddiesBillExceptions {
		note = new Bill(note); 
		assertEquals("Cena",note.getName());
		assertEquals(1,0f,note.getHowMuch());
		assertEquals("Rose",note.getWho().getName());
		
	}

	@Test
	public void testBillStringDoubleBuddy() throws BuddiesBillExceptions {
		assertEquals("Cena",note.getName());
		assertEquals(1,0f,note.getHowMuch());
		assertEquals("Rose",note.getWho().getName());
	}


	@Test
	public void testToString() {
		assertEquals("Cena - 1,00 pagado por Rose" + "\n", note.toString());
	}

}
