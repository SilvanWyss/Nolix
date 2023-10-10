//package declaration
package ch.nolix.system.application.webapplicationcounterpartupdater;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IBooleanGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
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
			UPDATE_COMMAND_CREATOR.createSetTitleCommandFromWebGui(webGui),
			UPDATE_COMMAND_CREATOR.createSetIconCommandFromWebGui(webGui),
			UPDATE_COMMAND_CREATOR.createSetRootHtmlElementCommandFromWebGui(webGui),
			UPDATE_COMMAND_CREATOR.createSetCssCommandFromWebGui(webGui),
			UPDATE_COMMAND_CREATOR.createSetEventFunctionsCommandFromWebGui(webGui),
			UPDATE_COMMAND_CREATOR.createSetUserInputFunctionsCommandFromWebGui(webGui)
		);
	}
}
