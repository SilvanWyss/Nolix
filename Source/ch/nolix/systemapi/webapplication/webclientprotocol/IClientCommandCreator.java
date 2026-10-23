/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webapplication.webclientprotocol;

import ch.nolix.baseapi.document.chainednode.ChainedNode;
import ch.nolix.baseapi.net.target.IServerTarget;

/**
 * @author Silvan Wyss
 */
public interface IClientCommandCreator {
  /**
   * @param cookieName
   * @param cookieValue
   * @return a new command to add or set a cookie with the given cookieName and
   *         cookieValue
   */
  ChainedNode createAddOrSetCookieCommand(String cookieName, String cookieValue);

  /**
   * @param cookieName
   * @return a new command to delete the cookie with the given cookieName
   */
  ChainedNode createDeleteCookieCommand(String cookieName);

  /**
   * @param url
   * @return a new command to open a new tab with the given url
   */
  ChainedNode createOpenNewTabCommand(String url);

  /**
   * @param serverTarget
   * @return a new command to redirect to the given serverTarget
   */
  ChainedNode createRedirectCommand(IServerTarget serverTarget);

  /**
   * @param url
   * @return a new command to redirect to the given url
   */
  ChainedNode createRedirectCommand(String url);

  /**
   * @param content
   * @return a new command to save a file with the given content
   */
  ChainedNode createSaveFileCommand(byte[] content);

  /**
   * @param text
   * @return a new command to write the given text to the clipboard
   */
  ChainedNode createWriteTextToClipBoardCommand(String text);
}
