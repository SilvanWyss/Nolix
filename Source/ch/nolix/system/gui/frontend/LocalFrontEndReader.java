//package declaration
package ch.nolix.system.gui.frontend;

//Java imports
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;

//class
public final class LocalFrontEndReader implements IFrontEndReader {

  // constant
  private static final JFileChooser FILE_CHOOSER;

  // static initializing
  static {

    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (final
        ClassNotFoundException
        | InstantiationException
        | IllegalAccessException
        | UnsupportedLookAndFeelException exception) {
      throw WrapperException.forError(exception);
    }

    FILE_CHOOSER = new JFileChooser();
  }

  // method
  @Override
  public IContainer<byte[]> getFilesFromClipboard() {
    return getFilePathsFromClipboard().to(FileSystemAccessor::readFileToBytes);
  }

  // method
  @Override
  public String getTextFromClipboard() {
    try {
      return Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString();
    } catch (final IOException | UnsupportedFlavorException exception) {
      throw WrapperException.forError(exception);
    }
  }

  // method
  @Override
  public SingleContainer<byte[]> readFileToBytes() {

    if (FILE_CHOOSER.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
      return new SingleContainer<>();
    }

    final var filePath = FILE_CHOOSER.getSelectedFile().getPath();

    return new SingleContainer<>(new FileAccessor(filePath).readFileToBytes());
  }

  // method
  private IContainer<String> getFilePathsFromClipboard() {
    try {

      @SuppressWarnings("unchecked")
      final var files = (List<File>) (Toolkit.getDefaultToolkit().getSystemClipboard()
          .getData(DataFlavor.javaFileListFlavor));

      return ReadContainer.forIterable(files).to(File::getPath);
    } catch (final IOException | UnsupportedFlavorException exception) {
      throw WrapperException.forError(exception);
    }
  }
}
