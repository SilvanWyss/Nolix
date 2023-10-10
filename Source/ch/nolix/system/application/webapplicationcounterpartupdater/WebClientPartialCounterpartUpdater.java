//package declaration
package ch.nolix.system.application.webapplicationcounterpartupdater;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class WebClientPartialCounterpartUpdater {
	
	//constant
	private static final UpdateCommandCreator UPDATE_COMMAND_CREATOR = new UpdateCommandCreator(); 
	
	//static method
	public static WebClientPartialCounterpartUpdater forCounterpartRunner(
		final IElementTaker<IContainer<? extends IChainedNode>> counterpartRunner
	) {
		return new WebClientPartialCounterpartUpdater(counterpartRunner);
	}
	
	//attribute
	private final IElementTaker<IContainer<? extends IChainedNode>> counterpartRunner;
	
	//constructor
	private WebClientPartialCounterpartUpdater(
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
			UPDATE_COMMAND_CREATOR.createSetRootHtmlElementCommandFromControl(control),
			UPDATE_COMMAND_CREATOR.createSetCssCommandFromWebGui(gui),
			UPDATE_COMMAND_CREATOR.createSetEventFunctionsCommandFromWebGui(gui),
			UPDATE_COMMAND_CREATOR.createSetUserInputFunctionsCommandFromWebGui(gui)
		);
	}
}
