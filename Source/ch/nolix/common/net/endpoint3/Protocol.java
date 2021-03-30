//package declaration
package ch.nolix.common.net.endpoint3;

//class
/**
 * Of the {@link Protocol} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 */
final class Protocol {
	
	//constants
	public static final String COMMANDS_HEADER = "Commands";
	public static final String DATA_REQUEST_HEADER = "DataRequest";
	public static final String DONE_HEADER = "Done";
	public static final String DATA_HEADER = "Data";
	public static final String ERROR_HEADER = "Error";
	
	//constructor
	/**
	 * Avoids that an instance of the {@link Protocol} can be created.
	 */
	private Protocol() {}
}
