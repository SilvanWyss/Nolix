/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.protocol;

import java.nio.charset.StandardCharsets;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.chainednode.ImmutableChainedNode;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.document.chainednode.ChainedNode;
import ch.nolix.baseapi.net.target.IServerTarget;
import ch.nolix.systemapi.webapplication.basewebclientprotocol.CommandProtocol;
import ch.nolix.systemapi.webapplication.basewebclientprotocol.ObjectProtocol;
import ch.nolix.systemapi.webapplication.webclientprotocol.IClientCommandCreator;

/**
 * @author Silvan Wyss
 */
public final class ClientCommandCreator implements IClientCommandCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createAddOrSetCookieCommand(final String name, final String value) {
    return //
    ImmutableChainedNode.withHeaderAndChildNodes(
      CommandProtocol.SET_OR_ADD_COOKIE_WITH_NAME_AND_VALUE,
      ImmutableList.withElements(
        ImmutableChainedNode.withHeader(name),
        ImmutableChainedNode.withHeader(value)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createDeleteCookieCommand(final String cookieName) {
    return //
    ImmutableChainedNode.withHeaderAndChildNode(CommandProtocol.DELETE_COOKIE_BY_NAME,
      ImmutableChainedNode.withHeader(cookieName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createOpenNewTabCommand(final String url) {
    return //
    ImmutableChainedNode.withHeaderAndChildNode(
      CommandProtocol.OPEN_NEW_TAB,
      ImmutableChainedNode.withHeaderAndChildNode(
        ObjectProtocol.URL,
        ImmutableChainedNode.withHeader(url)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createRedirectCommand(final IServerTarget serverTarget) {
    final var url = serverTarget.toUrl();

    return createRedirectCommand(url);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createRedirectCommand(final String url) {
    return ImmutableChainedNode.withHeaderAndChildNode(CommandProtocol.REDIRECT, ImmutableChainedNode.withHeader(url));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createSaveFileCommand(final byte[] bytes) {
    return //
    ImmutableChainedNode.withHeaderAndChildNodes(
      CommandProtocol.SAVE_FILE,
      ImmutableNode.withHeader(new String(bytes, StandardCharsets.UTF_8)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createWriteTextToClipBoardCommand(final String text) {
    return ImmutableChainedNode.withHeaderAndChildNode(CommandProtocol.WRITE_TEXT_TO_CLIPBOARD,
      ImmutableChainedNode.withHeader(text));
  }
}
