//package declaration
package ch.nolix.core.independent.nolixenvironment;

//Java imports
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//class
public final class GlobalNolixEnvironmentHelper {
	
	//constant
	public static final String LOCAL_NOLIX_FOLDER_NAME = "Nolix";
	
	//constant
	private static final String APPDATA_HEADER = "APPDATA";
	
	//constant
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
				throw new RuntimeException(pIOException);
			}
		}
	}
	
	//static method
	private static String getLocalAppDataFolderPath() {
		return System.getenv(APPDATA_HEADER);
	}
	
	//constructor
	private GlobalNolixEnvironmentHelper() {}
}
