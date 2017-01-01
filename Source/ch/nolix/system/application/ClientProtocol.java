/*
 * file:	Protocol.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	30
 */

//package declaration
package ch.nolix.system.application;

//package-visible class
final class ClientProtocol {
	
	//commands
	public final static String INVOKE_COMMAND = "Invoke";
	public final static String INVOKE_ON_ORIGIN_MACHINE_COMMAND = "InvokeOnOriginMachine";
	public final static String SET_READY_SIGNAL_COMMAND = "SetReadySignal";
	public final static String UPDATE_COMMAND = "Update";
	
	//requests
	public final static String TYPE_REQUEST = "Type";
	public final static String TARGET_APPLICATION_REQUEST = "TargetApplication";

	//codes
	public final static String STANDARD_CLIENT_CODE = "StandardClient";
	public final static String DIALOG_CLIENT_CODE = "DialogClient";

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private ClientProtocol() {}
}
