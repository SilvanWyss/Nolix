//package declaration
package ch.nolix.core.fileSystem;

//Java import
import java.io.File;

import ch.nolix.core.argument.Argument;
import ch.nolix.core.argument.ArgumentName;
import ch.nolix.core.argument.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * A file system item accessor can access a given file system item.
 * A file system item is a file or a folder.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 100
 */
public class FileSystemItemAccessor {

	//attribute
	private final File internalAccessor;
	
	//constructor
	/**
	 * Creates a new file system accessor for the file system item with the given path.
	 * 
	 * @param path
	 * @throws InvalidArgumentException if there exists no file system item with the given path
	 * in the file system on the local machine.
	 */
	public FileSystemItemAccessor(final String path) {
		
		//Creates the internal file accessor of this file accessor.
		internalAccessor = new File(path);
		
		//Checks if the given file path does not point to a directory.
		if (!internalAccessor.exists()) {
			throw new InvalidArgumentException(
				new ArgumentName("path"),
				new Argument(path),
				new ErrorPredicate("is no file system item")
			);
		}
	}

	//method
	/**
	 * @return a new folder accessor to the parent folder
	 * of the file system item of this file system item accessor.
	 */
	public final FolderAccessor getParentFolderAccessor() {
		return new FolderAccessor(internalAccessor.getParent());
	}
	
	//method
	/**
	 * @return the path of the file system item of this file system item accessor.
	 */
	public final String getPath() {
		return internalAccessor.getAbsolutePath();
	}
	
	//method
	/**
	 * @return the size of the file system item of this file system item accessor in bytes.
	 */
	public long getSizeInBytes() {
		return getInternalAccessor().length();
	}
	
	//method
	/**
	 * @return true if the file system item is a file in the file system on the local machine.
	 */
	public final boolean isFile() {
		return getInternalAccessor().isFile();
	}
	
	//method
	/**
	 * @return true if this path is a folder in the file system on the local machine.
	 */
	public final boolean isFolder() {
		return getInternalAccessor().isDirectory();
	}
	
	//method
	/**
	 * Opens the parent folder of the file system item of this file system item accessor
	 * in the file explorer.
	 */
	public final void openParentFolder() {
		getParentFolderAccessor().openInFileExplorer();
	}
	
	//method
	/**
	 * @return the internal accessor of this file system item accessor.
	 */
	protected final File getInternalAccessor() {
		return internalAccessor;
	}
}
