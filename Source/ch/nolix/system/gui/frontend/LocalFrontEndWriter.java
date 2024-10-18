package ch.nolix.system.gui.frontend;

import javax.swing.JFileChooser;

import ch.nolix.core.environment.filesystem.GlobalFileSystemAccessor;
import ch.nolix.core.environment.localcomputer.PopupWindowProvider;
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;

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

      if (GlobalFileSystemAccessor.exists(path)) {
        if (PopupWindowProvider.showRequestWindow(
          "The file '" + path + "' exists already. Do you want to overwrite it?")) {
          GlobalFileSystemAccessor.createFile(path, WriteMode.OVERWRITE_WHEN_TARGET_EXISTS_ALREADY, bytes);
        }
      } else {
        GlobalFileSystemAccessor.createFile(path, bytes);
      }
    }
  }

  @Override
  public void writeTextToClipboard(final String text) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "writeTextToClipboard");
  }
}
