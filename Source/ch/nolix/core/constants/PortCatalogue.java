//package declaration
package ch.nolix.core.constants;

//class
/**
 * Of the {@link PortCatalogue} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public final class PortCatalogue {
	
	//constants
	public static final int MIN_PORT = 0;
	public static final int FTP_PORT = 20;
	public static final int SMTP_PORT = 25;
	public static final int HTTP_PORT = 80;
	public static final int HTTPS_PORT = 443;
	public static final int MAX_PORT = 65535;
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link PortCatalogue} can be created.
	 */
	private PortCatalogue() {}
}
