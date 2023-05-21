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
import ch.nolix.coreapi.webapi.cssapi.ICSS;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.system.application.webapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHTMLElementEvent;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;

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
	public void updateControlOnCounterpart(final IControl<?, ?> control) {
		
		final var updateCommands = createUpdateCommandsFromControl(control);
		
		counterpartRunner.run(updateCommands);
	}
	
	//method
	private IContainer<ChainedNode> createUpdateCommandsFromControl(final IControl<?, ?> control) {
		return
		ImmutableList.withElements(
			createSetRootHTMLElementCommandFromControl(control),
			createSetCSSCommandFromWebGUI(control.getOriParentGUI()),
			createSetEventFunctionsCommandFromWebGUI(control.getOriParentGUI()),
			createSetUserInputFunctionsCommandFromWebGUI(control.getOriParentGUI())
		);
	}
	
	//method
	private ChainedNode createSetRootHTMLElementCommandFromControl(final IControl<?, ?> control) {
		return createSetHTMLElementCommandFromHTMLElement(control.getInternalId(), control.toHTMLElement());
	}
	
	//method
	private ChainedNode createSetHTMLElementCommandFromHTMLElement(
		final String paramHTMLElementId,
		final IHTMLElement<?, ?> pHTMLElement
	) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndChildNodes(
				CommandProtocol.SET_HTML_ELEMENT,
				ChainedNode.withHeader(paramHTMLElementId),
				ChainedNode.withHeader(pHTMLElement.toString())
			)
		);
	}
	
	//method
	private ChainedNode createSetCSSCommandFromWebGUI(final IWebGUI<?> webGUI) {
		return createSetCSSCommandFromCSS(webGUI.getCSS());
	}
	
	//method
	private ChainedNode createSetCSSCommandFromCSS(final ICSS<?, ?> pCSS) {
		return createSetCSSCommandFromCSS(pCSS.toStringWithoutEnclosingBrackets());
	}
	
	//method
	private ChainedNode createSetCSSCommandFromCSS(final String pCSS) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndChildNode(
				CommandProtocol.SET_CSS,
				ChainedNode.withHeader(pCSS)
			)
		);
	}
	
	//method
	private ChainedNode createSetEventFunctionsCommandFromWebGUI(final IWebGUI<?> webGUI) {
		return createSetEventFunctionsCommandFromHTMLElementEventRegistrations(webGUI.getHTMLElementEventRegistrations());
	}
	
	//method
	private ChainedNode createSetEventFunctionsCommandFromHTMLElementEventRegistrations(
		final IContainer<IHTMLElementEvent> pHTMLElementEventRegistrations
	) {
		
		final var eventFunctions =
		pHTMLElementEventRegistrations.to(
			e -> Node.withChildNode(Node.withHeader(e.getHTMLElementId()), Node.withHeader(e.getHTMLEvent()))
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
	private ChainedNode createSetUserInputFunctionsCommandFromWebGUI(final IWebGUI<?> webGUI) {
		return createSetUserInputFunctionsCommandForControls(webGUI.getOriControls());
	}
	
	//method
	private ChainedNode createSetUserInputFunctionsCommandForControls(final IContainer<IControl<?, ?>> controls) {
		
		final var userInputFunctions = new LinkedList<ChainedNode>();
		
		for (final var c : controls) {
			final var userInputFunction = c.getOptionalJavaScriptUserInputFunction();
			if (userInputFunction.containsAny()) {
				userInputFunctions.addAtEnd(
					createUserInputFunctionFromControlAndString(c, userInputFunction.getOriElement())
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
