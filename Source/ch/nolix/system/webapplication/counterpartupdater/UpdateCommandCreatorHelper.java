/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.counterpartupdater;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.chainednode.ChainedNode;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
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

  public static ChainedNode createSetCssCommandFromCss(final ICss css) {
    return createSetCssCommandFromCss(css.toStringWithoutEnclosingBrackets());
  }

  public static ChainedNode createSetCssCommandFromCss(final String css) {
    return ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNode(
        CommandProtocol.SET_CSS,
        ChainedNode.withHeader(css)));
  }

  public static ChainedNode createSetHtmlElementCommandFromHtmlElement(
    final String htmlElementId,
    final IHtmlElement htmlElement) {
    return ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNodes(
        CommandProtocol.SET_HTML_ELEMENT,
        ChainedNode.withHeader(htmlElementId),
        ChainedNode.withHeader(htmlElement.toString())));
  }

  public static ChainedNode createSetEventFunctionsCommandFromHtmlElementEventRegistrations(
    final ExtendedIterable<IHtmlElementEvent> htmlElementEventRegistrations) {
    final var eventFunctionsView = //
    htmlElementEventRegistrations.getViewOf(
      e -> ImmutableNode.withChildNodes(
        ImmutableNode.withHeader(e.getHtmlElementId()),
        ImmutableNode.withHeader(e.getHtmlEvent())));

    return ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNodesFromNodes(
        CommandProtocol.SET_EVENT_FUNCTIONS,
        eventFunctionsView));
  }

  public static ChainedNode createSetRootHtmlElementCommandFromHtmlElement(final IHtmlElement htmlElement) {
    return ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNode(
        CommandProtocol.SET_ROOT_HTML_ELEMENT,
        ChainedNode.withHeader(htmlElement.toString())));
  }

  public static ChainedNode createSetUserInputFunctionsCommandForControls(
    final ExtendedIterable<Control<?, ?>> controls) {
    final ILinkedList<IChainedNode> userInputFunctions = LinkedList.createEmpty();

    for (final var c : controls) {
      final var userInputFunction = c.getOptionalJavaScriptUserInputFunction();
      if (userInputFunction.isPresent()) {
        userInputFunctions.addAtEnd(
          UpdateCommandCreatorHelper.createUserInputFunctionFromControlAndString(c, userInputFunction.get()));
      }
    }

    return ChainedNode.withHeaderAndNextNode(
      ObjectProtocol.GUI,
      ChainedNode.withHeaderAndChildNodes(
        CommandProtocol.SET_USER_INPUT_FUNCTIONS,
        userInputFunctions));
  }

  public static ChainedNode createUserInputFunctionFromControlAndString(
    final Control<?, ?> control,
    final String string) {
    return //
    ChainedNode.withChildNodes(ImmutableNode.withHeader(control.getInternalId()), ImmutableNode.withHeader(string));
  }
}
