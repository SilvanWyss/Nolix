//package declaration
package ch.nolix.system.application.webapplicationcounterpartupdater;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IBooleanGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.application.webapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebClientCounterpartUpdater {
	
	//constant
	private static final UpdateCommandCreator UPDATE_COMMAND_CREATOR = new UpdateCommandCreator(); 
	
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
			UPDATE_COMMAND_CREATOR.createSetCssCommandFromWebGui(webGui),
			UPDATE_COMMAND_CREATOR.createSetEventFunctionsCommandFromWebGui(webGui),
			UPDATE_COMMAND_CREATOR.createSetUserInputFunctionsCommandFromWebGui(webGui)
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
}
