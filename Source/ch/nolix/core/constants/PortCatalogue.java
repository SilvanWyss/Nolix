//package declaration
package ch.nolix.core.constants;

//own import
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;

//class
/**
 * Of the {@link PortCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 */
public final class PortCatalogue {
	
	//constants
	public static final int MIN_PORT = 0;
	public static final int FTP_PORT = 20;
	public static final int SMTP_PORT = 25;
	public static final int HTTP_PORT = 80;
	public static final int HTTPS_PORT = 443;
	public static final int DE_FACTO_HTTP_PORT = 8080;
	public static final int MAX_PORT = 65535;
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link PortCatalogue} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private PortCatalogue() {
		throw new UninstantiableClassException(PortCatalogue.class);
	}
}
