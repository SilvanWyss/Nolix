/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.counterpartupdater;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.chainednode.ImmutableChainedNode;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.chainednode.ChainedNode;
import ch.nolix.baseapi.web.cssmodel.ICss;
import ch.nolix.baseapi.web.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.webapplication.webclientprotocol.CommandProtocol;
import ch.nolix.systemapi.webapplication.webclientprotocol.ObjectProtocol;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public final class UpdateCommandCreatorHelper {
  private UpdateCommandCreatorHelper() {
  }

  public static ImmutableChainedNode createSetCssCommandFromCss(final ICss css) {
    return createSetCssCommandFromCss(css.toStringWithoutEnclosingBrackets());
  }

  public static ImmutableChainedNode createSetCssCommandFromCss(final String css) {
    return ImmutableChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ImmutableChainedNode.withHeaderAndChildNode(
        CommandProtocol.SET_CSS,
        ImmutableChainedNode.withHeader(css)));
  }

  public static ImmutableChainedNode createSetHtmlElementCommandFromHtmlElement(
    final String htmlElementId,
    final IHtmlElement htmlElement) {
    return ImmutableChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ImmutableChainedNode.withHeaderAndChildNodes(
        CommandProtocol.SET_HTML_ELEMENT,
        ImmutableChainedNode.withHeader(htmlElementId),
        ImmutableChainedNode.withHeader(htmlElement.toString())));
  }

  public static ImmutableChainedNode createSetEventFunctionsCommandFromHtmlElementEventRegistrations(
    final ExtendedIterable<IHtmlElementEvent> htmlElementEventRegistrations) {
    final var eventFunctionsView = //
    htmlElementEventRegistrations.getViewOf(
      e -> ImmutableNode.withChildNodes(
        ImmutableNode.withHeader(e.getHtmlElementId()),
        ImmutableNode.withHeader(e.getHtmlEvent())));

    return ImmutableChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ImmutableChainedNode.withHeaderAndChildNodesFromNodes(
        CommandProtocol.SET_EVENT_FUNCTIONS,
        eventFunctionsView));
  }

  public static ImmutableChainedNode createSetRootHtmlElementCommandFromHtmlElement(final IHtmlElement htmlElement) {
    return ImmutableChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ImmutableChainedNode.withHeaderAndChildNode(
        CommandProtocol.SET_ROOT_HTML_ELEMENT,
        ImmutableChainedNode.withHeader(htmlElement.toString())));
  }

  public static ImmutableChainedNode createSetUserInputFunctionsCommandForControls(
    final ExtendedIterable<Control<?, ?>> controls) {
    final ILinkedList<ChainedNode> userInputFunctions = LinkedList.createEmpty();

    for (final var c : controls) {
      final var userInputFunction = c.getOptionalJavaScriptUserInputFunction();
      if (userInputFunction.isPresent()) {
        userInputFunctions.addAtEnd(
          UpdateCommandCreatorHelper.createUserInputFunctionFromControlAndString(c, userInputFunction.get()));
      }
    }

    return ImmutableChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ImmutableChainedNode.withHeaderAndChildNodes(
        CommandProtocol.SET_USER_INPUT_FUNCTIONS,
        userInputFunctions));
  }

  public static ImmutableChainedNode createUserInputFunctionFromControlAndString(
    final Control<?, ?> control,
    final String string) {
    return //
    ImmutableChainedNode.withChildNodes(ImmutableNode.withHeader(control.getInternalId()), ImmutableNode.withHeader(string));
  }
}
