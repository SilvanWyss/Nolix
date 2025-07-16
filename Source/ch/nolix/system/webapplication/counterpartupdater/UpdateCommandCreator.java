package ch.nolix.system.webapplication.counterpartupdater;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.webapi.cssapi.ICss;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webapplicationapi.counterpartapi.IUpdateCommandCreator;
import ch.nolix.systemapi.webapplicationapi.protocol.CommandProtocol;
import ch.nolix.systemapi.webapplicationapi.protocol.ObjectProtocol;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

public final class UpdateCommandCreator implements IUpdateCommandCreator {

  @Override
  public IChainedNode createSetCssCommandFromWebGui(final IWebGui<?> webGui) {
    return createSetCssCommandFromCss(webGui.getCss());
  }

  @Override
  public IChainedNode createSetEventFunctionsCommandFromWebGui(final IWebGui<?> webGui) {
    return createSetEventFunctionsCommandFromHtmlElementEventRegistrations(webGui.getHtmlElementEventRegistrations());
  }

  @Override
  public IChainedNode createSetIconCommandFromWebGui(final IWebGui<?> webGui) {
    return createSetIconCommandForIcon(webGui.getIcon());
  }

  @Override
  public IChainedNode createSetIconCommandForIcon(final IImage icon) {
    return ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNode(CommandProtocol.SET_ICON, icon.getSpecification()));
  }

  @Override
  public IChainedNode createSetRootHtmlElementCommandFromControl(final IControl<?, ?> control) {
    return createSetHtmlElementCommandFromHtmlElement(control.getInternalId(), control.getHtml());
  }

  @Override
  public IChainedNode createSetRootHtmlElementCommandFromWebGui(final IWebGui<?> webGui) {
    return createSetRootHtmlElementCommandFromHtmlElement(webGui.getHtml());
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
    return createSetUserInputFunctionsCommandForControls(webGui.getStoredControls());
  }

  private ChainedNode createSetCssCommandFromCss(final ICss css) {
    return createSetCssCommandFromCss(css.toStringWithoutEnclosingBrackets());
  }

  private ChainedNode createSetCssCommandFromCss(final String css) {
    return ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNode(
        CommandProtocol.SET_CSS,
        ChainedNode.withHeader(css)));
  }

  private ChainedNode createSetHtmlElementCommandFromHtmlElement(
    final String htmlElementId,
    final IHtmlElement htmlElement) {
    return ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNodes(
        CommandProtocol.SET_HTML_ELEMENT,
        ChainedNode.withHeader(htmlElementId),
        ChainedNode.withHeader(htmlElement.toString())));
  }

  private ChainedNode createSetEventFunctionsCommandFromHtmlElementEventRegistrations(
    final IContainer<IHtmlElementEvent> htmlElementEventRegistrations) {

    final var eventFunctions = htmlElementEventRegistrations.to(
      e -> Node.withChildNode(Node.withHeader(e.getHtmlElementId()), Node.withHeader(e.getHtmlEvent())));

    return ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNodesFromNodes(
        CommandProtocol.SET_EVENT_FUNCTIONS,
        eventFunctions));
  }

  private ChainedNode createSetRootHtmlElementCommandFromHtmlElement(final IHtmlElement htmlElement) {
    return ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNode(
        CommandProtocol.SET_ROOT_HTML_ELEMENT,
        ChainedNode.withHeader(htmlElement.toString())));
  }

  private ChainedNode createSetUserInputFunctionsCommandForControls(final IContainer<IControl<?, ?>> controls) {

    final ILinkedList<IChainedNode> userInputFunctions = LinkedList.createEmpty();

    for (final var c : controls) {
      final var userInputFunction = c.getOptionalJavaScriptUserInputFunction();
      if (userInputFunction.isPresent()) {
        userInputFunctions.addAtEnd(
          createUserInputFunctionFromControlAndString(c, userInputFunction.get()));
      }
    }

    return ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNodes(
        CommandProtocol.SET_USER_INPUT_FUNCTIONS,
        userInputFunctions));
  }

  private ChainedNode createUserInputFunctionFromControlAndString(final IControl<?, ?> control, final String string) {
    return ChainedNode.withChildNodesFromNodes(Node.withHeader(control.getInternalId()), Node.withHeader(string));
  }
}
