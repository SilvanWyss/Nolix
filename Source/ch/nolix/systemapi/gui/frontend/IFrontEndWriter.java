/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.frontend;

import ch.nolix.coreapi.net.target.IApplicationInstanceTarget;

/**
 * @author Silvan Wyss
 */
public interface IFrontEndWriter {
  void openNewTabWithUrl(String url);

  void redirectTo(IApplicationInstanceTarget applicationInstanceTarget);

  void redirectToUrl(String url);

  void saveFile(byte[] bytes);

  void writeTextToClipboard(String text);
}
