//package declaration
package ch.nolix.core.fileSystem;

//Java imports
import java.io.File;
import java.io.IOException;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * A {@link FolderAccessor} can access a folder.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 190
 */
public final class FolderAccessor extends FileSystemItemAccessor {
	
	//constructor
	/**
	 * Creates a new {@link FolderAccessor} for the folder of the running jar file.
	 */
	public FolderAccessor() {
		
		//Calls constructor of the base class.
		super(FileSystemAccessor.getFolderPathOfRunningJar());
	}
	
	//constructor
	/**
	 * Creates a new {@link FolderAccessor} for the folder with the given path.
	 * 
	 * @param path
	 * @throws InvalidArgumentException
	 * if there does not exist a folder with the given path in the file system on the local machine.
	 */
	public FolderAccessor(final String path) {
		
		//Calls constructor of the base class.
		super(path);
		
		//Checks if the file system item with the given path is actually a folder.
		if (!new FileSystemAccessor().fileSystemItemIsFolder(path)) {
			throw new InvalidArgumentException(
				VariableNameCatalogue.PATH,
				path,
				"is not a folder"
			);
		}
	}
	
	//method
	/**
	 * @param relativePath
	 * @return true if the folder of the current {@link FolderAccessor}
	 * contains an item with the given relative path.
	 */
	public boolean containsItem(final String relativePath) {
		return new FileSystemAccessor().fileSystemItemExists(getPath() + "/" + relativePath);
	}
	
	//method
	/**
	 * Creates a new empty file with the given relative path in the folder of the current {@link FolderAccessor}.
	 * 
	 * @param relativePath
	 * @return a new {@link FileAccessor} for the created file.
	 * @throws InvalidArgumentException if there exists already a file system item with the given relative path
	 * in the folder of the current {@link FolderAccessor}.
	 */
	public FileAccessor createFile(final String relativePath) {
		return (new FileSystemAccessor(getPath()).createFile(relativePath));
	}
	
	//method
	/**
	 * Creates a new empty folder with the given relative path in the folder of the current {@link FolderAccessor}.
	 * 
	 * @param relativePath
	 * @return a new {@link FolderAccessor} for the created folder.
	 * @throws InvalidArgumentException if there exists alreay a file system item with the given relative path
	 * in the folder of the current {@link FolderAccessor}.
	 */
	public FolderAccessor createFolder(final String relativePath) {
		return (new FileSystemAccessor(getPath()).createFolder(relativePath));
	}
	
	//method
	/**
	 * Opens the folder of the current {@link FolderAccessor} in a new file explorer.
	 */
	public final void openInFileExplorer() {
		try {
			new ProcessBuilder(("explorer.exe " + getPath()).split(" ")).start();
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
	}
	
	//method
	/**
	 * Deletes the file system item with the given relative path
	 * from the folder of the current {@link FolderAccessor} if it exists.
	 * 
	 * @param relativePath
	 */
	public void deleteFileSystemItem(final String relativePath) {
		new FileSystemAccessor(getPath()).deleteFileSystemItem(relativePath);
	}
	
	//method
	/**
	 * @return new {@link FileAccessor} for the files in the folder of the current {@link FolderAccessor}.
	 */
	public List<FileAccessor> getFileAccessors() {
		return
		new ReadContainer<File>(new File(getPath()))
		.getRefSelected(f -> f.isFile())
		.to(f -> new FileAccessor(f.getAbsolutePath()));
	}
	
	//method
	/**
	 * @param extension
	 * @return new {@link FileAccessor}
	 * for the files in the folder of the current {@link FolderAccessor}, that have the given extension.
	 */
	public List<FileAccessor> getFileAccessors(final String extension) {
		return
		getFileAccessors()
		.getRefSelected(fa -> fa.hasExtension(extension));
	}
	
	//method
	/**
	 * @param extension
	 * @return new {@link FileAccessor}
	 * for the files in the folder of the current {@link FolderAccessor},
	 * that have the given extension, recursively.
	 */
	public List<FileAccessor> getFileAccessorsRecursively(final String extension) {
		return
		getFileAccessorsRecursively()
		.getRefSelected(fa -> fa.hasExtension(extension));
	}
	
	//method
	/**
	 * @return new {@link FileAccessor} for the files in the folder of the current {@link FolderAccessor} recursively.
	 */
	public List<FileAccessor> getFileAccessorsRecursively() {
		
		final var fileAccessors = new List<FileAccessor>();
		
		for (final var f : new File(getPath()).listFiles()) {
			if (f.isFile()) {
				fileAccessors.addAtEnd(new FileAccessor(f.getPath()));
			}
			else {
				fileAccessors.addAtEnd(new FolderAccessor(f.getPath()).getFileAccessorsRecursively());
			}
		}
		
		return fileAccessors;
	}
	
	//method
	/**
	 * @return new {@link FileSystemItemAccessor}
	 * for the file system items in the folder of the current {@link FolderAccessor}.
	 */
	public List<FileSystemItemAccessor> getFileSystemItemAccessors() {
		return
		new ReadContainer<File>(new File(getPath()))
		.to(f -> new FileSystemItemAccessor(f.getAbsolutePath()));
	}
	
	//method
	/**
	 * @param relativePath
	 * @return a new {@link FolderAccessor}
	 * for the folder with the given relative path in the folder of the current {@link FolderAccessor}.
	 */
	public FolderAccessor getFolderAccessor(final String relativePath) {
		return new FolderAccessor(getPath() + "/" + relativePath);
	}
}
