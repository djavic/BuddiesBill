package es.dc.javi;

import static org.junit.Assert.*;

import org.junit.Test;


import org.junit.Before;

public class BuddyTest {

	private Buddy rose;
	private Buddy pepe;

	@Before

	public void setUp() throws Exception {
		rose = new Buddy("Rose", 1.0f);
		pepe = new Buddy("Pepe",15.0f);
	}

	@Test
	public void testBuddyBuddy() throws BuddiesBillExceptions {
		rose = new Buddy(rose);
		assertEquals("Rose", rose.getName());
		assertEquals(1, 0f, rose.getExpentMoney());
	}

	@Test
	public void testBuddyStringFloat() {
		assertEquals("Rose", rose.getName());
		assertEquals(1, 0f, rose.getExpentMoney());
	}

	@Test
	public void testGetName() {
		assertEquals("Rose", rose.getName());
	}

	@Test
	public void testPay() throws BuddiesBillExceptions {
		
		this.rose.pay(20.0f);
		assertEquals(21,0f,rose.getExpentMoney());
		
		
	}
	
	@Test
	public void testReicive() throws BuddiesBillExceptions {
		
		this.pepe.receive(10.0f);
		assertEquals(5,0f,pepe.getExpentMoney());
		
	}

	@Test
	public void testToString() {
		assertEquals("Rose: 1,00" + "\n", rose.toString());

	}

}
