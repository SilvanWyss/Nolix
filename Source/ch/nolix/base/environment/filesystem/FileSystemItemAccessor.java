/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.filesystem;

/**
 * @author Silvan Wyss
 */
public final class FileSystemItemAccessor extends AbstractFileSystemItemAccessor {
  /**
   * Creates a new {@link FileSystemItemAccessor} for the file system item with
   * the given path.
   * 
   * @param path
   * @throws RuntimeException if there does not exist a file system item with the
   *                          given path in the file system on the local machine
   */
  private FileSystemItemAccessor(final String path) {
    super(path);
  }

  /**
   * @param path
   * @return a new {@link FileSystemItemAccessor} for the file system item with
   *         the given path
   * @throws RuntimeException if there does not exist a file system item with the
   *                          given path in the file system on the local machine
   */
  public static FileSystemItemAccessor withPath(final String path) {
    return new FileSystemItemAccessor(path);
  }
}
