package es.dc.javi;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.LogManager;

/**
 * Clase que almacena la una factura (o Bill) producida en un evento por uno de
 * los participantes (o Buddies).
 * 
 * @author 5K
 * 
 */
public class Bill {
	
	/**
	 * Atributo que almacena el nombre de la factura (o Bill).
	 */
	private String name;
	/**
	 * Atributo que almacena la cantidad del importe de la factura (o Bill).
	 */
	private float howMuch;
	/**
	 * Atributo que almacena el paticipante que ha realizado la factura (o Bill).
	 */
	private Buddy who;
	/**
	 * Atributo para registrar los eventos de la clase en un archivo de resgistro de
	 * eventos.
	 */
	private static Logger logger = LogManager.getLogger(Bill.class);

	/**
	 * Constructor que recibe como parametro un constructor de tipo Bill.
	 * 
	 * @param note
	 *            Argumento de tipo Bill.
	 */
	public Bill(Bill note) {// Constructor que recibe el objeto Bill

		this.name = note.getName();
		this.howMuch = note.getHowMuch();
		this.who = note.getWho();

		logger.info("Creacion del objeto tipo: Bill = [Nombre: " + this.name + ",Importe: " + this.howMuch + "]");
	}

	/**
	 * Constructor de la clase.
	 * 
	 * @param name
	 *            Variable de tipo String que almacena el nombre del la factura.
	 * @param money
	 *            Cantidad monetaria del importe de la factura.
	 * @param participant
	 *            Participante (o tambien llamado Buddy) que ha pagado la factura.
	 * @throws BuddiesBillExceptions
	 *             Excepcion que se produce si ha habido algun error.
	 */
	public Bill(String name, float money, Buddy participant) throws BuddiesBillExceptions {
		this.name = name;
		this.howMuch = money;
		this.who = participant;

		logger.info("Creacion del objeto tipo: Bill = [Nombre: " + this.name + ",Importe: " + this.howMuch + "]");
	}

	/**
	 * Metodo get que devuelve el nombre de la factura (o Bill).
	 * 
	 * @return
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * Metodo get que devuelve el importe de la factura (o Bill).
	 * 
	 * @return
	 */
	public float getHowMuch() {

		return this.howMuch;
	}

	/**
	 * Metodo get que devuelve el participante (o Buddy) que ha ralizado la factura
	 * (o Bill).
	 * 
	 * @return
	 */
	public Buddy getWho() {

		return this.who;
	}

	/**
	 * Metodo que devuelve un objeto de tipo String con el siguiente formato de
	 * ejemplo: "Cena - 1,00 pagado por Rose\n"
	 */
	public String toString() { // Devolvemos los datos del objeto

		return getName() + " - " + String.format("%.2f", getHowMuch()) + " pagado por " + who.getName() + "\n";
	}

}
