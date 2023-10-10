//package declaration
package ch.nolix.system.application.webapplicationcounterpartupdater;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.webapi.cssapi.ICss;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.application.webapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class BackendWebClientPartialCounterpartUpdater {
	
	//static method
	public static BackendWebClientPartialCounterpartUpdater forCounterpartRunner(
		final IElementTaker<IContainer<? extends IChainedNode>> counterpartRunner
	) {
		return new BackendWebClientPartialCounterpartUpdater(counterpartRunner);
	}
	
	//attribute
	private final IElementTaker<IContainer<? extends IChainedNode>> counterpartRunner;
	
	//constructor
	private BackendWebClientPartialCounterpartUpdater(
		final IElementTaker<IContainer<? extends IChainedNode>> counterpartRunner
	) {
		
		GlobalValidator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();
		
		this.counterpartRunner = counterpartRunner;
	}
	
	//method
	/**
	 * Updates the given control on the counterpart of the client of the web GUI of the given control.
	 * Updates only the content, not the look and the functions on the control.
	 * 
	 * @param control
	 */
	public void updateControlOnCounterpart(final IControl<?, ?> control) {
		
		final var updateCommands = createUpdateCommandsFromControl(control);
		
		counterpartRunner.run(updateCommands);
	}
	
	//method
	private IContainer<ChainedNode> createUpdateCommandsFromControl(final IControl<?, ?> control) {
		
		final var gui = control.getStoredParentGui();
		
		return
		ImmutableList.withElement(
			createSetRootHtmlElementCommandFromControl(control),
			createSetCssCommandFromWebGui(gui),
			createSetEventFunctionsCommandFromWebGui(gui),
			createSetUserInputFunctionsCommandFromWebGui(gui)
		);
	}
	
	//method
	private ChainedNode createSetRootHtmlElementCommandFromControl(final IControl<?, ?> control) {
		return createSetHtmlElementCommandFromHtmlElement(control.getInternalId(), control.getHtml());
	}
	
	//method
	private ChainedNode createSetHtmlElementCommandFromHtmlElement(
		final String htmlElementId,
		final IHtmlElement htmlElement
	) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndChildNodes(
				CommandProtocol.SET_HTML_ELEMENT,
				ChainedNode.withHeader(htmlElementId),
				ChainedNode.withHeader(htmlElement.toString())
			)
		);
	}
	
	//method
	private ChainedNode createSetCssCommandFromWebGui(final IWebGui<?> webGui) {
		return createSetCssCommandFromCss(webGui.getCss());
	}
	
	//method
	private ChainedNode createSetCssCommandFromCss(final ICss css) {
		return createSetCssCommandFromCss(css.toStringWithoutEnclosingBrackets());
	}
	
	//method
	private ChainedNode createSetCssCommandFromCss(final String css) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndChildNode(
				CommandProtocol.SET_CSS,
				ChainedNode.withHeader(css)
			)
		);
	}
	
	//method
	private ChainedNode createSetEventFunctionsCommandFromWebGui(final IWebGui<?> webGui) {
		return createSetEventFunctionsCommandFromHtmlElementEventRegistrations(webGui.getHtmlElementEventRegistrations());
	}
	
	//method
	private ChainedNode createSetEventFunctionsCommandFromHtmlElementEventRegistrations(
		final IContainer<IHtmlElementEvent> htmlElementEventRegistrations
	) {
		
		final var eventFunctions =
		htmlElementEventRegistrations.to(
			e -> Node.withChildNode(Node.withHeader(e.getHtmlElementId()), Node.withHeader(e.getHtmlEvent()))
		);
		
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndChildNodesFromNodes(
				CommandProtocol.SET_EVENT_FUNCTIONS,
				eventFunctions
			)
		);
	}
	
	//method
	private ChainedNode createSetUserInputFunctionsCommandFromWebGui(final IWebGui<?> webGui) {
		return createSetUserInputFunctionsCommandForControls(webGui.getStoredControls());
	}
	
	//method
	private ChainedNode createSetUserInputFunctionsCommandForControls(final IContainer<IControl<?, ?>> controls) {
		
		final var userInputFunctions = new LinkedList<ChainedNode>();
		
		for (final var c : controls) {
			final var userInputFunction = c.getOptionalJavaScriptUserInputFunction();
			if (userInputFunction.containsAny()) {
				userInputFunctions.addAtEnd(
					createUserInputFunctionFromControlAndString(c, userInputFunction.getStoredElement())
				);
			}
		}
		
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndChildNodes(
				CommandProtocol.SET_USER_INPUT_FUNCTIONS,
				userInputFunctions
			)
		);
	}
	
	//method
	private ChainedNode createUserInputFunctionFromControlAndString(final IControl<?, ?> control, final String string) {
		return ChainedNode.withChildNodesFromNodes(Node.withHeader(control.getInternalId()), Node.withHeader(string));
	}
}
