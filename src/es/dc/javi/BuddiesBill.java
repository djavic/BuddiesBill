package es.dc.javi;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.LogManager;

/**
 * @author 5K
 */
public class BuddiesBill {
	/**
	 * Atributo para la generacion de trazas en un fichero de registro.
	 */
	private static Logger logger = LogManager.getLogger(BuddiesBill.class);

	/**
	 * Metodo principal del programa.
	 * 
	 * @param args
	 *            Corresponden a al nombre del evento, numero maximo de
	 *            participantes y numero maximo de movimientos o gastos por parte de
	 *            los participantes del evento.
	 */
	public static void main(String[] args) {

		String eventName;
		int maxParticipants;
		int maxMovements;

		try {
			eventName = args[0];
			maxParticipants = Integer.parseInt(args[1]);
			maxMovements = Integer.parseInt(args[2]);

			if (args.length != 3) {
				logger.warn("Sintaxis: Numero de argumentos no valido. (Num maximo de args: " + maxParticipants + ").");
				System.out.println("Syntaxis: Numero de argumentos no valido");
			} else if (maxParticipants > 0 && maxMovements > 0) {

				Event event = new Event(eventName, maxParticipants, maxMovements);
				logger.info("Creado Evento = [Nombre: " + eventName + ", NumMaxParticipantes: " + maxParticipants
						+ ", MaxMovements: " + maxMovements + "]");
				TextUI ui = new TextUI(event);
				logger.info("Creada interfaz de texto.");
				ui.start();
				logger.info("Inicio del metodo start, menu interartivo con el usuario.");

			}

		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}

	}

}
