/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.filesystem;

import java.io.File;

import ch.nolix.baseapi.commontype.stringtool.RegularExpressionPatternCatalog;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * A file system item accessor can access a given file system item. A file
 * system item is a file or a folder.
 * 
 * @author Silvan Wyss
 */
public class FileSystemItemAccessor {
  private final File internalAccessor;

  /**
   * Creates a new {@link FileSystemItemAccessor }for the file system item with
   * the given path.
   * 
   * @param path
   * @throws RuntimeException if there does not exist a file system item with the
   *                          given path in the file system on the local machine.
   */
  protected FileSystemItemAccessor(final String path) {
    // Creates the internal file accessor of this file accessor.
    internalAccessor = new File(path);

    // Asserts that the given file path does not point to a directory.
    if (!internalAccessor.exists()) {
      throw InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        LowerCaseVariableNameCatalog.PATH,
        path,
        "is not a file system item");
    }
  }

  /**
   * @param path
   * @return a new {@link FileSystemItemAccessor }for the file system item with
   *         the given path
   * @throws RuntimeException if there does not exist a file system item with the
   *                          given path in the file system on the local machine.
   */
  public static FileSystemItemAccessor withPath(final String path) {
    return new FileSystemItemAccessor(path);
  }

  /**
   * @return the extension of the name of the file system item of the current
   *         {@link FileSystemItemAccessor}.
   */
  public final String getExtension() {
    final var array = RegularExpressionPatternCatalog.DOT_PATTERN.split(getName());

    return ("." + array[array.length - 1]);
  }

  /**
   * @return the name of the file system item of the current
   *         {@link FileSystemItemAccessor}.
   */
  public final String getName() {
    final var array = getPath().split("/");

    return array[array.length - 1];
  }

  /**
   * @return a new folder accessor to the parent folder of the file system item of
   *         this file system item accessor.
   */
  public final FolderAccessor getParentFolderAccessor() {
    return FolderAccessor.forFolderPath(internalAccessor.getParent());
  }

  /**
   * @return the path of the file system item of this file system item accessor.
   */
  public final String getPath() {
    return internalAccessor.getAbsolutePath();
  }

  /**
   * @param extension
   * @return true if the file system item of the current
   *         {@link FileSystemItemAccessor} has the given extension, false
   *         otherwise
   */
  public final boolean hasExtension(final String extension) {
    return getExtension().equals(extension);
  }

  /**
   * @return the size of the file system item of this file system item accessor in
   *         bytes.
   */
  public long getSizeInBytes() {
    return getInternalAccessor().length();
  }

  /**
   * @return true if the file system item is a file in the file system on the
   *         local machine, false otherwise
   */
  public final boolean isFile() {
    return getInternalAccessor().isFile();
  }

  /**
   * @return true if this path is a folder in the file system on the local
   *         machine, false otherwise
   */
  public final boolean isFolder() {
    return getInternalAccessor().isDirectory();
  }

  /**
   * @return the internal accessor of this file system item accessor.
   */
  protected final File getInternalAccessor() {
    return internalAccessor;
  }
}
