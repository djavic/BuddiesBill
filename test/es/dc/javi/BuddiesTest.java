package es.dc.javi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
/**
 * 
 * @author Grupo 5K
 *
 */
public class BuddiesTest {

	private Buddies buddies;
	private Buddy[] buddy;
	private Buddy vivi;
	private Buddy pepe;
	private Buddy juan;
	private Buddy luis;
	
	private Buddy antonio;
	private Buddy javi;
	private Buddy laura;
	private Buddy marta;
	

	@Before
	public void setUp() throws BuddiesBillExceptions {

		this.buddies = new Buddies(6);
		this.vivi = new Buddy("vivi", 0.0f);
		this.pepe = new Buddy("pepe", 0.0f);
		this.juan = new Buddy("juan", 0.0f);
		this.luis = new Buddy("luis", 0.0f);
		this.luis = new Buddy("luis",0.0f);

		this.antonio = new Buddy("antonio", 1.0f);
		this.javi = new Buddy("javi", 20.0f);
		this.laura = new Buddy("laura",10.0f);
		this.marta = new Buddy("marta",40.0f);

	}
	
	 @Test
	 public void testGetNumParticipants() throws BuddiesBillExceptions {
		 
		 assertEquals(0,buddies.getNumParticipants());
		 this.buddies.add(vivi);
		 assertEquals(1,buddies.getNumParticipants());
	 }

	@Test
	public void testBuddiesInt() {
		buddy = new Buddy[6];
		assertEquals(6, buddy.length);
	}

	@Test(expected = BuddiesBillExceptions.class)
	public void testAddArrayFull() throws BuddiesBillExceptions {

		this.buddies.add(vivi);
		this.buddies.add(pepe);
		this.buddies.add(juan);
		this.buddies.add(luis);
		this.buddies.add(javi);
		this.buddies.add(marta);
		this.buddies.add(laura);
		

	}

	@Test(expected = BuddiesBillExceptions.class)
	public void testAddRepeatName() throws BuddiesBillExceptions {

		this.buddies.add(vivi);
		this.buddies.add(vivi);

	}

	@Test
	public void testAddOk() throws BuddiesBillExceptions {

		assertEquals("", buddies.toString());
		this.buddies.add(pepe);
		assertEquals("pepe: 0,00\n", buddies.toString());

	}

	@Test
	public void testIsSameNameNotSame() throws BuddiesBillExceptions {

		this.buddies.add(pepe);
		assertEquals(null, buddies.isSameName(juan.getName()));
	}

	@Test 
	public void testIsSameNameSame() throws BuddiesBillExceptions {

		this.buddies.add(pepe);
		assertEquals(pepe.toString(), buddies.isSameName(pepe.getName()).toString());
	}

	@Test
	public void testRemoveOK() throws BuddiesBillExceptions {

		this.buddies.add(pepe);
		this.buddies.add(luis);
		this.buddies.add(vivi);
		assertEquals("pepe: 0,00\n" + "luis: 0,00\n" + "vivi: 0,00\n", buddies.toString());
		this.buddies.remove("luis");
		assertEquals("pepe: 0,00\n" + "vivi: 0,00\n", buddies.toString());

	}

	@Test(expected = BuddiesBillExceptions.class)
	public void testRemoveMoneyNotCero() throws BuddiesBillExceptions {

		this.buddies.add(antonio);
		this.buddies.remove("antonio");
	}

	@Test(expected = BuddiesBillExceptions.class)
	public void testRemoveNotInList() throws BuddiesBillExceptions {
		this.buddies.remove("juan");

	}

	@Test
	public void testBalance() throws BuddiesBillExceptions {

		this.buddies.add(antonio);
		this.buddies.add(javi);
		assertEquals("21,00", this.buddies.balance());
	}
	
	@Test
	public void testNameBuddy() throws BuddiesBillExceptions {
		
		this.buddies.add(antonio);
		assertEquals("antonio", this.buddies.nameBuddy(0));
		
	}
	
	@Test
	public void testMoneyBuddy() throws BuddiesBillExceptions {
		
		this.buddies.add(antonio);
		assertEquals(1,0f, this.buddies.moneyBuddy(0));
		
	}
	
	@Test
	public void testSettleUpCasos3_5() throws BuddiesBillExceptions {
		this.buddies.add(laura);
		this.buddies.add(vivi);
		this.buddies.add(pepe);
		this.buddies.add(juan);
		this.buddies.add(luis);
		//this.buddies.addMovement(coche, 10, juan);
		assertEquals("Payment from vivi to laura: 2.0\n"+"Payment from pepe to laura: 2.0\n"+"Payment from juan to laura: 2.0\n"+"Payment from luis to laura: 2.0\n", this.buddies.settleUp());
	}
	
	@Test
	public void testSettleUpCasos2_4_5() throws BuddiesBillExceptions { 
		this.buddies.add(laura); //juan
		this.buddies.add(pepe);
		this.buddies.add(luis);
		this.buddies.add(javi);
		this.buddies.add(vivi);
		//this.addMovement(coche, 10, juan);
		//this.buddies.addMovement(cena, 20, javi);
		assertEquals("Payment from pepe to laura: 4.0\n"+"Payment from pepe to javi: 2.0\n"+"Payment from luis to javi: 6.0\n"+"Payment from vivi to javi: 6.0\n", this.buddies.settleUp());
	}

	@Test
	public void testSettleUpCasos1_2_6() throws BuddiesBillExceptions { 
		this.buddies.add(juan);
		this.buddies.add(laura);//pepe
		this.buddies.add(luis);
		this.buddies.add(vivi);
		this.buddies.add(javi);
		//this.buddies.addMovement(coche, 10, pepe);
		//this.buddies.addMovement(cena, 20, antonio);
		assertEquals("Payment from juan to laura: 4.0\n"+"Payment from juan to javi: 2.0\n"+"Payment from luis to javi: 6.0\n"+"Payment from vivi to javi: 6.0\n", this.buddies.settleUp());
	}
	@Test
	public void testToString() throws BuddiesBillExceptions {
		buddies.add(pepe);
		assertEquals("pepe: 0,00\n", buddies.toString());
	}
}
