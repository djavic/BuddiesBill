package es.dc.javi;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.LogManager;

/**
 * Clase encargada de la gestion del objeto participante (o Buddy). Aloja
 * informacion relativa al nombre del participante (o Buddy) y los gastos del
 * mismo en el evento.
 * 
 * @author 5K
 */
public class Buddy {
	/**
	 *  Nombre del participante (o Buddy).
	 */
	private String name;
	/**
	 *  Gastos del participante (o Buddy) en su participacion en el
	 *            evento.
	 */
	private float expentMoney;
	/**
	 * Atributo para generar los trazas dentro de un archivo de registro.
	 */
	private static Logger logger = LogManager.getLogger(Buddy.class);
	
	/**
	 * Constructor de la clase. Recibe como paramtro un objeto de tipo participante
	 * (o Buddy).
	 * 
	 * @param participant
	 */
	public Buddy(Buddy participant) {

		this.name = participant.getName();
		this.expentMoney = participant.getExpentMoney();

		logger.debug("Creado objeto Buddy[Nombre: " + participant.getName() + "].");
	}

	/**
	 * Constructor de la clase.
	 * 
	 * @param name
	 *            Nombre del participante (o Buddy). Objeto de tipo String.
	 * @param money
	 *            Importe de gasto del participante (o Buddy). Objeto de tipo int.
	 * @throws BuddiesBillExceptions
	 *             Recoge las excepciones que se puedan producir.
	 */
	public Buddy(String name, float money) throws BuddiesBillExceptions {
		this.name = name;
		this.expentMoney = money;

		logger.debug("Creado objeto Buddy[Nombre: " + name + ", Money: " + expentMoney + "].");
	}

	/**
	 * Metodo get devuelve el valor del atributo name de la clase.
	 * 
	 * @return El valor de name.
	 */
	public String getName() {

		return this.name;

	}

	/**
	 * Metodo get que devuelve el valor del atributo expectMoney de la clase.
	 * 
	 * @return El valor de expendMoney.
	 */
	public float getExpentMoney() {

		return this.expentMoney;
	}
	/**
	 * 
	 * @param value
	 * 
	 * Metodo para actualizar el dinero
	 */
	public void setExpentMoney(float value) {
		
		this.expentMoney = value;
	}

	/**
	 * Metodo que simula el pago que ha generado un participante. Aumentando el
	 * valor de su gasto total respecto del evento.
	 * 
	 * @param money
	 *            Cantidad monetaria que ha pagado y desea sumar a su cantidad total
	 *            aportada al evento.
	 * @throws BuddiesBillExceptions
	 *             Excepciones que se puedan producir.
	 */
	public void pay(float money) throws BuddiesBillExceptions {
		this.expentMoney = getExpentMoney() + money;
		logger.debug("Creado pago [Cantidad: " + money + "].");
	}

	/**
	 * Metodo que simula la eliminacion de un pago de un participante. Restandole el
	 * valor de la eliminacion del pago a la cantidad total que lleva aportada ese
	 * participante (o Buddy) en el evento.
	 * 
	 * @param money
	 *            Cantidad monetaria que desea restar del pago total que ha
	 *            realizado totalmente en en evento.
	 */
	public void receive(float money) {
		this.expentMoney = getExpentMoney() - money;
		logger.debug("Eliminacion de pago [Cantidad: " + money + "].");
	}

	/**
	 * Metodo to String.
	 */
	public String toString() { // Devolvemos los datos del objeto

		return getName() + ": " + String.format("%.2f", getExpentMoney()) + "\n";

	}

}
