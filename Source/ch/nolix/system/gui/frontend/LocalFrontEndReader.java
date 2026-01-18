/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.gui.frontend;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.gui.frontend.IFrontEndReader;

/**
 * @author Silvan Wyss
 */
public final class LocalFrontEndReader implements IFrontEndReader {
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

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<byte[]> getFilesFromClipboard() {
    return getFilePathsFromClipboard().to(FileSystemAccessor::readFileToBytes);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTextFromClipboard() {
    try {
      return Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString();
    } catch (final IOException | UnsupportedFlavorException exception) {
      throw WrapperException.forError(exception);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<byte[]> readFileToBytes() {
    if (FILE_CHOOSER.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
      return Optional.empty();
    }

    final var filePath = FILE_CHOOSER.getSelectedFile().getPath();

    return Optional.of(new FileAccessor(filePath).readFileToBytes());
  }

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
