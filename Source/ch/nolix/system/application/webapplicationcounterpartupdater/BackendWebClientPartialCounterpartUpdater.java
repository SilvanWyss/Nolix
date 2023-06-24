//package declaration
package ch.nolix.system.application.webapplicationcounterpartupdater;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.application.webapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

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
		return ImmutableList.withElement(createSetRootHtmlElementCommandFromControl(control));
	}
	
	//method
	private ChainedNode createSetRootHtmlElementCommandFromControl(final IControl<?, ?> control) {
		return createSetHtmlElementCommandFromHtmlElement(control.getInternalId(), control.toHtmlElement());
	}
	
	//method
	private ChainedNode createSetHtmlElementCommandFromHtmlElement(
		final String htmlElementId,
		final IHtmlElement<?, ?> htmlElement
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
}
