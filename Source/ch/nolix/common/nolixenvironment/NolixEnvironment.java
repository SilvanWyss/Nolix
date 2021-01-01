//package declaration
package ch.nolix.common.nolixenvironment;

//Java imports
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//own import
import ch.nolix.common.exception.WrapperException;

//class
public final class NolixEnvironment {
	
	//constants
	public static final double DEFAULT_MAX_DEVIATION = 0.000000001; //10^-9
	public static final int DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS = 500;
	public static final String LOCAL_NOLIX_FOLDER_NAME = "Nolix";
	
	//constants
	private static final String APPDATA_HEADER = "APPDATA";
	private static final char FOLDER_DELIMITER = '/';
	
	//static method
	public static String getLocalNolixFolderPath() {
		
		final var localNolixFolderPath = getLocalAppDataFolderPath() + FOLDER_DELIMITER + LOCAL_NOLIX_FOLDER_NAME;
		createFolderIfDoesNotExist(localNolixFolderPath);
		
		return localNolixFolderPath;
	}
	
	//method
	private static void createFolderIfDoesNotExist(final String path) {
		
		final var lPath = Path.of(path);
		
		if (!Files.exists(lPath)) {
			try {
				Files.createDirectory(lPath);
			} catch (final IOException pIOException) {
				throw new WrapperException(pIOException);
			}
		}
	}
	
	//static method
	private static String getLocalAppDataFolderPath() {
		return System.getenv(APPDATA_HEADER);
	}
	
	//visibility-reduced constructor
	private NolixEnvironment() {}
}
