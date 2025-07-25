package ch.nolix.systemapi.guiapi.frontendapi;

import ch.nolix.coreapi.net.target.IApplicationInstanceTarget;

public interface IFrontEndWriter {

  void openNewTabWithUrl(String url);

  void redirectTo(IApplicationInstanceTarget applicationInstanceTarget);

  void redirectToUrl(String url);

  void saveFile(byte[] bytes);

  void writeTextToClipboard(String text);
}
