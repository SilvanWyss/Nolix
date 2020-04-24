//package declaration
package ch.nolix.common.fileSystem;

//Java imports
import java.io.File;
import java.io.IOError;
import java.io.IOException;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.processProperties.WriteMode;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperException.WrapperException;

//class
/**
 * The {@link FileSystemAccessor} can access the file system on the local machine.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 350
 */
public final class FileSystemAccessor {
	
	//static method
	/**
	 * @return a new {@link FolderAccessor} to the folder of the running jar file.
	 */
	public static FolderAccessor getFolderOfRunningJarFile() {
		return new FolderAccessor(getFolderPathOfRunningJarFile());
	}
	
	//static method
	/**
	 * @return the path of the folder of the running jar file.
	 */
	public static String getFolderPathOfRunningJarFile() {
		return new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath();
	}
	
	//static method
	/**
	 * Opens the folder with the given path in a new file explorer.
	 * 
	 * @param path
	 */
	public static void openInFileExplorer(final String path) {
		try {
			Runtime.getRuntime().exec("explorer.exe /select," + path);
		}
		catch (final IOException IOException) {
			throw new IOError(IOException);
		}
	}
	
	//static method
	/**
	 * Opens the folder of the running jar file in a new file explorer.
	 */
	public static void openFolderOfRunningJarFileInExplorer() {
		openInFileExplorer(getFolderPathOfRunningJarFile());
	}
	
	//static method
	/**
	 * Creates a new empty file with the given path.
	 * 
	 * @param path
	 * @return a new {@link FileAccessor} to the created file.
	 * @throws ArgumentIsNullException if the given path is null.
	 * @throws EmptyArgumentException if the given path is empty.
	 * @throws InvalidArgumentException if there exists already a file system item with the given path.
	 */
	public static FileAccessor createFile(final String path) {
		
		//Calls other method.
		return createFile(path, WriteMode.THROW_EXCEPTION_WHEN_EXISTS_ALREADY);
	}
	
	//static method
	/**
	 * Creates a new empty file with the given path.
	 * 
	 * @param path
	 * @param writeMode
	 * @return a new {@link FileAccessor} to the created file.
	 * @throws ArgumentIsNullException if the given path is null.
	 * @throws InvalidArgumentException if the given path is blank.
	 * @throws InvalidArgumentException
	 * if the given writeMode flag={@link WriteMode#THROW_EXCEPTION_WHEN_EXISTS_ALREADY}
	 * and there exists already a file system item with the given path.
	 */
	public static FileAccessor createFile(final String path, final WriteMode writeMode) {
		
		//Asserts that the if given path is not null or empty.
		Validator.assertThat(path).thatIsNamed(VariableNameCatalogue.PATH).isNotBlank();
		
		//Creates file.
		try {
			
			if (!new File(path).createNewFile()) {
				switch (writeMode) {
					case OVERWRITE_WHEN_EXISTS_ALREADY:
						deleteFileSystemItem(path);
						return createFile(path);
					case SKIP_WHEN_EXISTS_ALREADY:
						return new FileAccessor(path);
					case THROW_EXCEPTION_WHEN_EXISTS_ALREADY:
						throw new InvalidArgumentException("file system item",	path, "exists already");		
				}
			}
			
			return new FileAccessor(path);
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//static method
	/**
	 * Creates a new file with the given path. The file will have the given content.
	 * 
	 * @param path
	 * @param writeMode
	 * @param content
	 * @return a new {@link FileAccessor} to the created file.
	 * @throws ArgumentIsNullException if the given path is null.
	 * @throws InvalidArgumentException if the given path is blank.
	 * @throws InvalidArgumentException
	 * if the given writeMode flag={@link WriteMode#THROW_EXCEPTION_WHEN_EXISTS_ALREADY}
	 * and there exists already a file system item with the given path.
	 * @throws ArgumentIsNullException if the given content is null.
	 */
	public static FileAccessor createFile(final String path, final WriteMode writeMode, final String content) {
		
		final var fileAccessor = createFile(path, writeMode);
		
		fileAccessor.overwriteFile(content);
		
		return fileAccessor;
	}
	
	//static method
	/**
	 * Creates a new file with the given path.
	 * The file will have the given content.
	 * 
	 * @param path
	 * @return a new {@link FileAccessor} to the created file.
	 * @throws ArgumentIsNullException if the given path is null.
	 * @throws EmptyArgumentException if the given path is empty.
	 * @throws InvalidArgumentException if there exists already a file system item with the given path.
	 */
	public static FileAccessor createFile(final String path, final byte[] content) {
		
		final var fileAccessor = createFile(path);
		
		fileAccessor.overwriteFile(content);
		
		return fileAccessor;
	}
	
	//static method
	/**
	 * Creates a new file with the given path. The file will have the given content.
	 * 
	 * @param path
	 * @return a new {@link FileAccessor} to the created file.
	 * @throws ArgumentIsNullException if the given path is null.
	 * @throws InvalidArgumentException if the given path is blank.
	 * @throws InvalidArgumentException if there exists already a file system item with the given path.
	 */
	public static FileAccessor createFile(final String path, final String content) {
		
		//Calls other method.
		return createFile(path, WriteMode.THROW_EXCEPTION_WHEN_EXISTS_ALREADY, content);
	}
	
	//static method
	/**
	 * Creates a new empty folder with the given path.
	 * 
	 * @param path
	 * @return a new {@link FileAccessor} to the created folder.
	 * @throws InvalidArgumentException if there exists already a file system item with the given path.
	 */
	public static FolderAccessor createFolder(final String path) {
		
		//Asserts that there does not exist already a file system item with the given path.
		if (exists(path)) {
			throw new InvalidArgumentException("file system item",	path, "exists already");
		}
		
		//Creates folder.
		new File(path).mkdirs();
		
		//Creates and returns a FolderAccessor to the folder.
		return new FolderAccessor(path);
	}
	
	//static method
	/**
	 * Deletes the file system item with the given path if it exists.
	 * 
	 * @param path
	 */
	public static void deleteFileSystemItem(final String path) {
		new File(path).delete();
	}
	
	//static method
	/**
	 * @param path
	 * @return true if there exists a file system item with given path.
	 */
	public static boolean exists(final String path) {
		return new File(path).exists();
	}
	
	//static method
	/**
	 * @param path
	 * @return new {@link FileAccessor}s for the files in the folder with the given path.
	 */
	public static LinkedList<FileAccessor> getFileAccessors(final String path) {
		return
		new ReadContainer<File>(new File(path).listFiles())
		.getRefSelected(f -> f.isFile())
		.to(f -> new FileAccessor(f.getAbsolutePath()));
	}
	
	//static method
	/**
	 * @param path
	 * @param extension
	 * @return new {@link FileAccessor}s for the files in the folder with the given path,
	 * that have the given extension.
	 */
	public static LinkedList<FileAccessor> getFileAccessors(final String path, final String extension) {
		return getFileAccessors(path).getRefSelected(fa -> fa.hasExtension(extension));
	}
	
	//static method
	/**
	 * @param path
	 * @return new {@link FileAccessor}s for the files in the folder with the given path recursively.
	 */
	public static LinkedList<FileAccessor> getFileAccessorsRecursively(final String path) {
		
		final var fileAccessors = new LinkedList<FileAccessor>();
		
		for (final var f : new File(path).listFiles()) {
			if (f.isFile()) {
				fileAccessors.addAtEnd(new FileAccessor(f.getPath()));
			}
			else if (f.isDirectory()) {
				fileAccessors.addAtEnd(new FolderAccessor(f.getPath()).getFileAccessorsRecursively());
			}
		}
		
		return fileAccessors;
	}
	
	//static method
	/**
	 * @param path
	 * @return new {@link FileSystemItemAccessor}s
	 * for the file system items in the folder with the given path.
	 */
	public static LinkedList<FileSystemItemAccessor> getFileSystemItemAccessors(final String path) {
		return
		new ReadContainer<File>(new File(path).listFiles())
		.to(f -> new FileSystemItemAccessor(f.getAbsolutePath()));
	}
	
	//static method
	/**
	 * @param relativePath
	 * @return true if there exists a file with the given path.
	 */
	public static boolean isFile(final String path) {
		return new File(path).isFile();
	}
	
	//static method
	/**
	 * @param path
	 * @return true if there exists a folder with the given path.
	 */
	public static boolean isFolder(final String path) {
		return new File(path).isDirectory();
	}
	
	//static method
	public static void overwriteFile(final String path, final byte[] content) {
		
		//Asserts that there does not exist a folder with the given path.
		if (isFolder(path)) {
			throw new InvalidArgumentException(path, "is a folder");
		}
		
		//Handles the case that there does not exist a file with the given path.
		if (!isFile(path)) {
			createFile(path);
		}
		
		new FileAccessor(path).overwriteFile(content);
	}
	
	//static method
	/**
	 * Overwrites the file with the given path.
	 * Creates a new file with the given path if it does not exists.
	 * The file will get the given content.
	 * 
	 * @param path
	 * @param content
	 * @throws InvalidArgumentException if there exists already a folder with the given path.
	 */
	public static void overwriteFile(final String path, final String content) {
		
		//Asserts that there does not exist a folder with the given path.
		if (isFolder(path)) {
			throw new InvalidArgumentException(path, "is a folder");
		}
		
		//Handles the case that there does not exist a file with the given path.
		if (!isFile(path)) {
			createFile(path);
		}
		
		new FileAccessor(path).overwriteFile(content);
	}
	
	//static method
	/**
	 * Reads the content of the file with the given path to lines.
	 * 
	 * @return the lines of the file with the given path.
	 * @throws InvalidArgumentException if there does not exist a file with the given path.
	 */
	public static LinkedList<String> readFileToLines(final String path) {
		return new FileAccessor(path).readFileToLines();
	}
	
	//visibility-reducing constructor
	/**
	 * Avoids that an instance of the {@link FileSystemAccessor} can be created.
	 */
	private FileSystemAccessor() {}
}
