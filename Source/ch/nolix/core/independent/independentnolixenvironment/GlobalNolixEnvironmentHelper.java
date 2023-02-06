//package declaration
package ch.nolix.core.independent.independentnolixenvironment;

//Java imports
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;

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
				throw WrapperException.forError(pIOException);
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
