package es.dc.javi;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.text.DecimalFormat;

import javax.swing.text.html.parser.ParserDelegator;

import org.apache.logging.log4j.LogManager;

/**
 * Clase agregada de participantes (o Buddies). Su mision es el control y
 * gestion de los partipantes en el evento (o Buddies).
 * 
 * @author 5K
 */
public class Buddies {

	/**
	 * Estrucutura de tipo Array que almacena los participantes (o Buddies) de la
	 * aplicacion.
	 */
	private Buddy[] buddy;
	/**
	 * Variable que indica la posicion siguiente donde se inserta el siguiente
	 * elemento.
	 */
	private int next;
	/**
	 * Atributo para registrar los eventos de la clase en un archivo de resgistro de
	 * eventos.
	 */
	private static Logger logger = LogManager.getLogger(Buddies.class);

	/**
	 * Constructor de la clase.
	 * 
	 * @param maxParticipants
	 *            Numero maximo de participantes que admite el array y tambien el
	 *            evento.
	 */
	public Buddies(int maxParticipants) { /** Constructor **/

		this.next = 0;
		buddy = new Buddy[maxParticipants];

		logger.info("Creacion del objeto Buddies (Num maximo de participantes: " + maxParticipants + ").");
	}

	/**
	 * Metodo que devuelve el numero maximo de participantes.
	 * 
	 * @return Numero maximo de participantes.
	 */
	public int getNumParticipants() {
		return this.next;
	}

	/**
	 * Metodo que anade un participante a la estrucutra de tipo Array. Genera
	 * excepciones si no lo consigue en ciertos casos.
	 * 
	 * @param participant
	 *            Participante (o Buddy) a aniadir.
	 * @throws BuddiesBillExceptions
	 *             Excepciones que se generan si no se anade satisfactoriamente.
	 *             Casos en los que el Array este lleno o se intente aniadir un
	 *             participante que se encuentre ya en el Array.
	 */
	public void add(Buddy participant) throws BuddiesBillExceptions {/** Metodo para aniadir participante **/

		if (buddy.length == this.next) {/* Comprobamos que no se supere el max de participantes */

			logger.error("Lista de participantes llena");
			throw new BuddiesBillExceptions("People Error: No more room left");

		} else if (isSameName(participant.getName()) != null) {/* buscamos a ver si hay algun particupante repetido */

			logger.error("Participante " + participant.getName() + " repetido. ");
			throw new BuddiesBillExceptions(
					"Add people error: Buddy " + participant.getName() + " already in the party");

		} else {/* Se aniade el participante (o Buddy) a al Array. */

			buddy[this.next++] = new Buddy(participant);// Add Buddy
			logger.info("Add participant: Buddy=[" + participant.getName() + "].");
		}
	}

	/**
	 * Metodo para buscar si existe otro objeto con el mismo nombre al pasado por
	 * argumento, si existe retornamos su posicion, si no un -1.
	 * 
	 * @param name
	 * @return
	 */
	public Buddy isSameName(String name) {
		Buddy out = null;

		for (int i = 0; i < this.next; i++) {

			if (name.equals(buddy[i].getName())) {
				out = buddy[i];
			}
		}
		return out;
	}

	/**
	 * Metodo que elimina un participante (o Buddy) del array. Produce excepciones
	 * en ciertos casos.
	 * 
	 * @param name
	 *            Nombre del participante (o Buddy) que se quiera eliminar del
	 *            Array.
	 * @throws BuddiesBillExceptions:
	 *             Eliminacion de un participante (o Buddy) que tenga gastos a su
	 *             nombre. Caso en el que se quiera elimniar un participante (o
	 *             Buddy) que no este en el Array.
	 */
	public void remove(String name) throws BuddiesBillExceptions { /** Metodo para borrar participantes **/

		int number = -1;
		int flag = 0;

		for (int i = 0; i < this.next; i++) {

			if (buddy[i].getName().equals(name) == true && buddy[i] != null && buddy[i].getExpentMoney() == 0) {

				buddy[i] = null;
				logger.info("El participante " + name + " ha sido borrado.");
				number = i; // guardamos en number la posicon del elemento borrado
				flag = 1;

			} else if (buddy[i].getName().equals(name) && buddy[i] != null && (buddy[i].getExpentMoney() > 0)) {
				flag = 1;
				logger.error("El participante " + name
						+ " tiene gastos a su nombre, borrelos antes de eliminar el participante.");
				throw new BuddiesBillExceptions(
						"The buddy " + name + " can't be removed, please remove his movements first");

			}

		}

		if (flag == 0) {
			logger.error("El participante " + name + " que desea borrar no esta en la lista.");
			throw new BuddiesBillExceptions("The buddy " + name + " is not in the party");
		}
		reorder(number);
	}

	/**
	 * Metodo para reordenar el array y que no se encuentren posiciones null entre
	 * dos objetos creados.
	 * 
	 * @param number
	 *            Posicion del array a partir de la cual se quiere borrar. Da lugar
	 *            a un reordenado mas eficiente.
	 */
	private void reorder(int number) {/** Metodo para que que el array siempre este ordenado **/
		for (int i = number; i < next - 1; i++) {
			/**
			 * reordenamos empezando por la posicion del elemento borrado
			 **/

			buddy[i] = buddy[i + 1];
		}

		this.next--;

	}

	/**
	 * Metodo que calcula el balance de los gastos.
	 * 
	 * @return Balance total de gastos de la aplicacion.
	 */
	public String balance() {

		float balance = 0.0f;

		for (int i = 0; i < this.next; i++) {

			balance += buddy[i].getExpentMoney();
		}
		return String.format("%.2f", balance);
	}

	/**
	 * Metodo necesario para la ejecucion del algoritmo SettleUp. Devuelve el nombre
	 * de un participante del Array en funcion de la posicion que se le pase como
	 * parametro.
	 * 
	 * @param pos
	 *            Posicion del Array.
	 * @return
	 */
	public String nameBuddy(int pos) {

		return buddy[pos].getName();
	}

	/**
	 * Metodo necesario para la ejecucion del algoritmo SettleUp. Devuelve el dinero
	 * total aportado por un participante que se encuentre en el Array. Se busca en
	 * funcion de la posicion que se le pase por parametro.
	 * 
	 * @param pos
	 *            Posicion a buscar.
	 * @return
	 */
	public float moneyBuddy(int pos) {

		return buddy[pos].getExpentMoney();
	}

	public String settleUp() throws BuddiesBillExceptions {
		// Array de dinero pagado por cada uno
		float[] pagado = new float[getNumParticipants()];

		// Array de nombres
		String[] names = new String[getNumParticipants()];

		// Array donde se van a igualar los pagos para que todos sean el mismo
		float[] state = new float[getNumParticipants()];

		float media = 0.0f;
		DecimalFormat df = new DecimalFormat("#.#");

		StringBuffer mensajesSettup = new StringBuffer();

		for (int i = 0; i < getNumParticipants(); i++) {

			pagado[i] = moneyBuddy(i);
			names[i] = nameBuddy(i);

			state[i] = media - pagado[i];

		}

		media = (float) Double.parseDouble(df.format(sum(pagado) / pagado.length).replaceAll(",", "."));

		for (int i = 0; i < getNumParticipants(); i++) {

			state[i] = media - pagado[i];

		}

		float aux = 0;
		while (!isFinish(state)) {
			for (int i = 0; i < state.length; i++) {
				for (int j = i + 1; j < state.length; j++) {

					if (state[i] != 0 && state[j] != 0) {
						if (state[i] > Math.abs(state[j]) && state[j] < 0) {
							mensajesSettup.append(
									"Payment from " + names[i] + " to " + names[j] + ": " + Math.abs(state[j]) + "\n");
							logger.info("Pago: C1	" + names[i] + " paga a " + names[j] + ": " + Math.abs(state[j]));
							aux = state[i] + state[j];
							state[i] = aux;
							state[j] = 0;
							isSameName(names[i]).setExpentMoney(media);
							isSameName(names[j]).setExpentMoney(media);

						} else if (state[i] < Math.abs(state[j]) && state[j] < 0 && state[i] > 0) {
							mensajesSettup
									.append("Payment from " + names[i] + " to " + names[j] + ": " + state[i] + "\n");
							logger.info("Pago: C2	" + names[i] + " paga a " + names[j] + ": " + state[i]);
							aux = state[i] + state[j];
							state[i] = 0;
							state[j] = aux;
							isSameName(names[i]).setExpentMoney(media);
							isSameName(names[j]).setExpentMoney(media);

						} else if (Math.abs(state[i]) > state[j] && state[i] < 0 && state[j] > 0) {
							mensajesSettup
									.append("Payment from " + names[j] + " to " + names[i] + ": " + state[j] + "\n");
							logger.info("Pago: C3	" + names[j] + " paga a " + names[i] + ": " + state[j]);
							aux = state[i] + state[j];
							state[j] = 0;
							state[i] = aux;
							isSameName(names[i]).setExpentMoney(media);
							isSameName(names[j]).setExpentMoney(media);

						} else if (Math.abs(state[i]) < state[j] && state[i] < 0) {
							mensajesSettup.append(
									"Payment from " + names[j] + " to " + names[i] + ": " + Math.abs(state[i]) + "\n");
							logger.info("Pago: C4	" + names[j] + " paga a " + names[i] + ": " + Math.abs(state[i]));
							aux = state[i] + state[j];
							state[i] = 0;
							state[j] = aux;
							isSameName(names[i]).setExpentMoney(media);
							isSameName(names[j]).setExpentMoney(media);

						} else if (Math.abs(state[i]) == state[j] && state[i] < 0) {
							mensajesSettup
									.append("Payment from " + names[j] + " to " + names[i] + ": " + state[j] + "\n");
							logger.info("Pago: C5	" + names[j] + " paga a " + names[i] + ": " + state[j]);
							state[i] = 0;
							state[j] = 0;
							isSameName(names[i]).setExpentMoney(media);
							isSameName(names[j]).setExpentMoney(media);

						} else if (state[i] == Math.abs(state[j]) && state[j] < 0) {
							mensajesSettup
									.append("Payment from " + names[i] + " to " + names[j] + ": " + state[i] + "\n");
							logger.info("Pago: C6	" + names[i] + " paga a " + names[j] + ": " + state[i]);
							state[i] = 0;
							state[j] = 0;
							isSameName(names[i]).setExpentMoney(media);
							isSameName(names[j]).setExpentMoney(media);

						}

					}
				}
			}

		}
		return mensajesSettup.toString();
	}

	/**
	 * Metodo auxiliar que realiza el sumatorio de los valores de un array.
	 * 
	 * @param a
	 *            Array sobre el que se quiere sumar los valores del mismo.
	 * @return Suma de los valores del array.
	 */

	private float sum(float[] a) {
		float cont = 0;
		for (int i = 0; i < a.length; i++) {
			cont = cont + a[i];
		}
		return cont;
	}

	/**
	 * Metodo auxiliar que realiza el sumatorio en valor absoluto de los valores de
	 * un array.
	 * 
	 * @param a
	 *            Array sobre el que se quiere sumar los valores del mismo.
	 * @return Suma de los valores del array.
	 */

	private float sumAbs(float[] a) {
		float cont = 0;
		for (int i = 0; i < a.length; i++) {
			cont = cont + Math.abs(a[i]);
		}
		return cont;
	}

	/**
	 * Indica si ha acabado el algoritmo si todas las posiciones estan a cero.
	 * 
	 * @param state
	 * @return true si estan todas las posicienes a 0.0, false en caso contrario.
	 */

	private boolean isFinish(float[] state) {
		boolean estado = false;

		if (sumAbs(state) < 0.1) { // La condicion de parada no es 0, ya que en determiandos casos la reparticion
									// del dinero no da un numero justo, por ejemplo repartir 1 euro entre 3
									// participantes
			estado = true;

		}
		return estado;
	}

	/**
	 * Metodo toString.
	 */
	public String toString() { /** Metodo toString **/

		StringBuilder exit = new StringBuilder();

		for (int i = 0; i < this.next; i++) {
			exit.append(buddy[i]);
		}
		return exit.toString();
	}

}