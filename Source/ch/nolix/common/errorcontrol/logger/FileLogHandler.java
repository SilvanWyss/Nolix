//package declaration
package ch.nolix.common.errorcontrol.logger;

//Java imports
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import ch.nolix.common.errorcontrol.exception.WrapperException;
import ch.nolix.common.nolixenvironment.NolixEnvironment;

//class
public final class FileLogHandler extends LogHandler {
	
	//constant
	public static final String NOLIX_LOG_FILE_NAME = "Log.txt";
	
	//constant
	private static final char FOLDER_DELIMITER = '/';
	
	//static method
	public static String getLocalNolixLogFilePath() {
		
		final var localNolixFilePath = NolixEnvironment.getLocalNolixFolderPath() + FOLDER_DELIMITER + NOLIX_LOG_FILE_NAME;
		createFileIfDoesNotExist(localNolixFilePath);
		
		return localNolixFilePath;
	}
	
	//static method
	private static void createFileIfDoesNotExist(final String path) {
		
		final var lPath = Path.of(path);
		
		if (!Files.exists(lPath)) {
			try {
				Files.createFile(lPath);
			} catch (final IOException pIOException) {
				throw new WrapperException(pIOException);
			}	
		}
	}
	
	//method
	@Override
	protected void log(final LogEntry logEntry) {
		try {
			Files.writeString(
				Paths.get(getLocalNolixLogFilePath()),
				logEntry.toString() + System.lineSeparator(),
				StandardOpenOption.APPEND
			);
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}	
	}
}
