//package declaration
package ch.nolix.core.fileSystem;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ArgumentName;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;

//class
/**
 * A file accessor can access a given file.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 120
 */
public final class FileAccessor extends FileSystemItemAccessor {
	
	//constructor
	/**
	 * Creates a new file accessor for a file with the given file path.
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
		overwriteFile(StringCatalogue.EMPTY_STRING);
	}
	
	//method
	/**
	 * Overwrites the file of this file accessor with the given bytes.
	 * 
	 * @param bytes
	 * @throws RuntimeException if an error occurs.
	 */
	public void overwriteFile(final byte[] bytes) {
		try (final PrintWriter printWriter = new PrintWriter(getInternalAccessor())) {
			printWriter.print(bytes);
			printWriter.flush();
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
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
		return
		new String(readFileToBytes())
		.replace("\r", StringCatalogue.EMPTY_STRING);
	}
	
	//method
	/**
	 * Reads the content of the file of this file accessor to bytes.
	 * 
	 * @return the content of the file of this file accessor to bytes.
	 * @throws RuntimeException if an error occurs.
	 */
	public byte[] readFileToBytes() {
		try {
			return Files.readAllBytes(getInternalAccessor().toPath());
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
