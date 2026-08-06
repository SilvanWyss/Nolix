/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.filesystem;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.programcontrol.processproperty.WriteMode;

/**
 * The {@link FileSystemAccessor} can access the file system on the local
 * machine.
 * 
 * @author Silvan Wyss
 */
public final class FileSystemAccessor {
  /**
   * Prevents that an instance of the {@link FileSystemAccessor} can be created.
   */
  private FileSystemAccessor() {
  }

  /**
   * @return a new {@link FolderAccessor} to the folder of the running jar file.
   */
  public static FolderAccessor getFolderOfRunningJarFile() {
    return FolderAccessor.forFolderPath(getFolderPathOfRunningJarFile());
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
   * Creates a new empty file with the given path.
   * 
   * @param path
   * @return a new {@link FileAccessor} to the created file
   * @throws RuntimeException if the given path is null
   * @throws RuntimeException if the given path is empty
   * @throws RuntimeException if there exists already a file system item with the
   *                          given path.
   */
  public static FileAccessor createFile(final String path) {
    // Calls other method.
    return createFile(path, WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY);
  }

  /**
   * Creates a new empty file with the given path.
   * 
   * @param path
   * @param writeMode
   * @return a new {@link FileAccessor} to the created file
   * @throws RuntimeException if the given path is null
   * @throws RuntimeException if the given path is blank
   * @throws RuntimeException if the given writeMode flag =
   *                          {@link WriteMode#THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY}
   *                          and there exists already a file system item with the
   *                          given path.
   */
  public static FileAccessor createFile(final String path, final WriteMode writeMode) {
    // Asserts that the if given path is not null or empty.
    Validator.assertThat(path).thatIsNamed(LowerCaseVariableNameCatalog.PATH).isNotBlank();

    // Creates file.
    try {
      if (!new File(path).createNewFile()) {
        switch (writeMode) {
          case OVERWRITE_WHEN_TARGET_EXISTS_ALREADY:
            deleteFileSystemItem(path);
            return createFile(path);
          case SKIP_WHEN_TARGET_EXISTS_ALREADY:
            return FileAccessor.withFilePath(path);
          case THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY:
            throw InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
              "file system item",
              path,
              "exists already");
          default:
            throw InvalidArgumentException.forArgument(writeMode);
        }
      }

      return FileAccessor.withFilePath(path);
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
   * @return a new {@link FileAccessor} to the created file
   * @throws RuntimeException if the given path is null
   * @throws RuntimeException if the given path is blank
   * @throws RuntimeException if the given writeMode =
   *                          {@link WriteMode#THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY}
   *                          and there exists already a file system item with the
   *                          given path
   * @throws RuntimeException if the given content is null
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
   * @return a new {@link FileAccessor} to the created file
   * @throws RuntimeException if the given path is null
   * @throws RuntimeException if the given path is blank
   * @throws RuntimeException if the given writeMode =
   *                          {@link WriteMode#THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY}
   *                          and there exists already a file system item with the
   *                          given path
   * @throws RuntimeException if the given content is null
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
   * @return a new {@link FileAccessor} to the created file
   * @throws RuntimeException if the given path is null
   * @throws RuntimeException if the given path is empty
   * @throws RuntimeException if there exists already a file system item with the
   *                          given path.
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
   * @return a new {@link FileAccessor} to the created file
   * @throws RuntimeException if the given path is null
   * @throws RuntimeException if the given path is blank
   * @throws RuntimeException if there exists already a file system item with the
   *                          given path.
   */
  public static FileAccessor createFile(final String path, final String content) {
    // Calls other method.
    return createFile(path, WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY, content);
  }

  /**
   * Creates a new empty folder with the given path.
   * 
   * @param path
   * @return a new {@link FileAccessor} to the created folder
   * @throws RuntimeException if there exists already a file system item with the
   *                          given path.
   */
  public static FolderAccessor createFolder(final String path) {
    // Asserts that there does not exist already a file system item with the given
    // path.
    if (exists(path)) {
      throw InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        "file system item",
        path,
        "exists already");
    }

    // Creates folder.
    new File(path).mkdirs();

    // Creates and returns a FolderAccessor to the folder.
    return FolderAccessor.forFolderPath(path);
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
   * @return true if there exists a file system item with given path, false
   *         otherwise
   */
  public static boolean exists(final String path) {
    return new File(path).exists();
  }

  /**
   * @param path
   * @return new {@link FileAccessor}s for the files in the folder with the given
   *         path.
   */
  public static ExtendedIterable<FileAccessor> getFileAccessors(final String path) {
    return //
    ExtendedIterableView.forArray(new File(path).listFiles())
      .getViewOfStoredSelected(File::isFile)
      .to(f -> FileAccessor.withFilePath(f.getAbsolutePath()));
  }

  /**
   * @param path
   * @param extension
   * @return new {@link FileAccessor}s for the files in the folder with the given
   *         path, that have the given extension.
   */
  public static ExtendedIterable<FileAccessor> getFileAccessors(final String path, final String extension) {
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
        fileAccessors.addAtEnd(FileAccessor.withFilePath(f.getPath()));
      } else if (f.isDirectory()) {
        fileAccessors.addAtEnd(FolderAccessor.forFolderPath(f.getPath()).getFileAccessorsRecursively());
      } else {
        throw InvalidArgumentException.forArgument(f);
      }
    }

    return fileAccessors;
  }

  /**
   * @param path
   * @return new {@link FileSystemItemAccessor}s for the file system items in the
   *         folder with the given path.
   */
  public static ExtendedIterable<FileSystemItemAccessor> getFileSystemItemAccessors(final String path) {
    return ExtendedIterableView.forArray(new File(path).listFiles())
      .to(f -> new FileSystemItemAccessor(f.getAbsolutePath()));
  }

  /**
   * @param path
   * @return true if there exists a file with the given path, false otherwise
   */
  public static boolean isFile(final String path) {
    return new File(path).isFile();
  }

  /**
   * @param path
   * @return true if there exists a folder with the given path, false otherwise
   */
  public static boolean isFolder(final String path) {
    return new File(path).isDirectory();
  }

  public static void overwriteFile(final String path, final byte[] content) {
    // Asserts that there does not exist a folder with the given path.
    if (isFolder(path)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(path, "is a folder");
    }

    // Handles the case that there does not exist a file with the given path.
    if (!isFile(path)) {
      createFile(path);
    }

    FileAccessor.withFilePath(path).overwriteFile(content);
  }

  /**
   * Overwrites the file with the given path. Creates a new file with the given
   * path if it does not exists. The file will get the given content.
   * 
   * @param path
   * @param content
   * @throws RuntimeException if there exists already a folder with the given
   *                          path.
   */
  public static void overwriteFile(final String path, final String content) {
    // Asserts that there does not exist a folder with the given path.
    if (isFolder(path)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(path, "is a folder");
    }

    // Handles the case that there does not exist a file with the given path.
    if (!isFile(path)) {
      createFile(path);
    }

    FileAccessor.withFilePath(path).overwriteFile(content);
  }

  /**
   * Reads the content of the file with the given filePath to a text.
   * 
   * @param filePath
   * @return the content of the file with the filePath as text
   * @throws RuntimeException if there does not exist a file with the given
   *                          filePath
   */
  public static String readFile(final String filePath) {
    return FileAccessor.withFilePath(filePath).readFile();
  }

  /**
   * Reads the content of the file with the given filePath to bytes.
   * 
   * @param filePath
   * @return the bytes of the file with the given filePath
   * @throws RuntimeException if there does not exist a file with the given
   *                          filePath in the file system on the local machine
   */
  public static byte[] readFileToBytes(final String filePath) {
    return FileAccessor.withFilePath(filePath).readFileToBytes();
  }

  /**
   * Reads the content of the file with the given path to lines.
   * 
   * @param path
   * @return the lines of the file with the given path
   * @throws RuntimeException if there does not exist a file with the given path
   */
  public static ILinkedList<String> readFileToLines(final String path) {
    return FileAccessor.withFilePath(path).readFileToLines();
  }
}
