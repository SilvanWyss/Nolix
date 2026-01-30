/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.counterpartupdater;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.webapplication.counterpart.IUpdateCommandCreator;
import ch.nolix.systemapi.webapplication.protocol.CommandProtocol;
import ch.nolix.systemapi.webapplication.protocol.ObjectProtocol;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public final class UpdateCommandCreator implements IUpdateCommandCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createSetCssCommandForWebGui(final IWebGui<?> webGui) {
    final var css = webGui.getCss();

    return UpdateCommandCreatorHelper.createSetCssCommandFromCss(css);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createSetEventFunctionsCommandForWebGui(final IWebGui<?> webGui) {
    final var htmlElementEventRegistrations = webGui.getHtmlElementEventRegistrations();

    return //
    UpdateCommandCreatorHelper.createSetEventFunctionsCommandFromHtmlElementEventRegistrations(
      htmlElementEventRegistrations);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createSetIconCommandForWebGui(final IWebGui<?> webGui) {
    return createSetIconCommandForIcon(webGui.getIcon());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createSetIconCommandForIcon(final IImage icon) {
    return //
    ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNode(CommandProtocol.SET_ICON, icon.getSpecification()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createSetRootHtmlElementCommandForControl(final IControl<?, ?> control) {
    final var htmlElementId = control.getInternalId();
    final var htmlElement = control.getHtml();

    return UpdateCommandCreatorHelper.createSetHtmlElementCommandFromHtmlElement(htmlElementId, htmlElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createSetRootHtmlElementCommandForWebGui(final IWebGui<?> webGui) {
    final var htmlElement = webGui.getHtml();

    return UpdateCommandCreatorHelper.createSetRootHtmlElementCommandFromHtmlElement(htmlElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createSetTitleCommandForWebGui(final IWebGui<?> webGui) {
    return createSetTitleCommandForTitle(webGui.getTitle());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createSetTitleCommandForTitle(final String title) {
    return //
    ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNodesFromNodes(
        CommandProtocol.SET_TITLE,
        Node.withHeader(title)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IChainedNode createSetUserInputFunctionsCommandForWebGui(final IWebGui<?> webGui) {
    return UpdateCommandCreatorHelper.createSetUserInputFunctionsCommandForControls(webGui.getStoredControls());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IChainedNode> createUpdateCommandsForControls(
    final IContainer<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    Validator.assertThat(controls).thatIsNamed("controls").isNotEmpty();

    final var webGui = controls.getStoredFirst().getStoredParentGui();

    final ILinkedList<IChainedNode> updatedCommands = LinkedList.createEmpty();

    updatedCommands.addAtEnd(controls.getViewOf(this::createSetRootHtmlElementCommandForControl));

    if (updateConstellationOrStyle) {
      updatedCommands.addAtEnd(
        createSetCssCommandForWebGui(webGui),
        createSetEventFunctionsCommandForWebGui(webGui),
        createSetUserInputFunctionsCommandForWebGui(webGui));
    }

    return updatedCommands;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IChainedNode> createUpdateCommandsForWebGui(final IWebGui<?> webGui) {
    return //
    ImmutableList.withElements(
      createSetTitleCommandForWebGui(webGui),
      createSetIconCommandForWebGui(webGui),
      createSetRootHtmlElementCommandForWebGui(webGui),
      createSetCssCommandForWebGui(webGui),
      createSetEventFunctionsCommandForWebGui(webGui),
      createSetUserInputFunctionsCommandForWebGui(webGui));
  }
}
