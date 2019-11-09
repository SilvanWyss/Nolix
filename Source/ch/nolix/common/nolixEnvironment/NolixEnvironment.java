//package declaration
package ch.nolix.common.nolixEnvironment;

//class
public final class NolixEnvironment {
	
	//constant
	public static final String LOCAL_NOLIX_FOLDER_NAME = "Nolix";
	
	//constant
	private static final String APPDATA_HEADER = "APPDATA";
	
	//static method
	public static String getLocalNolixFolderPath() {
		return getLocalAppDataFolderPath() + "/" + LOCAL_NOLIX_FOLDER_NAME;
	}
	
	//static method
	private static String getLocalAppDataFolderPath() {
		return System.getenv(APPDATA_HEADER);
	}
	
	//private constructor
	private NolixEnvironment() {}
}
