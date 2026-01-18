/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.protocol;

import java.nio.charset.StandardCharsets;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.coreapi.net.target.IServerTarget;
import ch.nolix.systemapi.application.basewebapplicationprotocol.CommandProtocol;
import ch.nolix.systemapi.application.basewebapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.webapplication.protocol.IClientCommandCreator;

/**
 * @author Silvan Wyss
 */
public final class ClientCommandCreator implements IClientCommandCreator {
  @Override
  public IChainedNode createAddOrSetCookieCommand(final String name, final String value) {
    return //
    ChainedNode.withHeaderAndChildNodes(
      CommandProtocol.SET_OR_ADD_COOKIE_WITH_NAME_AND_VALUE,
      ImmutableList.withElements(
        ChainedNode.withHeader(name),
        ChainedNode.withHeader(value)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createDeleteCookieCommand(final String cookieName) {
    return //
    ChainedNode.withHeaderAndChildNode(CommandProtocol.DELETE_COOKIE_BY_NAME, ChainedNode.withHeader(cookieName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createOpenNewTabCommand(final String url) {
    return //
    ChainedNode.withHeaderAndChildNode(
      CommandProtocol.OPEN_NEW_TAB,
      ChainedNode.withHeaderAndChildNode(
        ObjectProtocol.URL,
        ChainedNode.withHeader(url)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createRedirectCommand(final IServerTarget serverTarget) {
    final var url = serverTarget.toUrl();

    return createRedirectCommand(url);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createRedirectCommand(final String url) {
    return ChainedNode.withHeaderAndChildNode(CommandProtocol.REDIRECT, ChainedNode.withHeader(url));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createSaveFileCommand(final byte[] bytes) {
    return //
    ChainedNode.withHeaderAndChildNodesFromNodes(
      CommandProtocol.SAVE_FILE,
      Node.withHeader(new String(bytes, StandardCharsets.UTF_8)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createWriteTextToClipBoardCommand(final String text) {
    return ChainedNode.withHeaderAndChildNode(CommandProtocol.WRITE_TEXT_TO_CLIPBOARD, ChainedNode.withHeader(text));
  }
}
