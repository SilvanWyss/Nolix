/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.filesystem;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * A {@link FolderAccessor} can access a folder.
 * 
 * @author Silvan Wyss
 */
public final class FolderAccessor extends FileSystemItemAccessor {
  /**
   * Creates a new {@link FolderAccessor} for the folder with the given
   * folderPath.
   * 
   * @param folderPath
   * @throws RuntimeException if there does not exist a folder with the given
   *                          folderPath in the file system on the local machine.
   */
  private FolderAccessor(final String folderPath) {
    if (!FileSystemAccessor.isFolder(folderPath)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        folderPath,
        "folder path",
        "is not a folder");
    }

    super(folderPath);
  }

  /**
   * @param folderPath
   * @return a new {@link FolderAccessor} for the folder for the given folderPath
   * @throws RuntimeException if there does not exist a folder with the given
   *                          folderPath in the file system on the local machine.
   */
  public static FolderAccessor forFolderPath(final String folderPath) {
    return new FolderAccessor(folderPath);
  }

  /**
   * @param relativePath
   * @return true if the folder of the current {@link FolderAccessor} contains an
   *         item with the given relative path, false otherwise
   */
  public boolean containsItem(final String relativePath) {
    return FileSystemAccessor.exists(getPath() + "/" + relativePath);
  }

  /**
   * Creates a new empty file with the given relative path in the folder of the
   * current {@link FolderAccessor}.
   * 
   * @param relativePath
   * @return a new {@link FileAccessor} for the created file
   * @throws RuntimeException if there exists already a file system item with the
   *                          given relative path in the folder of the current
   *                          {@link FolderAccessor}.
   */
  public FileAccessor createFile(final String relativePath) {
    return (FileSystemAccessor.createFile(getPath() + "/" + relativePath));
  }

  /**
   * Creates a new empty folder with the given relative path in the folder of the
   * current {@link FolderAccessor}.
   * 
   * @param relativePath
   * @return a new {@link FolderAccessor} for the created folder
   * @throws RuntimeException if there exists alreay a file system item with the
   *                          given relative path in the folder of the current
   *                          {@link FolderAccessor}.
   */
  public FolderAccessor createFolder(final String relativePath) {
    return FileSystemAccessor.createFolder(getPath() + "/" + relativePath);
  }

  /**
   * Deletes the file system item with the given relative path from the folder of
   * the current {@link FolderAccessor} if it exists.
   * 
   * @param relativePath
   */
  public void deleteFileSystemItem(final String relativePath) {
    FileSystemAccessor.deleteFileSystemItem(getPath() + "/" + relativePath);
  }

  /**
   * @return new {@link FileAccessor}s for the files in the folder of the current
   *         {@link FolderAccessor}.
   */
  public ExtendedIterable<FileAccessor> getFileAccessors() {
    return FileSystemAccessor.getFileAccessors(getPath());
  }

  /**
   * @param extension
   * @return a new {@link FileAccessor}s for the files in the folder of the
   *         current {@link FolderAccessor}, that have the given extension.
   */
  public ExtendedIterable<FileAccessor> getFileAccessors(final String extension) {
    return FileSystemAccessor.getFileAccessors(getPath(), extension);
  }

  /**
   * @param extension
   * @return new {@link FileAccessor} for the files in the folder of the current
   *         {@link FolderAccessor}, that have the given extension, recursively.
   */
  public ExtendedIterable<FileAccessor> getFileAccessorsRecursively(final String extension) {
    return getFileAccessorsRecursively().getStoredSelected(fa -> fa.hasExtension(extension));
  }

  /**
   * @return new {@link FileAccessor} for the files in the folder of the current
   *         {@link FolderAccessor} recursively.
   */
  public ILinkedList<FileAccessor> getFileAccessorsRecursively() {
    return FileSystemAccessor.getFileAccessorsRecursively(getPath());
  }

  /**
   * @return new {@link FileSystemItemAccessor}s to the file system items in the
   *         folder of the current {@link FolderAccessor}.
   */
  public ExtendedIterable<FileSystemItemAccessor> getFileSystemItemAccessors() {
    return FileSystemAccessor.getFileSystemItemAccessors(getPath());
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
   * Reads the content of the file with the given relative path.
   * 
   * @param relativePath
   * @return the content of the file with the given relative path
   * @throws RuntimeException if there does not exist a file with the given
   *                          relative path in the folder of the current
   *                          {@link FolderAccessor}
   * @throws RuntimeException if an error occurs
   */
  public String readFile(final String relativePath) {
    return FileAccessor.withFilePath(getPath() + "/" + relativePath).readFile();
  }
}
