//package declaration
package ch.nolix.common.fileSystem;

//Java imports
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import ch.nolix.common.constants.StringCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//class
/**
 * A file accessor can access a given file.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 110
 */
public final class FileAccessor extends FileSystemItemAccessor {
	
	//constructor
	/**
	 * Creates a new file accessor for a file with the given file path.
	 * 
	 * @param filePath
	 * @throws InvalidArgumentException if there does not exist a file with the given file path
	 * in the file system on the local machine.
	 */
	public FileAccessor(final String filePath) {
		
		//Calls method of the base class.
		super(filePath);
		
		//Checks if the file system item with the given file path is acutally a file.
		if (!FileSystemAccessor.isFile(filePath)) {
			throw new InvalidArgumentException(
				VariableNameCatalogue.FILE_PATH,
				filePath,
				"is not a file"
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
		try (final var fileOutputStream = new FileOutputStream(getInternalAccessor())) {
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
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
	 * @return the bytes of the file of this file accessor.
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
	
	//method
	/**
	 * Reads the content of the file of this file accessor to lines.
	 * 
	 * @return the lines of the file of this file accessor.
	 * @throws RuntimeException if an error occurs.
	 */
	public List<String> readFileToLines() {
		
		final var lines = new List<String>();
		
		try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(getInternalAccessor()))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				lines.addAtEnd(line);
			}
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
		
		return lines;
	}
}
