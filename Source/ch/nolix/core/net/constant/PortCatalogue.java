//package declaration
package ch.nolix.core.net.constant;

//class
/**
 * Of the {@link PortCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class PortCatalogue {
	
	//constants
	public static final int MIN_PORT = 0;
	public static final int FTP_PORT = 20;
	public static final int SMTP_PORT = 25;
	public static final int HTTP_PORT = 80;
	public static final int HTTPS_PORT = 443;
	public static final int MSSQL_PORT = 1433;
	public static final int MAX_PORT = 65535;
	
	//constructor
	/**
	 * Prevents that an instance of the {@link PortCatalogue} can be created.
	 */
	private PortCatalogue() {}
}
