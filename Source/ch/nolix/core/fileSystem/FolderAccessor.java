//package declaration
package ch.nolix.core.fileSystem;

//Java import
import java.io.IOException;

import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * A folder accessor can access a given folder.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 90
 */
public final class FolderAccessor extends FileSystemItemAccessor {

	//constructor
	/**
	 * Creates a new folder accessor for the folder with the given folder path.
	 * 
	 * @param folderPath
	 * @throws InvalidArgumentException if there exists no folder with the given folder path
	 * in the file system on the local machine.
	 */
	public FolderAccessor(final String folderPath) {

		//Calls constructor of the base class.
		super(folderPath);
		
		//Checks if the file system item with the given folder path is acutally a folder.
		if (!new FileSystemAccessor().fileSystemItemIsFolder(folderPath)) {
			throw new InvalidArgumentException(
				new ArgumentName("folder path"),
				new Argument(folderPath),
				new ErrorPredicate("is no folder")
			);
		}
	}
	
	//method
	/**
	 * Creates a new empty file with the given relative path in the folder of this folder accessor.
	 * 
	 * @param relativeFilePath
	 * @return new file accessor to the created file.
	 * @throws InvalidArgumentException if a file system item with the given relative file path
	 * exists in the folder of this folder accessor.
	 * @throws RuntimeException if an error occurs.
	 */
	public FileAccessor createFile(final String relativeFilePath) {
		return (new FileSystemAccessor(getPath()).createFile(relativeFilePath));
	}
	
	//method
	/**
	 * Creates a new empty folder with the given relative path in the folder of this folder accessor.
	 * 
	 * @param relativeFolderPath
	 * @return new folder accessor to the created folder.
	 * @throws InvalidArgumentException if a file system item with the given relative file path
	 * exists in the folder of this folder accessor.
	 */
	public FolderAccessor createFolder(final String relativeFolderPath) {
		return (new FileSystemAccessor(getPath()).createFolder(relativeFolderPath));
	}
	
	//method
	/**
	 * Opens the folder of this folder accessor in a new file explorer.
	 */
	public final void openInFileExplorer() {
		try {
			new ProcessBuilder(("explorer.exe " + getPath()).split(" ")).start();
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	//method
	/**
	 * Deletes the file system item with the given relative path
	 * from the folder of this folder accessor if it exists.
	 * 
	 * @param relativePath
	 */
	public void deleteFileSystemItem(final String relativePath) {
		new FileSystemAccessor(getPath()).deleteFileSystemItem(relativePath);
	}
}
