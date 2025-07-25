package ch.nolix.systemapi.gui.frontend;

import ch.nolix.coreapi.net.target.IApplicationInstanceTarget;

public interface IFrontEndWriter {

  void openNewTabWithUrl(String url);

  void redirectTo(IApplicationInstanceTarget applicationInstanceTarget);

  void redirectToUrl(String url);

  void saveFile(byte[] bytes);

  void writeTextToClipboard(String text);
}
