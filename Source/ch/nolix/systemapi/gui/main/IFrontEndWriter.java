/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.main;

import ch.nolix.baseapi.net.target.IApplicationTarget;

/**
 * @author Silvan Wyss
 */
public interface IFrontEndWriter {
  void openNewTabWithUrl(String url);

  void redirectTo(IApplicationTarget applicationTarget);

  void redirectToUrl(String url);

  void saveFile(byte[] bytes);

  void writeTextToClipboard(String text);
}
