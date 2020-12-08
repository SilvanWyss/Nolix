//package declaration
package ch.nolix.common.logger;

//Java imports
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//own import
import ch.nolix.common.nolixenvironment.NolixEnvironment;

//class
public final class FileLogHandler extends LogHandler {
	
	//constant
	public static final String NOLIX_LOG_FILE_NAME = "Log.txt";
	
	//static method
	public static String getLocalNolixLogFilePath() {
		
		final var localNolixFilePath = NolixEnvironment.getLocalNolixFolderPath() + "/" + NOLIX_LOG_FILE_NAME;
		createFileIfDoesNotExist(localNolixFilePath);
		
		return localNolixFilePath;
	}
	
	//static method
	private static void createFileIfDoesNotExist(final String path) {
		
		final var lPath = Path.of(getLocalNolixLogFilePath());
		
		if (!Files.exists(lPath)) {
			try {
				Files.createFile(lPath);
			}
			catch (final IOException pIOException) {}	
		}
	}
	
	//method
	@Override
	protected void log(final LogEntry logEntry) {
		try {
			Files.write(
				Paths.get(getLocalNolixLogFilePath()),
				logEntry.toString().getBytes(),
				StandardOpenOption.APPEND
			);
		}
		catch (final IOException pIOException) {}	
	}
}
