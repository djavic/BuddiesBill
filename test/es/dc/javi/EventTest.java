package es.dc.javi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EventTest {

	private Event event;

	private String pepe;
	private String luis;
	private String juan;
	private String javi;
	private String antonio;

	private String coche;
	private String cena;

	@Before
	public void setUp() throws Exception {

		this.event = new Event("Weekend", 5, 6);

		pepe = "pepe";
		luis = "luis";
		juan = "juan";
		javi = "javi";
		antonio = "antonio";
		coche = "coche";
		cena = "cena";

	}

	@Test
	public void testEventStringIntInt() {
		assertEquals(this.event.getEventName(),"Weekend");
	}


	@Test
	public void testAddBuddy() throws BuddiesBillExceptions {

		this.event.addBuddy(pepe);
		assertEquals(this.event.showInfoBuddies(), "pepe: 0,00\n");

	}

	@Test
	public void testRemoveBuddy() throws BuddiesBillExceptions {

		this.event.addBuddy(pepe);
		this.event.addBuddy(juan);
		this.event.addBuddy(luis);
		this.event.removeBuddy(juan);
		assertEquals("pepe: 0,00\n" + "luis: 0,00\n",this.event.showInfoBuddies());
	}

	@Test
	public void testAddMovement() throws BuddiesBillExceptions {

		this.event.addBuddy(juan);
		this.event.addMovement(coche, 20, juan);
		assertEquals("coche - 20,00 pagado por juan\n",this.event.showInfoBills());
		assertEquals(this.event.showInfoBuddies(), "juan: 20,00\n");
	}

	@Test(expected = BuddiesBillExceptions.class)
	public void testAddMovementParticipantNotInList() throws BuddiesBillExceptions {

		this.event.addMovement(coche, 20, juan);
		assertEquals("coche - 20,00 pagado por juan",this.event.showInfoBills());
		assertEquals("juan: 20,00\n",this.event.showInfoBuddies());
	}

	@Test
	public void testRemoveMovementOk() throws BuddiesBillExceptions {

		this.event.addBuddy(juan);
		this.event.addMovement(coche, 20, juan);
		this.event.addMovement(cena, 10, juan);
		assertEquals("coche - 20,00 pagado por juan\n" + "cena - 10,00 pagado por juan\n",this.event.showInfoBills());
		assertEquals( "juan: 30,00\n",this.event.showInfoBuddies());
		this.event.removeMovement(coche);
		assertEquals( "cena - 10,00 pagado por juan\n",this.event.showInfoBills());
		assertEquals( "juan: 10,00\n",this.event.showInfoBuddies());
	}
	
	@Test(expected = BuddiesBillExceptions.class)
	public void testRemoveMovementNotInList() throws BuddiesBillExceptions {
		
		this.event.removeMovement(coche);
		
	}
	@Test
	public void testShowInfoBuddies() throws BuddiesBillExceptions {
		this.event.addBuddy(juan);
		this.event.addBuddy(pepe);
		this.event.addBuddy(luis);
		assertEquals("juan: 0,00\n" + "pepe: 0,00\n" + "luis: 0,00\n",this.event.showInfoBuddies());
		
		
	}

	@Test
	public void testShowInfoBills() throws BuddiesBillExceptions {
		this.event.addBuddy(juan);
		this.event.addMovement(coche, 20, juan);
		this.event.addMovement(cena, 10, juan);
		assertEquals( "coche - 20,00 pagado por juan\n" + "cena - 10,00 pagado por juan\n",this.event.showInfoBills());
		
	}

	@Test
	public void testShowBalance() throws BuddiesBillExceptions {
		this.event.addBuddy(juan);
		this.event.addMovement(coche, 20, juan);
		this.event.addMovement(cena, 10, juan);
		assertEquals("30,00",this.event.showBalance());
	}

	@Test
	public void testSettleUpCasos3_5() throws BuddiesBillExceptions {
		this.event.addBuddy(juan);
		this.event.addBuddy(pepe);
		this.event.addBuddy(luis);
		this.event.addBuddy(javi);
		this.event.addBuddy(antonio);
		this.event.addMovement(coche, 10, juan);
		assertEquals("Payment from pepe to juan: 2.0\n"+"Payment from luis to juan: 2.0\n"+"Payment from javi to juan: 2.0\n"+"Payment from antonio to juan: 2.0\n", this.event.settleUp());
	}
	
	@Test
	public void testSettleUpCasos2_4_5() throws BuddiesBillExceptions {
		this.event.addBuddy(juan);
		this.event.addBuddy(pepe);
		this.event.addBuddy(luis);
		this.event.addBuddy(javi);
		this.event.addBuddy(antonio);
		this.event.addMovement(coche, 10, juan);
		this.event.addMovement(cena, 20, javi);
		assertEquals("Payment from pepe to juan: 4.0\n"+"Payment from pepe to javi: 2.0\n"+"Payment from luis to javi: 6.0\n"+"Payment from antonio to javi: 6.0\n", this.event.settleUp());
	}

	@Test
	public void testSettleUpCasos1_2_6() throws BuddiesBillExceptions { 
		this.event.addBuddy(juan);
		this.event.addBuddy(pepe);
		this.event.addBuddy(luis);
		this.event.addBuddy(javi);
		this.event.addBuddy(antonio);
		this.event.addMovement(coche, 10, pepe);
		this.event.addMovement(cena, 20, antonio);
		assertEquals("Payment from juan to pepe: 4.0\n"+"Payment from juan to antonio: 2.0\n"+"Payment from luis to antonio: 6.0\n"+"Payment from javi to antonio: 6.0\n", this.event.settleUp());
	}
}
