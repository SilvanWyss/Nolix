//package declaration
package ch.nolix.core.fileSystem;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

//own imports
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * A file accessor can access a given file.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 90
 */
public final class FileAccessor extends FileSystemItemAccessor {
	
	//constructor
	/**
	 * Creates new file accessor for a file with the given file path.
	 * 
	 * @param filePath
	 * @throws InvalidArgumentException if there exists no file with the given file path
	 * in the file system on the local machine.
	 */
	public FileAccessor(final String filePath) {
		
		//Calls method of the base class.
		super(filePath);
		
		//Checks if the file system item with the given file path is acutally a file.
		if (!new FileSystemAccessor().fileSystemItemIsFile(filePath)) {
			throw new InvalidArgumentException(
				new ArgumentName("file path"),
				new Argument(filePath),
				new ErrorPredicate("is no file")
			);
		}
	}
	
	//method
	/**
	 * Clears the file of this file accessor.
	 * Deletes the content of the file of this file accessor, but not the file itself.
	 */
	public void clearFile() {
		overwriteFile(StringManager.EMPTY_STRING);
	}
	
	//method
	/**
	 * Overwrites the file of this file accessor with the given content.
	 * 
	 * @param content
	 * @throws RuntimeException if an error occurs.
	 */
	public void overwriteFile(final String content) {		
		try (final PrintWriter printWriter = new PrintWriter(getInternalAccessor())) {
			printWriter.print(content.replace("\n", "\r\n"));
			printWriter.flush();
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	//method
	/**
	 * Reads the content of the file of this file accessor.
	 * 
	 * @return the content of the file of this file accessor.
	 * @throws RuntimeException if an error occurs.
	 */
	public String readFile() {		
		try {
			return new String(Files.readAllBytes(getInternalAccessor().toPath()));
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
