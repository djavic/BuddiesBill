package es.dc.javi;

import java.text.DecimalFormat;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class Event {
	/**
	 * Variable que almacena en nombre del evento.
	 */
	private String eventName;
	/**
	 * Variable (es un agregado) para almacenar los participantes.
	 */
	private Buddies buddies;
	/**
	 * Variable (es un agregado) para almacenar las facturas.
	 */
	private Bills bills;
	/**
	 * Atributo para generar los trazas dentro de un archivo de registro.
	 */
	private static Logger logger = LogManager.getLogger(Event.class);

	/**
	 * Constructor de la clase.
	 * 
	 * @param eventName
	 *            Nombre del evento.
	 * @param maxParticipants
	 *            Numero max de participantes
	 * @param maxMovements
	 *            Numero max de movimientos.
	 */
	public Event(String eventName, int maxParticipants, int maxMovements) {

		this.eventName = eventName;
		buddies = new Buddies(maxParticipants);
		bills = new Bills(maxMovements);

	}

	/**
	 * Metodo get para devolver el valor del atributo name.
	 * 
	 * @return Valor de name.
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * Metodo que aniade un participante del evento, siempre que haya hueco.
	 * 
	 * @param name
	 *            Nombre del participante.
	 * @throws BuddiesBillExceptions
	 *             Excepcion que se genera.
	 */
	public void addBuddy(String name) throws BuddiesBillExceptions { // aniadimos buddies

		Buddy participant = new Buddy(name, 0.0f);
		buddies.add(participant);

	}

	/**
	 * Elimina un participante del evento, siempre que exita ese participante.
	 * 
	 * @param name
	 *            Nombre del participante.
	 * @throws BuddiesBillExceptions
	 *             Excepcion que se pueda generar.
	 */
	public void removeBuddy(String name) throws BuddiesBillExceptions { // borramos buddies

		buddies.remove(name);

	}

	/**
	 * Agregar gasto al evento.
	 * 
	 * @param movementName
	 *            Nombre del gasto.
	 * @param money
	 *            Importe del gasto.
	 * @param buddyName
	 *            Nombre del participante que lo ha llevado a cabo.
	 * @throws BuddiesBillExceptions
	 *             Excepcion que se pueda generar.
	 */
	public void addMovement(String movementName, float money, String buddyName) throws BuddiesBillExceptions {

		Buddy person;

		person = buddies.isSameName(buddyName);
		Bill payment = new Bill(movementName, money, person);

		if (person == null) {
			throw new BuddiesBillExceptions("The buddy " + buddyName + " is not in the party");
		} else {
			bills.add(payment); // Aniadimos la factura
			person.pay(money);

		}

	}

	/**
	 * Elimina el un movimiento o gasto que se ha producido en el evento.
	 * 
	 * @param name
	 *            Nombre del evento a eliminar.
	 * @throws BuddiesBillExceptions
	 *             Excepcion que se pueda generar.
	 */
	public void removeMovement(String name) throws BuddiesBillExceptions {

		Bill aux = bills.isSameBill(name);
		
		if(aux == null) {
			throw new BuddiesBillExceptions("Movement not included");
		}

		aux.getWho().receive(aux.getHowMuch());
		bills.remove(aux);

	}

	/**
	 * Muestra la informacion del evento.
	 * 
	 * @return Cadena de caracteres que contiene la informacion del evento.
	 */
	public String showInfoBuddies() {

		return buddies.toString();

	}

	/**
	 * Metodo que devuelve una cadena de caracteres con la informacion de las
	 * facturas.
	 * 
	 * @return La cadena de caracteres con la informacion de las facturas.
	 */
	public String showInfoBills() {

		return bills.toString();

	}

	/**
	 * Muestra el balance economico del evento siendo este devuelto en formato de
	 * una cadena de caracteres.
	 * 
	 * @return La cadena de caracteres con el balance economico del evento.
	 */
	public String showBalance() {
		return buddies.balance();
	}

	/**
	 * Metodo que lleva a cabo la realizacion del los pagos de quien debe a quien
	 * dinero.
	 * 
	 * @return Una cadena de caracteres que representa que participante le tiene que
	 *         quien y cuanto.
	 * @throws BuddiesBillExceptions
	 *             Excepciones que se puedan producir.
	 */
	public String settleUp() throws BuddiesBillExceptions {

		return buddies.settleUp();
	}

}
