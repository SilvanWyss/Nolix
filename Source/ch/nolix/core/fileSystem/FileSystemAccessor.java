//package declaration
package ch.nolix.core.fileSystem;

//Java imports
import java.io.File;
import java.io.IOException;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * A file system accessor can access the file system on the local machine.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 300
 */
public final class FileSystemAccessor {
	
	//static method
	/**
	 * @return a new folder accessor to the folder of the running jar.
	 */
	public static FolderAccessor accessFolderOfRunningJar() {
		return new FolderAccessor(getFolderPathOfRunningJar());
	}
	
	//static method
	/**
	 * @return the folder path of the running jar file.
	 */
	public static String getFolderPathOfRunningJar() {
		return new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath();
	}
	
	//static method
	/**
	 * Opens the folder of the running jar in a new file explorer.
	 */
	public static void openFolderOfRunningJarInExplorer() {
		accessFolderOfRunningJar().openInFileExplorer();
	}

	//optional attribute
	private final String rootFolder;
	
	//constructor
	/**
	 * Creates new file system accessor.
	 */
	public FileSystemAccessor() {
		
		//Clears the root folder of this file system accessor.
		rootFolder = null;
	}
	
	//constructor
	/**
	 * Creates new file system accessor with the root folder with the given root folder path.
	 * 
	 * @param rootFolderPath
	 * @throws InvalidArgumentException if there exists no folder
	 * with the given root folder path in the file system on the local machine.
	 */
	public FileSystemAccessor(final String rootFolderPath) {
		
		//Checks if the given root folder is a folder.
		if (!fileSystemItemIsFolder(rootFolderPath)) {
			throw new InvalidArgumentException(
				new ArgumentName("root folder"),
				new Argument(rootFolderPath),
				new ErrorPredicate("is no folder")
			);
		}
		
		//Sets the root folder of this file system accessor.
		this.rootFolder = rootFolderPath;
	}
	
	//method
	/**
	 * Creates a new empty file with the given relative file path in the file system on the local machine.
	 * 
	 * @param relativeFilePath
	 * @return file accessor to the created file.
	 * @throws InvalidArgumentException if a file system item with the given relative file path
	 * exists already in the file system on the local machine.
	 * @throws RuntimeException if an error occurs.
	 */
	public FileAccessor createFile(final String relativeFilePath) {
		
		//Creates file path.
		final String filePath = getEntryPath() + relativeFilePath;
		
		//Checks if the given file path does not exist in the file system on the local machine.
		if (fileSystemItemExists(filePath)) {
			throw new InvalidArgumentException(
				new ArgumentName("file path"),
				new Argument(filePath),
				new ErrorPredicate("exists already in the file system")
			);
		}
		
		//Creates file.
		try {
			new File(filePath).createNewFile();
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
		
		//Creates and returns a file accessor to the created file.
		return new FileAccessor(filePath);
	}
	
	//method
	/**
	 * Creates a new file with the given relative file path in the file system on the local machine.
	 * Writes the given content to the created file.
	 * 
	 * @param relativeFilePath
	 * @throws InvalidArgumentException if a file system item with the given relative file path
	 * exists already in the file system on the local machine.
	 * @throws RuntimeException if an error occurs.
	 */
	public void createFile(
		final String relativeFilePath,
		final String content
	) {	
		createFile(relativeFilePath).overwriteFile(content);
	}
	
	//method
	/**
	 * Creates a new empty file with the given relative file path in the file system on the local machine.
	 * 
	 * Increments the file name if a file system item with the given relative file path
	 * exists already in the file system on the local machine.
	 * 
	 * @param relativeFilePath
	 * @return file accessor to the created file.
	 * @throws RuntimeException if an error occurs.
	 */
	public FileAccessor createFileIncrementingFileName(
		final String relativeFilePath
	) {
		
		//Handles the case that no file system item with the given relative file path exists
		//in the file system on the local machine.
		if (!fileSystemItemExists(relativeFilePath)) {
			return createFile(relativeFilePath);
		}
		
		//Handles the case that a file system item with the given relative file path exists
		//in the file system on the local machine.		
			final String[] relativeFilePathParts = relativeFilePath.split("\\.");
			
			String producteRelativeFilePath;
			int i = 1;
			do {
				
				producteRelativeFilePath
				= relativeFilePathParts[0]
				+ "_"
				+ i
				+ "."
				+ relativeFilePathParts[relativeFilePathParts.length - 1];
				
				i++;
			}
			while (fileSystemItemExists(producteRelativeFilePath));
			
			return createFile(producteRelativeFilePath);
	}
	
	//method
	/**
	 * Creates a new file with the given relative file path in the file system on the local machine.
	 * Writes the given content to the created file.
	 * 
	 * Increments the file name if a file system item with the given relative file path
	 * exists already in the file system on the local machine.
	 * 
	 * @param relativeFilePath
	 * @throws RuntimeException if an error occurs.
	 */
	public void createFileIncrementingFileName(
		final String relativeFilePath,
		final String content
	) {
		createFileIncrementingFileName(relativeFilePath)
		.overwriteFile(content);
	}
	
	//method
	/**
	 * Creates new empty folder with the given relative folder path in the file system on the local machine.
	 * 
	 * @param relativeFolderPath
	 * @return new folder accessor to the created folder.
	 * @throws InvalidArgumentException if a file system item with the given relative folder path
	 * exists already in the file system on the local machine.
	 */
	public FolderAccessor createFolder(final String relativeFolderPath) {
		
		//Creates folder path.
		final String folderPath = getEntryPath() + relativeFolderPath;
		
		//Checks if the given folder path does not exist in the file system on the local machine.
		if (fileSystemItemExists(folderPath)) {
			throw new InvalidArgumentException(
				new ArgumentName("folder path"),
				new Argument(folderPath),
				new ErrorPredicate("exists already in the file system")
			);
		}
		
		//Creates folder.
		new File(folderPath).mkdirs();
		
		//Creates and returns folder accessor.
		return new FolderAccessor(folderPath);
	}
	
	//method
	/**
	 * Deletes the file system item with the given relative path from the file system on the local machine if it exists.
	 * 
	 * @param relativePath
	 */
	public void deleteFileSystemItem(final String relativePath) {
		new File(getEntryPath() + relativePath).delete();
	}
	
	//method
	/**
	 * @param relativePath
	 * @return true if a file system item with given relative path exists in the file system on the local machine.
	 */
	public boolean fileSystemItemExists(final String relativePath) {
		return (new File(getEntryPath() + relativePath).exists());
	}

	//method
	/**
	 * @param relativePath
	 * @return true if the file system item with the given relative path is a file in the file system on the local machine.
	 */
	public boolean fileSystemItemIsFile(final String relativePath) {
		return (new File(getEntryPath() + relativePath).isFile());
	}
	
	//method
	/**
	 * @param relativePath
	 * @return true if the file system item with the given path exists in the file system on the local machine.
	 */
	public boolean fileSystemItemIsFolder(final String relativePath) {
		return (new File(getEntryPath() + relativePath).isDirectory());
	}
	
	//method
	/**
	 * @return the entry path of this file system accessor.
	 */
	private String getEntryPath() {
		
		if (!hasRootFolder()) {
			return StringCatalogue.EMPTY_STRING;
		}
		
		return (getRootPathOrEmptyString() + CharacterCatalogue.SLASH);
	}
	
	//method
	/**
	 * @return the root path of this file system accessor or an empty string.
	 */
	private String getRootPathOrEmptyString() {
		
		//Handles the case that this file system accessor has no root folder.
		if (!hasRootFolder()) {
			return StringCatalogue.EMPTY_STRING;
		}
		
		//Handles the case that this file system accessor has a root folder.
		return rootFolder;
	}
	
	//method
	/**
	 * @return true if this file system accessor has a root folder.
	 */
	private boolean hasRootFolder() {
		return (rootFolder != null);
	}
}
