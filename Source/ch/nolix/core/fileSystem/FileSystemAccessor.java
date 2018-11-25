//package declaration
package ch.nolix.core.fileSystem;

//Java imports
import java.io.File;
import java.io.IOException;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A file system accessor can access the file system on the local machine.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 390
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
	 * Creates a new file system accessor.
	 */
	public FileSystemAccessor() {
		
		//Clears the root folder of this file system accessor.
		rootFolder = null;
	}
	
	//constructor
	/**
	 * Creates a new file system accessor with the root folder with the given root folder path.
	 * 
	 * @param rootFolderPath
	 * @throws InvalidArgumentException if there exists no folder
	 * with the given root folder path in the file system on the local machine.
	 */
	public FileSystemAccessor(final String rootFolderPath) {
		
		//Checks if the given root folder is a folder.
		if (!fileSystemItemIsFolder(rootFolderPath)) {
			throw new InvalidArgumentException(
				VariableNameCatalogue.ROOT_FOLDER,
				rootFolderPath,
				"is not a folder"
			);
		}
		
		//Sets the root folder of this file system accessor.
		this.rootFolder = rootFolderPath;
	}
	
	//method
	/**
	 * Creates a new empty file in the file system on the local machine.
	 * 
	 * The file path will be the entry path of the current {@link FileSystemAccessor}
	 * followed by the given relative file path.
	 * 
	 * @param relativeFilePath
	 * @return a new {@link FileAccessor} to the created file.
	 * @throws NullArgumentException if the given relative file path is null.
	 * @throws EmptyArgumentException if the given relative file path is empty.
	 * @throws InvalidArgumentException if the computed file path exists already.
	 */
	public FileAccessor createFile(final String relativeFilePath) {
		
		//Calls other method.
		return createFile(relativeFilePath, false);
	}
	
	//method
	/**
	 * Creates a new empty file on the local machine.
	 * 
	 * The file path will be the entry path of the current {@link FileSystemAccessor}
	 * followed by the given relative file path.
	 * 
	 * If the given overwrite flag is true,
	 * a file with the computed file path, that exists already, will be overwritten.
	 * 
	 * @param relativeFilePath
	 * @param overwrite
	 * @return a new {@link FileAccessor} to the created file.
	 * @throws NullArgumentException if the given relative file path is null.
	 * @throws EmptyArgumentException if the given relative file path is empty.
	 * @throws InvalidArgumentException if the given overwrite flag is false
	 * and the computed file path exists already.
	 */
	public FileAccessor createFile(final String relativeFilePath, boolean overwrite) {
		
		//Checks if the if given file path is not null and not empty.
		Validator
		.suppose(relativeFilePath)
		.thatIsNamed("relative file path")
		.isNotEmpty();
		
		//Computes file path.
		final var filePath = getEntryPath() + relativeFilePath;
		
		//In the case the given overwrite flag is false,
		//checks if the given file path does not exist already.
		if (!overwrite && fileSystemItemExists(filePath)) {
			throw new InvalidArgumentException(
				VariableNameCatalogue.FILE_PATH,
				filePath,
				"exists already"
			);
		}
		
		//Creates file.
		try {
			new File(filePath).createNewFile();
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
		
		//Creates and returns a file accessor to the file.
		return new FileAccessor(filePath);
	}
	
	//method
	/**
	 * Creates a file on the local machine.
	 * 
	 * The file path will be the entry path of the current {@link FileSystemAccessor}
	 * followed by the given relative file path.
	 * 
	 * The file will have the given content.
	 * 
	 * If the given overwrite flag is true,
	 * a file with the computed file path, that exists already, will be overwritten.
	 * 
	 * @param relativeFilePath
	 * @param overwrite
	 * @return a new {@link FileAccessor} to the created file.
	 * @throws NullArgumentException if the given relative file path is null.
	 * @throws EmptyArgumentException if the given relative file path is empty.
	 * @throws InvalidArgumentException if the given overwrite flag is false
	 * and the computed file path exists already.
	 */
	public FileAccessor createFile(
		final String relativeFilePath,
		final boolean overwrite,
		final String content
	) {
		
		//Calls other method.
		final var fileAccessor = createFile(relativeFilePath, overwrite);
		
		fileAccessor.overwriteFile(content);
		
		return fileAccessor;
	}
	
	//method
	/**
	 * Creates a file on the local machine.
	 * 
	 * The file path will be the entry path of the current {@link FileSystemAccessor}
	 * followed by the given relative file path.
	 * 
	 * The file will have the given content.
	 * 
	 * @param relativeFilePath
	 * @param overwrite
	 * @return a new {@link FileAccessor} to the created file.
	 * @throws NullArgumentException if the given relative file path is null.
	 * @throws EmptyArgumentException if the given relative file path is empty.
	 * @throws InvalidArgumentException if the computed file path exists already.
	 */
	public FileAccessor createFile(
		final String relativeFilePath,
		final String content
	) {
		
		//Calls other method.
		return createFile(relativeFilePath, false, content);
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
	 * Creates a new empty folder with the given relative folder path in the file system on the local machine.
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
				VariableNameCatalogue.FOLDER_PATH,
				folderPath,
				"exists already"
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
	public void overwriteFile(
		final String relativeFilePath,
		final String content
	) {
	
		if (!fileSystemItemExists(relativeFilePath)) {
			createFile(relativeFilePath);
		}
		
		new FileAccessor(relativeFilePath).overwriteFile(content);
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
