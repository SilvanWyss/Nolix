package ch.nolix.system.webapplication.counterpartupdater;

import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
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
  @Override
  public IChainedNode createSetCssCommandFromWebGui(final IWebGui<?> webGui) {
    final var css = webGui.getCss();

    return UpdateCommandCreatorHelper.createSetCssCommandFromCss(css);
  }

  @Override
  public IChainedNode createSetEventFunctionsCommandFromWebGui(final IWebGui<?> webGui) {
    final var htmlElementEventRegistrations = webGui.getHtmlElementEventRegistrations();

    return //
    UpdateCommandCreatorHelper.createSetEventFunctionsCommandFromHtmlElementEventRegistrations(
      htmlElementEventRegistrations);
  }

  @Override
  public IChainedNode createSetIconCommandFromWebGui(final IWebGui<?> webGui) {
    return createSetIconCommandForIcon(webGui.getIcon());
  }

  @Override
  public IChainedNode createSetIconCommandForIcon(final IImage icon) {
    return //
    ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNode(CommandProtocol.SET_ICON, icon.getSpecification()));
  }

  @Override
  public IChainedNode createSetRootHtmlElementCommandFromControl(final IControl<?, ?> control) {
    final var htmlElementId = control.getInternalId();
    final var htmlElement = control.getHtml();

    return UpdateCommandCreatorHelper.createSetHtmlElementCommandFromHtmlElement(htmlElementId, htmlElement);
  }

  @Override
  public IChainedNode createSetRootHtmlElementCommandFromWebGui(final IWebGui<?> webGui) {
    final var htmlElement = webGui.getHtml();

    return UpdateCommandCreatorHelper.createSetRootHtmlElementCommandFromHtmlElement(htmlElement);
  }

  @Override
  public IChainedNode createSetTitleCommandFromWebGui(final IWebGui<?> webGui) {
    return createSetTitleCommandForTitle(webGui.getTitle());
  }

  @Override
  public IChainedNode createSetTitleCommandForTitle(final String title) {
    return ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNodesFromNodes(
        CommandProtocol.SET_TITLE,
        Node.withHeader(title)));
  }

  @Override
  public IChainedNode createSetUserInputFunctionsCommandFromWebGui(final IWebGui<?> webGui) {
    return UpdateCommandCreatorHelper.createSetUserInputFunctionsCommandForControls(webGui.getStoredControls());
  }
}
