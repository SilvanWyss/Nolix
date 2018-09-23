//package declaration
package ch.nolix.core.endPoint5;

//package-visible class
/**
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
final class Protocol {
		
	//message headers
	public static final String COMMANDS = "Commands";
	public static final String DATA_REQUEST = "DataRequest";
	
	//reply headers
	public static final String DONE = "Done";
	public static final String DATA = "Data";
	public static final String ERROR = "Error";

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Protocol() {}
}
