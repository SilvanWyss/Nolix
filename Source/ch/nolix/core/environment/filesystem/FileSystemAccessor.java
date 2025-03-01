package ch.nolix.core.environment.filesystem;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

/**
 * The {@link FileSystemAccessor} can access the file system on the local
 * machine.
 * 
 * @author Silvan Wyss
 * @version 2017-07-14
 */
public final class FileSystemAccessor {

  /**
   * Prevents that an instance of the {@link FileSystemAccessor} can be
   * created.
   */
  private FileSystemAccessor() {
  }

  /**
   * @return a new {@link FolderAccessor} to the folder of the running jar file.
   */
  public static FolderAccessor getFolderOfRunningJarFile() {
    return new FolderAccessor(getFolderPathOfRunningJarFile());
  }

  /**
   * @return the path of the folder of the running jar file.
   */
  public static String getFolderPathOfRunningJarFile() {
    try {
      return FileSystemAccessor.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
    } catch (final URISyntaxException pURISyntaxException) {
      throw WrapperException.forError(pURISyntaxException);
    }
  }

  /**
   * Opens the folder with the given path in a new file explorer.
   * 
   * @param path
   */
  public static void openInFileExplorer(final String path) {
    try {
      Runtime.getRuntime().exec(new String[] { "explorer.exe", "/select,", path });
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  /**
   * Opens the folder of the running jar file in a new file explorer.
   */
  public static void openFolderOfRunningJarFileInExplorer() {
    openInFileExplorer(getFolderPathOfRunningJarFile());
  }

  /**
   * Creates a new empty file with the given path.
   * 
   * @param path
   * @return a new {@link FileAccessor} to the created file.
   * @throws ArgumentIsNullException  if the given path is null.
   * @throws EmptyArgumentException   if the given path is empty.
   * @throws InvalidArgumentException if there exists already a file system item
   *                                  with the given path.
   */
  public static FileAccessor createFile(final String path) {

    //Calls other method.
    return createFile(path, WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY);
  }

  /**
   * Creates a new empty file with the given path.
   * 
   * @param path
   * @param writeMode
   * @return a new {@link FileAccessor} to the created file.
   * @throws ArgumentIsNullException  if the given path is null.
   * @throws InvalidArgumentException if the given path is blank.
   * @throws InvalidArgumentException if the given writeMode flag =
   *                                  {@link WriteMode#THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY}
   *                                  and there exists already a file system item
   *                                  with the given path.
   */
  public static FileAccessor createFile(final String path, final WriteMode writeMode) {

    //Asserts that the if given path is not null or empty.
    Validator.assertThat(path).thatIsNamed(LowerCaseVariableCatalog.PATH).isNotBlank();

    //Creates file.
    try {

      if (!new File(path).createNewFile()) {
        switch (writeMode) {
          case OVERWRITE_WHEN_TARGET_EXISTS_ALREADY:
            deleteFileSystemItem(path);
            return createFile(path);
          case SKIP_WHEN_TARGET_EXISTS_ALREADY:
            return new FileAccessor(path);
          case THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY:
            throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
              "file system item",
              path,
              "exists already");
        }
      }

      return new FileAccessor(path);
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  /**
   * Creates a new file with the given path. The file will have the given content.
   * 
   * @param path
   * @param writeMode
   * @param content
   * @return a new {@link FileAccessor} to the created file.
   * @throws ArgumentIsNullException  if the given path is null.
   * @throws InvalidArgumentException if the given path is blank.
   * @throws InvalidArgumentException if the given writeMode =
   *                                  {@link WriteMode#THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY}
   *                                  and there exists already a file system item
   *                                  with the given path.
   * @throws ArgumentIsNullException  if the given content is null.
   */
  public static FileAccessor createFile(final String path, final WriteMode writeMode, final byte[] content) {

    final var fileAccessor = createFile(path, writeMode);

    fileAccessor.overwriteFile(content);

    return fileAccessor;
  }

  /**
   * Creates a new file with the given path. The file will have the given content.
   * 
   * @param path
   * @param writeMode
   * @param content
   * @return a new {@link FileAccessor} to the created file.
   * @throws ArgumentIsNullException  if the given path is null.
   * @throws InvalidArgumentException if the given path is blank.
   * @throws InvalidArgumentException if the given writeMode =
   *                                  {@link WriteMode#THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY}
   *                                  and there exists already a file system item
   *                                  with the given path.
   * @throws ArgumentIsNullException  if the given content is null.
   */
  public static FileAccessor createFile(final String path, final WriteMode writeMode, final String content) {

    final var fileAccessor = createFile(path, writeMode);

    fileAccessor.overwriteFile(content);

    return fileAccessor;
  }

  /**
   * Creates a new file with the given path. The file will have the given content.
   * 
   * @param path
   * @param content
   * @return a new {@link FileAccessor} to the created file.
   * @throws ArgumentIsNullException  if the given path is null.
   * @throws EmptyArgumentException   if the given path is empty.
   * @throws InvalidArgumentException if there exists already a file system item
   *                                  with the given path.
   */
  public static FileAccessor createFile(final String path, final byte[] content) {

    final var fileAccessor = createFile(path);

    fileAccessor.overwriteFile(content);

    return fileAccessor;
  }

  /**
   * Creates a new file with the given path. The file will have the given content.
   * 
   * @param path
   * @param content
   * @return a new {@link FileAccessor} to the created file.
   * @throws ArgumentIsNullException  if the given path is null.
   * @throws InvalidArgumentException if the given path is blank.
   * @throws InvalidArgumentException if there exists already a file system item
   *                                  with the given path.
   */
  public static FileAccessor createFile(final String path, final String content) {

    //Calls other method.
    return createFile(path, WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY, content);
  }

  /**
   * Creates a new empty folder with the given path.
   * 
   * @param path
   * @return a new {@link FileAccessor} to the created folder.
   * @throws InvalidArgumentException if there exists already a file system item
   *                                  with the given path.
   */
  public static FolderAccessor createFolder(final String path) {

    //Asserts that there does not exist already a file system item with the given
    //path.
    if (exists(path)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        "file system item",
        path,
        "exists already");
    }

    //Creates folder.
    new File(path).mkdirs();

    //Creates and returns a FolderAccessor to the folder.
    return new FolderAccessor(path);
  }

  /**
   * Deletes the file system item with the given path if it exists.
   * 
   * @param path
   */
  public static void deleteFileSystemItem(final String path) {
    try {
      Files.delete(Path.of(path));
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  /**
   * @param path
   * @return true if there exists a file system item with given path.
   */
  public static boolean exists(final String path) {
    return new File(path).exists();
  }

  /**
   * @param path
   * @return new {@link FileAccessor}s for the files in the folder with the given
   *         path.
   */
  public static IContainer<FileAccessor> getFileAccessors(final String path) {
    return ContainerView.forArray(new File(path).listFiles())
      .getStoredSelected(File::isFile)
      .to(f -> new FileAccessor(f.getAbsolutePath()));
  }

  /**
   * @param path
   * @param extension
   * @return new {@link FileAccessor}s for the files in the folder with the given
   *         path, that have the given extension.
   */
  public static IContainer<FileAccessor> getFileAccessors(final String path, final String extension) {
    return getFileAccessors(path).getStoredSelected(fa -> fa.hasExtension(extension));
  }

  /**
   * @param path
   * @return new {@link FileAccessor}s for the files in the folder with the given
   *         path recursively.
   */
  public static ILinkedList<FileAccessor> getFileAccessorsRecursively(final String path) {

    final ILinkedList<FileAccessor> fileAccessors = LinkedList.createEmpty();

    for (final var f : new File(path).listFiles()) {
      if (f.isFile()) {
        fileAccessors.addAtEnd(new FileAccessor(f.getPath()));
      } else if (f.isDirectory()) {
        fileAccessors.addAtEnd(new FolderAccessor(f.getPath()).getFileAccessorsRecursively());
      } else {
        throw InvalidArgumentException.forArgument(f);
      }
    }

    return fileAccessors;
  }

  /**
   * @param path
   * @return new {@link FileSystemItemAccessorUnit}s for the file system items in the
   *         folder with the given path.
   */
  public static IContainer<FileSystemItemAccessorUnit> getFileSystemItemAccessors(final String path) {
    return ContainerView.forArray(new File(path).listFiles())
      .to(f -> new FileSystemItemAccessorUnit(f.getAbsolutePath()));
  }

  /**
   * @param path
   * @return true if there exists a file with the given path.
   */
  public static boolean isFile(final String path) {
    return new File(path).isFile();
  }

  /**
   * @param path
   * @return true if there exists a folder with the given path.
   */
  public static boolean isFolder(final String path) {
    return new File(path).isDirectory();
  }

  public static void overwriteFile(final String path, final byte[] content) {

    //Asserts that there does not exist a folder with the given path.
    if (isFolder(path)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(path, "is a folder");
    }

    //Handles the case that there does not exist a file with the given path.
    if (!isFile(path)) {
      createFile(path);
    }

    new FileAccessor(path).overwriteFile(content);
  }

  /**
   * Overwrites the file with the given path. Creates a new file with the given
   * path if it does not exists. The file will get the given content.
   * 
   * @param path
   * @param content
   * @throws InvalidArgumentException if there exists already a folder with the
   *                                  given path.
   */
  public static void overwriteFile(final String path, final String content) {

    //Asserts that there does not exist a folder with the given path.
    if (isFolder(path)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(path, "is a folder");
    }

    //Handles the case that there does not exist a file with the given path.
    if (!isFile(path)) {
      createFile(path);
    }

    new FileAccessor(path).overwriteFile(content);
  }

  /**
   * Reads the content of the file with the given filePath to bytes.
   * 
   * @param filePath
   * @return the bytes of the file with the given filePath.
   * @throws InvalidArgumentException if there does not exist a file with the
   *                                  given filePath in the file system on the
   *                                  local machine.
   */
  public static byte[] readFileToBytes(final String filePath) {
    return new FileAccessor(filePath).readFileToBytes();
  }

  /**
   * Reads the content of the file with the given path to lines.
   * 
   * @param path
   * @return the lines of the file with the given path.
   * @throws InvalidArgumentException if there does not exist a file with the
   *                                  given path.
   */
  public static ILinkedList<String> readFileToLines(final String path) {
    return new FileAccessor(path).readFileToLines();
  }
}
