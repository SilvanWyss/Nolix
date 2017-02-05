/*
 * file:	Protocol.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	20
 */

//package declaration
package ch.nolix.common.duplexController;

//package-visible class
final class Protocol {
		
	//outgoing message headers
	public static final String COMMANDS = "Commands";
	public static final String DATA_REQUEST = "DataRequest";
	
	//incoming message headers
	public static final String DONE = "Done";
	public static final String DATA = "Data";
	public static final String ERROR = "Error";

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Protocol() {}
}
