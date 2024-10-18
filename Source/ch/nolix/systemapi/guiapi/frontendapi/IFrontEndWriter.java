package ch.nolix.systemapi.guiapi.frontendapi;

import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;

public interface IFrontEndWriter {

  void openNewTabWithUrl(String url);

  void redirectTo(IApplicationInstanceTarget applicationInstanceTarget);

  void redirectToUrl(String url);

  void saveFile(byte[] bytes);

  void writeTextToClipboard(String text);
}
