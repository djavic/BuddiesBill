package es.dc.javi;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.LogManager;

/**
 * Clase interfaz. Encargada de recibir los mensajes de interaccion por parte de
 * los usuarios y tratar la informacion.
 * 
 * @author 5K
 *
 */
public class TextUI {

	/**
	 * Atributo que representa la interaccion con un evento.
	 */
	private Event event;
	/**
	 * Atributo para registrar las traza de los mensajes de la aplicacion.
	 */
	private static Logger logger = LogManager.getLogger(TextUI.class);

	/**
	 * Constructor de la clase.
	 * 
	 * @param event
	 *            Argumento que representa un evento.
	 */
	public TextUI(Event event) {
		this.event = event;

		logger.debug("Creada interfaz de texto.");
	}

	/**
	 * Metodo que ejecuta indefinidamente un menu con opciones (incluida una de
	 * salida) para la interaccion con el usuario por consola. Permite operaciones
	 * de 1 - Aniadir participante 2 - Borrar participante 3 - Aniadir pago 4 -
	 * Quitar pago 5 - Mostrar informacion 6 - Terminar evento 7 - Salir
	 * 
	 */
	public void start() {
		int opcionMarcada = 0;

		logger.debug("Inicio del menu interactivo");

		do {
			try {

				System.out.println("---\nEvent: " + event.getEventName());
				System.out.println("People:");
				System.out.println(event.showInfoBuddies());
				System.out.print("");
				System.out.println("Movements:");
				System.out.println(event.showInfoBills());
				System.out.print("");
				System.out.println("Balance: ");
				System.out.println(event.showBalance());

				System.out.println("\n---");

				showMenu();

				opcionMarcada = Teclado.readInteger();
				logger.info("Opcion marcada del menu: " + opcionMarcada);
				switch (opcionMarcada) {

				case 1: /* Opcion aniadir un participante. */
					String name;

					System.out.println("Name of the buddy to add: ");
					name = Teclado.readString();

					event.addBuddy(name);
					logger.info("Introducido Buddy[Nombre: " + name + "]");
					break;

				case 2: /* Opcion borrar participante */
					String nameToRemove;
					System.out.println("Name of the buddy to remove: ");
					System.out.println(event.showInfoBuddies());
					nameToRemove = Teclado.readString();
					event.removeBuddy(nameToRemove);
					logger.info("Eliminado Buddy[Nombre: " + nameToRemove + "]");
					break;

				case 3: /* Opcion aniadir pago */
					String movement;
					float money;

					String buddyName;

					System.out.println("What: ");
					movement = Teclado.readString();

					System.out.println("How much: ");
					money = Teclado.readFloat();

					System.out.println("Who: ");
					buddyName = Teclado.readString();

					event.addMovement(movement, money, buddyName);
					logger.info("Aniadido pago Bill[NombreGasto: " + movement + ", Importe: " + money
							+ ", Emitido por: " + buddyName + "]");
					break;

				case 4: /* Opcion quitar pago */
					System.out.println("Name of the movement to remove: ");
					System.out.println(event.showInfoBills());
					name = Teclado.readString();

					event.removeMovement(name);
					logger.info("Eliminado movement = " + name);
					break;

				case 5: /* Opcion mostrar informacion */
					System.out.println("People: ");
					System.out.println(event.showInfoBuddies());
					System.out.println("Movements: ");
					System.out.println(event.showInfoBills());
					break;
				case 6: /* Opcion settleUp */
					System.out.println(event.settleUp());
					break;
				case 7: /* Opcion salir de la aplicacion. */
					System.out.println("Bye!");
					logger.info("Se ha salido de la aplicacion.");
					break;
				default:
					System.out.println("Invalid option.");
					logger.warn("Marcada opcion invalida.");

				}
			} catch (BuddiesBillExceptions e) {
				System.out.println(e.getMessage());
				logger.debug("Exception msg: " + e.getMessage());

			}

		} while (0 < opcionMarcada && opcionMarcada < 7);

	}

	private void showMenu() {

		System.out.println("Select an option: ");
		System.out.println(" 1 - Add buddy");
		System.out.println(" 2 - Remove buddy");
		System.out.println(" 3 - Add movement");
		System.out.println(" 4 - Remove movement");
		System.out.println(" 5 - Show summary");
		System.out.println(" 6 - Settle up");
		System.out.println(" 7 - Exit");

		logger.debug("Mostrado menu interactivo");

	}

}
