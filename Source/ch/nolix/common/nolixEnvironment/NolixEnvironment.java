//package declaration
package ch.nolix.common.nolixEnvironment;

//class
public final class NolixEnvironment {
	
	//constants
	public static final double DEFAULT_MAX_DEVIATION = 0.000000001; //10^-9
	public static final int DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS = 500;
	public static final String LOCAL_NOLIX_FOLDER_NAME = "Nolix";
	
	//constant
	private static final String APPDATA_HEADER = "APPDATA";
	
	//static method
	public static String getLocalNolixFolderPath() {
		return (getLocalAppDataFolderPath() + "/" + LOCAL_NOLIX_FOLDER_NAME);
	}
	
	//static method
	private static String getLocalAppDataFolderPath() {
		return System.getenv(APPDATA_HEADER);
	}
	
	//visibility-reducing constructor
	private NolixEnvironment() {}
}
