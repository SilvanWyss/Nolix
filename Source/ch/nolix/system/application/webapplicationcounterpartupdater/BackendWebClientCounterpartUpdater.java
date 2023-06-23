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
import ch.nolix.coreapi.functionapi.genericfunctionapi.IBooleanGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.webapi.cssapi.ICSS;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.application.webapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;

//class
public final class BackendWebClientCounterpartUpdater {
	
	//static method
	public static BackendWebClientCounterpartUpdater forCounterpartRunner(
		final IElementTaker<IContainer<? extends IChainedNode>> counterpartRunner,
		final IBooleanGetter openStateRequester
	) {
		return new BackendWebClientCounterpartUpdater(counterpartRunner, openStateRequester);
	}
	
	//attribute
	private final IBooleanGetter openStateRequester;
	
	//attribute
	private final IElementTaker<IContainer<? extends IChainedNode>> counterpartRunner;
	
	//constructor
	private BackendWebClientCounterpartUpdater(
		final IElementTaker<IContainer<? extends IChainedNode>> counterpartRunner,
		final IBooleanGetter openStateRequester
	) {
		
		GlobalValidator.assertThat(openStateRequester).thatIsNamed("open state requester").isNotNull();
		GlobalValidator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();
		
		this.openStateRequester = openStateRequester;
		this.counterpartRunner = counterpartRunner;
	}
	
	//method
	public void updateCounterpartFromWebGUI(final IWebGUI<?> webGUI) {
		
		webGUI.applyStyleIfHasStyle();
		
		final var updateCommands = createUpdateCommandsFromWebGUI(webGUI);
		
		if (openStateRequester.getOutput()) {
			counterpartRunner.run(updateCommands);
		}
	}
	
	//method
	private IContainer<ChainedNode> createUpdateCommandsFromWebGUI(final IWebGUI<?> webGUI) {
		return
		ImmutableList.withElements(
			createSetTitleCommandFromWebGUI(webGUI),
			createSetIconCommandFromWebGUI(webGUI),
			createSetRootHTMLElementCommandFromWebGUI(webGUI),
			createSetCSSCommandFromWebGUI(webGUI),
			createSetEventFunctionsCommandFromWebGUI(webGUI),
			createSetUserInputFunctionsCommandFromWebGUI(webGUI)
		);
	}
	
	//method
	private ChainedNode createSetTitleCommandFromWebGUI(final IWebGUI<?> webGUI) {
		return createSetTitleCommandForTitle(webGUI.getTitle());
	}
	
	//method
	private ChainedNode createSetTitleCommandForTitle(final String title) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndChildNodesFromNodes(
				CommandProtocol.SET_TITLE,
				Node.withHeader(title)
			)
		);
	}
	
	//method
	private ChainedNode createSetIconCommandFromWebGUI(final IWebGUI<?> webGUI) {
		return createSetIconCommandForIcon(webGUI.getIcon());
	}
	
	//method
	private ChainedNode createSetIconCommandForIcon(final IImage icon) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndChildNode(CommandProtocol.SET_ICON, icon.getSpecification())
		);
	}
	
	//method
	private ChainedNode createSetRootHTMLElementCommandFromWebGUI(final IWebGUI<?> webGUI) {
		return createSetRootHTMLElementCommandFromHTMLElement(webGUI.getContent().toHTMLElement());
	}
	
	//method
	private ChainedNode createSetRootHTMLElementCommandFromHTMLElement(final IHtmlElement<?, ?> pHTMLElement) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndChildNode(
				CommandProtocol.SET_ROOT_HTML_ELEMENT,
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
		final IContainer<IHtmlElementEvent> pHTMLElementEventRegistrations
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
