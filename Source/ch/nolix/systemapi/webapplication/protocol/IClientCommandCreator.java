/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webapplication.protocol;

import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.coreapi.net.target.IServerTarget;

/**
 * @author Silvan Wyss
 */
public interface IClientCommandCreator {
  IChainedNode createAddOrSetCookieCommand(String cookieName, String cookieValue);

  IChainedNode createDeleteCookieCommand(String cookieName);

  IChainedNode createOpenNewTabCommand(String url);

  IChainedNode createRedirectCommand(IServerTarget serverTarget);

  IChainedNode createRedirectCommand(String url);

  IChainedNode createSaveFileCommand(byte[] content);

  IChainedNode createWriteTextToClipBoardCommand(String text);
}
