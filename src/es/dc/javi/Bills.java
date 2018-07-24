package es.dc.javi;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.LogManager;

/**
 * Clase agregada de facturas (o Bill). Su mision es el control y gestion de las
 * facturas que realizan los participantes (o Buddies).
 * 
 * @author 5K
 * 
 */
public class Bills {

	/**
	 * Objeto de tipo de array que almacena objetos de tipo facturas (o Bills).
	 */
	private Bill[] bill;
	/**
	 * Variable que almacena la posicion en la que se debe insertar el siguiente
	 * elemento del array.
	 */
	private int next;
	/**
	 * Atributo para registrar los eventos de la clase en un archivo de resgistro de
	 * eventos.
	 */
	private static Logger logger = LogManager.getLogger(Bills.class);

	/**
	 * Constructor de la clase.
	 * 
	 * @param maxMovements
	 *            Numero maximo de movimientos del array. Tambien es el numero
	 *            maximo de objetos de tipo factura (o Bills) que puede almacenar la
	 *            estructura.
	 */
	public Bills(int maxMovements) { // Constructor
		this.next = 0;
		bill = new Bill[maxMovements];

		logger.info("Creacion del objeto tipo Array: Bills = [Tamanio: " + maxMovements + "]");
	}

	/**
	 * Metodo get que devuelve el numero maximo de movimientos.
	 * 
	 * @return Numero maximo de movimientos.
	 */
	public int getNumMovements() {
		return this.next;
	}

	/**
	 * Metodo que nos permite aniadir una factura, siempre y cuando no este repetida
	 * y el array no este lleno
	 * 
	 * @param payment
	 *            Factura (o Bill) que se desea aniadir.
	 * @throws BuddiesBillExceptions
	 *             Excepciones que se procudcen si no se puede aniadir. En el caso
	 *             de que este llena la estructura o si se realiza un pogo que se
	 *             igual.
	 * 
	 * 
	 */

	public void add(Bill payment) throws BuddiesBillExceptions {
		if (bill.length == next) {
			/* Info logs */
			logger.warn("Lista de movimientos llena: Bill = [Nombre: " + payment.getName() + ",Importe: "
					+ payment.getHowMuch() + "]" + " no se ha insertado.");
			/* Excepcion */
			throw new BuddiesBillExceptions("People Error: No more room left");
		} else if (isSameBill(payment.getName()) != null) {
			/* Info logs */
			logger.warn("Movimiento Repetido: Bill = [Nombre: " + payment.getName() + ",Importe: "
					+ payment.getHowMuch() + "]" + " no se ha insertado.");
			/* Excepcion */
			throw new BuddiesBillExceptions("Add payment error: Payment already included");
		} else {
			bill[next++] = new Bill(payment); // Aniadimos el objeto a la lista buddy
			logger.info("Movimiento Aniadido: Bill = [Nombre: " + payment.getName() + ",Importe: "
					+ payment.getHowMuch() + "]" + " no se ha insertado.");
		}
	}

	/**
	 * Metodo para buscar un objeto igualal pasado, si no lo encuentra devuelve
	 * null, y si lo encuentra devuelve el objeto del array
	 * 
	 * @param movement
	 * @return Null si no se encuentra el elemento, el objeto en caso de que si.
	 */

	public Bill isSameBill(String movementName) {
		Bill out = null;

		for (int i = 0; i < this.next; i++) {
			if (movementName.equals(bill[i].getName())) {
				out = bill[i];
				break;
			}
		}
		return out;
	}

	/**
	 * Metodo que permite borrar una factura del array, siempre y cuando este en el,
	 * si no devuelve una excepcion.
	 * 
	 * @param payment
	 *            Fatura (o Bill) que se quiere eliminar.
	 * @throws BuddiesBillExceptions
	 *             Posibles excepciones del programa: Que la factura que se desea
	 *             borrar no este en la lista.
	 */

	public void remove(Bill payment) throws BuddiesBillExceptions {

		int number = -1;
		int flag = 0;
		for (int i = 0; i < this.next; i++) {
			if (bill[i].getName().equals(payment.getName()) == true) {
				bill[i] = null;
				number = i;
				flag = 1;
			}
		}

		if (flag == 0) {
			logger.warn("Factura (o Bill) no existe: Bill = [Nombre: " + payment.getName() + ",Importe: "
					+ payment.getHowMuch() + "]" + " no se ha borrado nada.");
			throw new BuddiesBillExceptions("Movement not included");
		}
		// Reordenamos el array para que no se encuentren nulls intermedios en la
		// estrucutura.
		reorder(number);
	}

	/**
	 * Metodo privado para reordenar el array una vez que se ha borrado un elemeto,
	 * esto hace que no queden huecos por en medio del array
	 * 
	 * @param number
	 *            Numero a partir del cual se quiere reordenar. Hace al algoritmo
	 *            mas eficiente.
	 */
	private void reorder(int number) {
		logger.debug("Se ha reordenado el array de la clase Bills.");
		for (int i = number; i < bill.length; i++) {
			if (i < 4) {
				bill[i] = bill[i + 1];
			} else {

				bill[i] = null;
			}
		}
		this.next--;
	}

	/**
	 * Metodo toString de la clase.
	 */
	public String toString() {

		StringBuilder exit = new StringBuilder();

		for (int i = 0; i < this.next; i++) {
			exit.append(bill[i]);

		}

		return exit.toString();
	}
}
