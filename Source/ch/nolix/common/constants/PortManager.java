//package declaration
package ch.nolix.common.constants;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
public final class PortManager {
	
	//ports
	public final static int MIN_PORT = 0;
	public final static int FTP_PORT = 20;
	public final static int SMTP_PORT = 25;
	public final static int HTTP_PORT = 80;
	public final static int HTTPS_PORT = 443;
	public final static int MAX_PORT = 65535;

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private PortManager() {}
}
