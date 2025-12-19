package ch.nolix.core.environment.filesystem;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * A file accessor can access a given file.
 * 
 * @author Silvan Wyss
 */
public final class FileAccessor extends FileSystemItemAccessorUnit {
  /**
   * Creates a new file accessor for a file with the given file path.
   * 
   * @param filePath
   * @throws InvalidArgumentException if there does not exist a file with the
   *                                  given file path in the file system on the
   *                                  local machine.
   */
  public FileAccessor(final String filePath) {
    //Calls method of the base class.
    super(filePath);

    //Asserts that the file system item with the given file path is acutally a
    //file.
    if (!FileSystemAccessor.isFile(filePath)) {
      throw InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        LowerCaseVariableCatalog.FILE_PATH,
        filePath,
        "is not a file");
    }
  }

  /**
   * Clears the file of this file accessor. Deletes the content of the file of
   * this file accessor, but not the file itself.
   */
  public void clearFile() {
    overwriteFile(StringCatalog.EMPTY_STRING);
  }

  /**
   * Overwrites the file of this file accessor with the given bytes.
   * 
   * @param bytes
   * @throws RuntimeException if an error occurs.
   */
  public void overwriteFile(final byte[] bytes) {
    try (final var fileOutputStream = new FileOutputStream(getInternalAccessor())) {
      fileOutputStream.write(bytes);
      fileOutputStream.flush();
    } catch (final IOException exception) {
      throw WrapperException.forError(exception);
    }
  }

  /**
   * Overwrites the file of this file accessor with the given content.
   * 
   * @param content
   * @throws RuntimeException if an error occurs.
   */
  public void overwriteFile(final String content) {
    try (final var printWriter = new PrintWriter(getInternalAccessor(), StandardCharsets.UTF_8)) {
      printWriter.print(content.replace("\n", "\r\n"));
      printWriter.flush();
    } catch (final IOException exception) {
      throw WrapperException.forError(exception);
    }
  }

  /**
   * Reads the content of the file of this file accessor.
   * 
   * @return the content of the file of this file accessor.
   * @throws RuntimeException if an error occurs.
   */
  public String readFile() {
    return new String(readFileToBytes(), StandardCharsets.UTF_8).replace("\r", StringCatalog.EMPTY_STRING);
  }

  /**
   * Reads the content of the file of this file accessor to bytes.
   * 
   * @return the bytes of the file of this file accessor.
   * @throws RuntimeException if an error occurs.
   */
  public byte[] readFileToBytes() {
    try {
      return Files.readAllBytes(getInternalAccessor().toPath());
    } catch (final IOException exception) {
      throw WrapperException.forError(exception);
    }
  }

  /**
   * Reads the content of the file of this file accessor to lines.
   * 
   * @return the lines of the file of this file accessor.
   * @throws RuntimeException if an error occurs.
   */
  public ILinkedList<String> readFileToLines() {
    final ILinkedList<String> lines = LinkedList.createEmpty();

    try (
    final var bufferedReader = new BufferedReader(new FileReader(getInternalAccessor(), StandardCharsets.UTF_8))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        lines.addAtEnd(line);
      }
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }

    return lines;
  }
}
