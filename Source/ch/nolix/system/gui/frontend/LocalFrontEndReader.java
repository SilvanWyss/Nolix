//package declaration
package ch.nolix.system.gui.frontend;

//Java imports
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

//Javax imports
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.environment.filesystem.GlobalFileSystemAccessor;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;

//class
public final class LocalFrontEndReader implements IFrontEndReader {

  //constant
  private static final JFileChooser FILE_CHOOSER;

  //static initializing
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

  //method
  @Override
  public IContainer<byte[]> getFilesFromClipboard() {
    return getFilePathsFromClipboard().to(GlobalFileSystemAccessor::readFileToBytes);
  }

  //method
  @Override
  public String getTextFromClipboard() {
    try {
      return Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString();
    } catch (final IOException | UnsupportedFlavorException exception) {
      throw WrapperException.forError(exception);
    }
  }

  //method
  @Override
  public Optional<byte[]> readFileToBytes() {

    if (FILE_CHOOSER.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
      return Optional.empty();
    }

    final var filePath = FILE_CHOOSER.getSelectedFile().getPath();

    return Optional.of(new FileAccessor(filePath).readFileToBytes());
  }

  //method
  private IContainer<String> getFilePathsFromClipboard() {
    try {

      @SuppressWarnings("unchecked")
      final var files = (List<File>) (Toolkit.getDefaultToolkit().getSystemClipboard()
        .getData(DataFlavor.javaFileListFlavor));

      return ContainerView.forIterable(files).to(File::getPath);
    } catch (final IOException | UnsupportedFlavorException exception) {
      throw WrapperException.forError(exception);
    }
  }
}
