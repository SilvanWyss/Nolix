//package declaration
package ch.nolix.core.environment.filesystem;

//Java imports
import java.io.File;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.RegularExpressionPatternCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * A file system item accessor can access a given file system item. A file
 * system item is a file or a folder.
 * 
 * @author Silvan Wyss
 * @version 2017-07-10
 */
public class FileSystemItemAccessor {

  //attribute
  private final File internalAccessor;

  //constructor
  /**
   * Creates a new file system accessor for the file system item with the given
   * path.
   * 
   * @param path
   * @throws InvalidArgumentException if there does not exist a file system item
   *                                  with the given path in the file system on
   *                                  the local machine.
   */
  public FileSystemItemAccessor(final String path) {

    //Creates the internal file accessor of this file accessor.
    internalAccessor = new File(path);

    //Asserts that the given file path does not point to a directory.
    if (!internalAccessor.exists()) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalogue.PATH,
        path,
        "is not a file system item");
    }
  }

  //method
  /**
   * @return the extension of the name of the file system item of the current
   *         {@link FileSystemItemAccessor}.
   */
  public final String getExtension() {

    final var array = RegularExpressionPatternCatalogue.DOT_PATTERN.split(getName());

    return ("." + array[array.length - 1]);
  }

  //method
  /**
   * @return the name of the file system item of the current
   *         {@link FileSystemItemAccessor}.
   */
  public final String getName() {

    final var array = getPath().split("/");

    return array[array.length - 1];
  }

  //method
  /**
   * @return a new folder accessor to the parent folder of the file system item of
   *         this file system item accessor.
   */
  public final FolderAccessor getParentFolderAccessor() {
    return new FolderAccessor(internalAccessor.getParent());
  }

  //method
  /**
   * @return the path of the file system item of this file system item accessor.
   */
  public final String getPath() {
    return internalAccessor.getAbsolutePath();
  }

  //method
  /**
   * @param extension
   * @return true if the file system item of the current
   *         {@link FileSystemItemAccessor} has the given extension.
   */
  public final boolean hasExtension(final String extension) {
    return getExtension().equals(extension);
  }

  //method
  /**
   * @return the size of the file system item of this file system item accessor in
   *         bytes.
   */
  public long getSizeInBytes() {
    return getInternalAccessor().length();
  }

  //method
  /**
   * @return true if the file system item is a file in the file system on the
   *         local machine.
   */
  public final boolean isFile() {
    return getInternalAccessor().isFile();
  }

  //method
  /**
   * @return true if this path is a folder in the file system on the local
   *         machine.
   */
  public final boolean isFolder() {
    return getInternalAccessor().isDirectory();
  }

  //method
  /**
   * Opens the parent folder of the file system item of this file system item
   * accessor in the file explorer.
   */
  public final void openParentFolder() {
    getParentFolderAccessor().openInFileExplorer();
  }

  //method
  /**
   * @return the internal accessor of this file system item accessor.
   */
  protected final File getInternalAccessor() {
    return internalAccessor;
  }
}
