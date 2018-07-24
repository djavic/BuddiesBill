package es.dc.javi;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * Clase encargada de recoger las excepciones de la aplicacion.
 * @author 5K
 *
 */
public class BuddiesBillExceptions extends Exception {

	public BuddiesBillExceptions(String message) {
		super(message);
	}

}
