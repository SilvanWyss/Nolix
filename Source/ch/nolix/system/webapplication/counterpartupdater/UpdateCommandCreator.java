/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.counterpartupdater;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.chainednode.ImmutableChainedNode;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.chainednode.ChainedNode;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PluralLowerCaseVariableNameCatalog;
import ch.nolix.systemapi.graphic.image.Image;
import ch.nolix.systemapi.webapplication.counterpart.IUpdateCommandCreator;
import ch.nolix.systemapi.webapplication.webclientprotocol.CommandProtocol;
import ch.nolix.systemapi.webapplication.webclientprotocol.ObjectProtocol;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public final class UpdateCommandCreator implements IUpdateCommandCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createSetCssCommandForWebGui(final IWebGui<?> webGui) {
    final var css = webGui.getCss();

    return UpdateCommandCreatorHelper.createSetCssCommandFromCss(css);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createSetEventFunctionsCommandForWebGui(final IWebGui<?> webGui) {
    final var htmlElementEventRegistrations = webGui.getHtmlElementEventRegistrations();

    return //
    UpdateCommandCreatorHelper.createSetEventFunctionsCommandFromHtmlElementEventRegistrations(
      htmlElementEventRegistrations);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createSetIconCommandForWebGui(final IWebGui<?> webGui) {
    return createSetIconCommandForIcon(webGui.getIcon());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createSetIconCommandForIcon(final Image icon) {
    return //
    ImmutableChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ImmutableChainedNode.withHeaderAndChildNode(CommandProtocol.SET_ICON, icon.getSpecification()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createSetRootHtmlElementCommandForControl(final Control<?, ?> control) {
    final var htmlElementId = control.getInternalId();
    final var htmlElement = control.getHtml();

    return UpdateCommandCreatorHelper.createSetHtmlElementCommandFromHtmlElement(htmlElementId, htmlElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createSetRootHtmlElementCommandForWebGui(final IWebGui<?> webGui) {
    final var htmlElement = webGui.getHtml();

    return UpdateCommandCreatorHelper.createSetRootHtmlElementCommandFromHtmlElement(htmlElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createSetTitleCommandForWebGui(final IWebGui<?> webGui) {
    return createSetTitleCommandForTitle(webGui.getTitle());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createSetTitleCommandForTitle(final String title) {
    return //
    ImmutableChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ImmutableChainedNode.withHeaderAndChildNodes(
        CommandProtocol.SET_TITLE,
        ImmutableNode.withHeader(title)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChainedNode createSetUserInputFunctionsCommandForWebGui(final IWebGui<?> webGui) {
    return UpdateCommandCreatorHelper.createSetUserInputFunctionsCommandForControls(webGui.getStoredControls());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<ChainedNode> createUpdateCommandsForControls(
    final ExtendedIterable<Control<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    Validator.assertThat(controls).thatIsNamed(PluralLowerCaseVariableNameCatalog.CONTROLS).isNotEmpty();

    final var webGui = controls.getStoredFirstNonNull().getStoredParentGui();

    final ILinkedList<ChainedNode> updatedCommands = LinkedList.createEmpty();

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
  public ExtendedIterable<ChainedNode> createUpdateCommandsForWebGui(final IWebGui<?> webGui) {
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
