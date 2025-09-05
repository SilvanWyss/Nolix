package ch.nolix.system.gui.frontend;

import javax.swing.JFileChooser;

import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.environment.localcomputer.PopupWindowProvider;
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.net.target.IApplicationInstanceTarget;
import ch.nolix.coreapi.programcontrol.processproperty.WriteMode;
import ch.nolix.systemapi.gui.frontend.IFrontEndWriter;

public final class LocalFrontEndWriter implements IFrontEndWriter {
  @Override
  public void openNewTabWithUrl(final String url) {
    ShellProvider.startDefaultWebBrowserOpeningUrl(url);
  }

  @Override
  public void redirectTo(final IApplicationInstanceTarget applicationInstanceTarget) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "redirectTo");
  }

  @Override
  public void redirectToUrl(String url) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "redirectToUrl");
  }

  @Override
  public void saveFile(final byte[] bytes) {
    final var fileChooser = new JFileChooser();

    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
      final var path = fileChooser.getSelectedFile().getPath();

      if (FileSystemAccessor.exists(path)) {
        if (PopupWindowProvider.showRequestWindow(
          "The file '" + path + "' exists already. Do you want to overwrite it?")) {
          FileSystemAccessor.createFile(path, WriteMode.OVERWRITE_WHEN_TARGET_EXISTS_ALREADY, bytes);
        }
      } else {
        FileSystemAccessor.createFile(path, bytes);
      }
    }
  }

  @Override
  public void writeTextToClipboard(final String text) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "writeTextToClipboard");
  }
}
