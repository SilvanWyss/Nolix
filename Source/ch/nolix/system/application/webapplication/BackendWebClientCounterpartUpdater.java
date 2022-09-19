//package declaration
package ch.nolix.system.application.webapplication;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.system.application.webapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;

//class
final class BackendWebClientCounterpartUpdater {
	
	//static method
	public static BackendWebClientCounterpartUpdater forBackendWebClient(final BackendWebClient<?> backendWebClient) {
		return new BackendWebClientCounterpartUpdater(backendWebClient);
	}
	
	//attribute
	private final BackendWebClient<?> parentBackendWebClient;
	
	//constructor
	private BackendWebClientCounterpartUpdater(final BackendWebClient<?> parentBackendWebClient) {
		
		GlobalValidator.assertThat(parentBackendWebClient).thatIsNamed("parent backend web client").isNotNull();
		
		this.parentBackendWebClient = parentBackendWebClient;
	}
	
	//method
	public void updateCounterpartFromWebGUI(final IWebGUI<?> webGUI) {
		
		final var updateCommands = createUpdateCommandsFromWebGUI(webGUI);
		
		parentBackendWebClient.internalRunOnCounterpart(updateCommands);
	}
	
	//method
	private IContainer<ChainedNode> createUpdateCommandsFromWebGUI(final IWebGUI<?> webGUI) {
		return
		ImmutableList.withElements(
			createSetTitleCommandFromWebGUI(webGUI),
			createSetIconCommandFromWebGUI(webGUI),
			createSetRootHTMLElementCommandFromWebGUI(webGUI)
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
	private ChainedNode createSetRootHTMLElementCommandFromHTMLElement(final IHTMLElement<?, ?> pHTMLElement) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndChildNode(
				CommandProtocol.SET_ROOT_HTML_ELEMENT,
				ChainedNode.withHeader(pHTMLElement.toString())
			)
		);
	}
}
