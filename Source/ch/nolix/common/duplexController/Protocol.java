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
	public final static String COMMANDS = "Commands";
	public final static String DATA_REQUEST = "DataRequest";
	
	//incoming message headers
	public final static String DONE = "Done";
	public final static String DATA = "Data";
	public final static String ERROR = "Error";

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Protocol() {}
}
