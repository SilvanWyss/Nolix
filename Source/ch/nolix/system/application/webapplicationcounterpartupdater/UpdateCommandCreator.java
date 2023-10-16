//package declaration
package ch.nolix.system.application.webapplicationcounterpartupdater;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICss;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.application.webapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class UpdateCommandCreator {

  //method
  public ChainedNode createSetCssCommandFromWebGui(final IWebGui<?> webGui) {
    return createSetCssCommandFromCss(webGui.getCss());
  }

  //method
  public ChainedNode createSetEventFunctionsCommandFromWebGui(final IWebGui<?> webGui) {
    return createSetEventFunctionsCommandFromHtmlElementEventRegistrations(webGui.getHtmlElementEventRegistrations());
  }

  //method
  public ChainedNode createSetIconCommandFromWebGui(final IWebGui<?> webGui) {
    return createSetIconCommandForIcon(webGui.getIcon());
  }

  //method
  public ChainedNode createSetIconCommandForIcon(final IImage icon) {
    return ChainedNode.withHeaderAndNextNode(
        ObjectProtocol.GUI,
        ChainedNode.withHeaderAndChildNode(CommandProtocol.SET_ICON, icon.getSpecification()));
  }

  //method
  public ChainedNode createSetRootHtmlElementCommandFromControl(final IControl<?, ?> control) {
    return createSetHtmlElementCommandFromHtmlElement(control.getInternalId(), control.getHtml());
  }

  //method
  public ChainedNode createSetRootHtmlElementCommandFromWebGui(final IWebGui<?> webGui) {
    return createSetRootHtmlElementCommandFromHtmlElement(webGui.getHtml());
  }

  //method
  public ChainedNode createSetTitleCommandFromWebGui(final IWebGui<?> webGui) {
    return createSetTitleCommandForTitle(webGui.getTitle());
  }

  //method
  public ChainedNode createSetTitleCommandForTitle(final String title) {
    return ChainedNode.withHeaderAndNextNode(
        ObjectProtocol.GUI,
        ChainedNode.withHeaderAndChildNodesFromNodes(
            CommandProtocol.SET_TITLE,
            Node.withHeader(title)));
  }

  //method
  public ChainedNode createSetUserInputFunctionsCommandFromWebGui(final IWebGui<?> webGui) {
    return createSetUserInputFunctionsCommandForControls(webGui.getStoredControls());
  }

  //method
  private ChainedNode createSetCssCommandFromCss(final ICss css) {
    return createSetCssCommandFromCss(css.toStringWithoutEnclosingBrackets());
  }

  //method
  private ChainedNode createSetCssCommandFromCss(final String css) {
    return ChainedNode.withHeaderAndNextNode(
        ObjectProtocol.GUI,
        ChainedNode.withHeaderAndChildNode(
            CommandProtocol.SET_CSS,
            ChainedNode.withHeader(css)));
  }

  //method
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

  //method
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

  //method
  private ChainedNode createSetRootHtmlElementCommandFromHtmlElement(final IHtmlElement htmlElement) {
    return ChainedNode.withHeaderAndNextNode(
        ObjectProtocol.GUI,
        ChainedNode.withHeaderAndChildNode(
            CommandProtocol.SET_ROOT_HTML_ELEMENT,
            ChainedNode.withHeader(htmlElement.toString())));
  }

  //method
  private ChainedNode createSetUserInputFunctionsCommandForControls(final IContainer<IControl<?, ?>> controls) {

    final var userInputFunctions = new LinkedList<ChainedNode>();

    for (final var c : controls) {
      final var userInputFunction = c.getOptionalJavaScriptUserInputFunction();
      if (userInputFunction.containsAny()) {
        userInputFunctions.addAtEnd(
            createUserInputFunctionFromControlAndString(c, userInputFunction.getStoredElement()));
      }
    }

    return ChainedNode.withHeaderAndNextNode(
        ObjectProtocol.GUI,
        ChainedNode.withHeaderAndChildNodes(
            CommandProtocol.SET_USER_INPUT_FUNCTIONS,
            userInputFunctions));
  }

  //method
  private ChainedNode createUserInputFunctionFromControlAndString(final IControl<?, ?> control, final String string) {
    return ChainedNode.withChildNodesFromNodes(Node.withHeader(control.getInternalId()), Node.withHeader(string));
  }
}
