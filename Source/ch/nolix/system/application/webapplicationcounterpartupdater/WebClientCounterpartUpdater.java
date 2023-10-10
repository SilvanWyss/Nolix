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
import ch.nolix.coreapi.webapi.cssapi.ICss;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.application.webapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebClientCounterpartUpdater {
	
	//static method
	public static WebClientCounterpartUpdater forCounterpartRunner(
		final IElementTaker<IContainer<? extends IChainedNode>> counterpartRunner,
		final IBooleanGetter openStateRequester
	) {
		return new WebClientCounterpartUpdater(counterpartRunner, openStateRequester);
	}
	
	//attribute
	private final IBooleanGetter openStateRequester;
	
	//attribute
	private final IElementTaker<IContainer<? extends IChainedNode>> counterpartRunner;
	
	//constructor
	private WebClientCounterpartUpdater(
		final IElementTaker<IContainer<? extends IChainedNode>> counterpartRunner,
		final IBooleanGetter openStateRequester
	) {
		
		GlobalValidator.assertThat(openStateRequester).thatIsNamed("open state requester").isNotNull();
		GlobalValidator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();
		
		this.openStateRequester = openStateRequester;
		this.counterpartRunner = counterpartRunner;
	}
	
	//method
	public void updateCounterpartFromWebGui(final IWebGui<?> webGui) {
		
		webGui.applyStyleIfHasStyle();
		
		final var updateCommands = createUpdateCommandsFromWebGui(webGui);
		
		if (openStateRequester.getOutput()) {
			counterpartRunner.run(updateCommands);
		}
	}
	
	//method
	private IContainer<ChainedNode> createUpdateCommandsFromWebGui(final IWebGui<?> webGui) {
		return
		ImmutableList.withElement(
			createSetTitleCommandFromWebGui(webGui),
			createSetIconCommandFromWebGui(webGui),
			createSetRootHtmlElementCommandFromWebGui(webGui),
			createSetCssCommandFromWebGui(webGui),
			createSetEventFunctionsCommandFromWebGui(webGui),
			createSetUserInputFunctionsCommandFromWebGui(webGui)
		);
	}
	
	//method
	private ChainedNode createSetTitleCommandFromWebGui(final IWebGui<?> webGui) {
		return createSetTitleCommandForTitle(webGui.getTitle());
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
	private ChainedNode createSetIconCommandFromWebGui(final IWebGui<?> webGui) {
		return createSetIconCommandForIcon(webGui.getIcon());
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
	private ChainedNode createSetRootHtmlElementCommandFromWebGui(final IWebGui<?> webGui) {
		return createSetRootHtmlElementCommandFromHtmlElement(webGui.getHtml());
	}
	
	//method
	private ChainedNode createSetRootHtmlElementCommandFromHtmlElement(final IHtmlElement htmlElement) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndChildNode(
				CommandProtocol.SET_ROOT_HTML_ELEMENT,
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
