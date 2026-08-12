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
  ChainedNode createAddOrSetCookieCommand(String cookieName, String cookieValue);

  ChainedNode createDeleteCookieCommand(String cookieName);

  ChainedNode createOpenNewTabCommand(String url);

  ChainedNode createRedirectCommand(IServerTarget serverTarget);

  ChainedNode createRedirectCommand(String url);

  ChainedNode createSaveFileCommand(byte[] content);

  ChainedNode createWriteTextToClipBoardCommand(String text);
}
