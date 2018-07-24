package es.dc.javi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BillsTest {

	private Bills bills;

	private Bill cena;
	private Bill comida;
	private Bill gasolina;
	private Bill avion;
	private Bill hotel;
	private Bill gasolina2;

	private Buddy vivi;
	private Buddy pepe;
	private Buddy juan;
	private Buddy javi;

	@Before
	public void setUp() throws BuddiesBillExceptions {

		this.bills = new Bills(5);

		this.vivi = new Buddy("vivi", 0.0f);
		this.pepe = new Buddy("pepe", 0.0f);
		this.juan = new Buddy("juan", 0.0f);
		this.javi = new Buddy("javi", 0.0f);

		this.cena = new Bill("cena", 5.65f, juan);
		this.comida = new Bill("comida", 8.97f, pepe);
		this.gasolina = new Bill("gasolina", 60f, vivi);
		this.avion = new Bill("avion", 2000.33f, javi);
		this.hotel = new Bill("hotel", 60.71f, vivi);
		this.gasolina2 = new Bill("gasolina2", 65f, vivi);
	}

	@Test
	public void testGetNumMovements() throws BuddiesBillExceptions {

		assertEquals(0, bills.getNumMovements());
		this.bills.add(cena);
		assertEquals(1, bills.getNumMovements());
	}

	@Test(expected = BuddiesBillExceptions.class)
	public void testAddArrayFull() throws BuddiesBillExceptions {

		this.bills.add(cena);
		this.bills.add(comida);
		this.bills.add(gasolina);
		this.bills.add(avion);
		this.bills.add(hotel);
		this.bills.add(gasolina2);
	}

	@Test
	public void testAddOk() throws BuddiesBillExceptions {
		assertEquals("", bills.toString());
		this.bills.add(comida);
		assertEquals("comida - 8,97 pagado por pepe\n", bills.toString());
	}

	@Test(expected = BuddiesBillExceptions.class)
	public void testAddRepeatedEvent() throws BuddiesBillExceptions {

		this.bills.add(cena);
		this.bills.add(cena);
	}

	@Test
	public void testRemoveOK() throws BuddiesBillExceptions {

		this.bills.add(cena);
		this.bills.add(comida);
		assertEquals("cena - 5,65 pagado por juan\ncomida - 8,97 pagado por pepe\n", bills.toString());
		this.bills.remove(comida);
		assertEquals("cena - 5,65 pagado por juan\n", bills.toString());

	}

	@Test(expected = BuddiesBillExceptions.class)
	public void testRemoveNotInList() throws BuddiesBillExceptions {
		this.bills.remove(cena);

	}

	@Test
	public void testToString() throws BuddiesBillExceptions {
		this.bills.add(cena);
		assertEquals("cena - 5,65 pagado por juan\n", bills.toString());
	}

}
