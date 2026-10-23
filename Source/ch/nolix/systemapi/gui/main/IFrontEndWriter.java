/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.main;

import ch.nolix.baseapi.net.target.IApplicationTarget;

/**
 * @author Silvan Wyss
 */
public interface IFrontEndWriter {
  /**
   * Opens a new tab with the given url in the front end.
   * 
   * @param url
   */
  void openNewTabWithUrl(String url);

  /**
   * Redirects the front end to the given applicationTarget.
   * 
   * @param applicationTarget
   */
  void redirectTo(IApplicationTarget applicationTarget);

  /**
   * Redirects the front end to the given url.
   * 
   * @param url
   */
  void redirectToUrl(String url);

  /**
   * Lets the front end save a file with the given bytes.
   * 
   * @param bytes
   */
  void saveFile(byte[] bytes);

  /**
   * Writes the given text to the clipboard of the front end.
   * 
   * @param text
   */
  void writeTextToClipboard(String text);
}
