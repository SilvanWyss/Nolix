package ch.nolix.core.environment.filesystem;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

/**
 * A {@link FolderAccessor} can access a folder.
 * 
 * @author Silvan Wyss
 * @version 2017-07-10
 */
public final class FolderAccessor extends FileSystemItemAccessor {

  /**
   * Creates a new {@link FolderAccessor} for the folder of the running jar file.
   */
  public FolderAccessor() {

    //Calls constructor of the base class.
    super(GlobalFileSystemAccessor.getFolderPathOfRunningJarFile());
  }

  /**
   * Creates a new {@link FolderAccessor} for the folder with the given path.
   * 
   * @param path
   * @throws InvalidArgumentException if there does not exist a folder with the
   *                                  given path in the file system on the local
   *                                  machine.
   */
  public FolderAccessor(final String path) {

    //Calls constructor of the base class.
    super(path);

    //Asserts that the file system item with the given path is actually a folder.
    if (!GlobalFileSystemAccessor.isFolder(path)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalogue.PATH,
        path,
        "is not a folder");
    }
  }

  /**
   * @param relativePath
   * @return true if the folder of the current {@link FolderAccessor} contains an
   *         item with the given relative path.
   */
  public boolean containsItem(final String relativePath) {
    return GlobalFileSystemAccessor.exists(getPath() + "/" + relativePath);
  }

  /**
   * Creates a new empty file with the given relative path in the folder of the
   * current {@link FolderAccessor}.
   * 
   * @param relativePath
   * @return a new {@link FileAccessor} for the created file.
   * @throws InvalidArgumentException if there exists already a file system item
   *                                  with the given relative path in the folder
   *                                  of the current {@link FolderAccessor}.
   */
  public FileAccessor createFile(final String relativePath) {
    return (GlobalFileSystemAccessor.createFile(getPath() + "/" + relativePath));
  }

  /**
   * Creates a new empty folder with the given relative path in the folder of the
   * current {@link FolderAccessor}.
   * 
   * @param relativePath
   * @return a new {@link FolderAccessor} for the created folder.
   * @throws InvalidArgumentException if there exists alreay a file system item
   *                                  with the given relative path in the folder
   *                                  of the current {@link FolderAccessor}.
   */
  public FolderAccessor createFolder(final String relativePath) {
    return GlobalFileSystemAccessor.createFolder(getPath() + "/" + relativePath);
  }

  /**
   * Deletes the file system item with the given relative path from the folder of
   * the current {@link FolderAccessor} if it exists.
   * 
   * @param relativePath
   */
  public void deleteFileSystemItem(final String relativePath) {
    GlobalFileSystemAccessor.deleteFileSystemItem(getPath() + "/" + relativePath);
  }

  /**
   * @return new {@link FileAccessor}s for the files in the folder of the current
   *         {@link FolderAccessor}.
   */
  public IContainer<FileAccessor> getFileAccessors() {
    return GlobalFileSystemAccessor.getFileAccessors(getPath());
  }

  /**
   * @param extension
   * @return a new {@link FileAccessor}s for the files in the folder of the
   *         current {@link FolderAccessor}, that have the given extension.
   */
  public IContainer<FileAccessor> getFileAccessors(final String extension) {
    return GlobalFileSystemAccessor.getFileAccessors(getPath(), extension);
  }

  /**
   * @param extension
   * @return new {@link FileAccessor} for the files in the folder of the current
   *         {@link FolderAccessor}, that have the given extension, recursively.
   */
  public IContainer<FileAccessor> getFileAccessorsRecursively(final String extension) {
    return getFileAccessorsRecursively()
      .getStoredSelected(fa -> fa.hasExtension(extension));
  }

  /**
   * @return new {@link FileAccessor} for the files in the folder of the current
   *         {@link FolderAccessor} recursively.
   */
  public ILinkedList<FileAccessor> getFileAccessorsRecursively() {
    return GlobalFileSystemAccessor.getFileAccessorsRecursively(getPath());
  }

  /**
   * @return new {@link FileSystemItemAccessor}s to the file system items in the
   *         folder of the current {@link FolderAccessor}.
   */
  public IContainer<FileSystemItemAccessor> getFileSystemItemAccessors() {
    return GlobalFileSystemAccessor.getFileSystemItemAccessors(getPath());
  }

  /**
   * @param relativePath
   * @return a new {@link FolderAccessor} for the folder with the given relative
   *         path in the folder of the current {@link FolderAccessor}.
   */
  public FolderAccessor getFolderAccessor(final String relativePath) {
    return new FolderAccessor(getPath() + "/" + relativePath);
  }

  /**
   * Opens the folder of the current {@link FolderAccessor} in a new file
   * explorer.
   */
  public void openInFileExplorer() {
    GlobalFileSystemAccessor.openInFileExplorer(getPath());
  }

  /**
   * Reads the content of the file with the given relative path.
   * 
   * @param relativePath
   * @return the content of the file with the given relative path.
   * @throws InvalidArgumentException if there does not exist a file with the
   *                                  given relative path in the folder of the
   *                                  current {@link FolderAccessor}.
   * @throws RuntimeException         if an error occurs.
   */
  public String readFile(final String relativePath) {
    return new FileAccessor(getPath() + "/" + relativePath).readFile();
  }
}
