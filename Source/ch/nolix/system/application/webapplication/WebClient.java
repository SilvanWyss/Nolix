//package declaration
package ch.nolix.system.application.webapplication;

//Java imports
import java.util.Base64;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.application.basewebapplication.BaseWebClient;
import ch.nolix.system.application.webapplicationcounterpartupdater.WebClientCounterpartUpdater;
import ch.nolix.system.application.webapplicationcounterpartupdater.WebClientPartialCounterpartUpdater;
import ch.nolix.system.application.webapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ControlCommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IUploader;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebClient<AC> extends BaseWebClient<WebClient<AC>, AC> {
	
	//method
	@Override
	protected INode<?> getDataFromHereFromBaseBackendWebClient(final IChainedNode request) {
		throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.REQUEST, request);
	}
	
	//method
	@Override
	protected void runHereOnBaseBackendWebClient(final IChainedNode command) {
		switch (command.getHeader()) { //NOSONAR: A switch-statement allows to add probable additional cases.
			case ObjectProtocol.GUI:
				runGuiCommand(command.getNextNode());				
				break;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.COMMAND, command);
		}
	}
	
	//method
	void internalUpdateControlOnCounterpart(final IControl<?, ?> control) {
		WebClientPartialCounterpartUpdater
		.forCounterpartRunner(this::runOnCounterpart)
		.updateControlOnCounterpart(control);
	}
	
	//method
	void internalUpdateCounterpartFromWebGui(final IWebGui<?> webGui) {
		WebClientCounterpartUpdater
		.forCounterpartRunner(this::runOnCounterpart, this::isOpen)
		.updateCounterpartFromWebGui(webGui);
	}
	
	//method
	void internalRunOnCounterpart(final IContainer<? extends IChainedNode> updateCommands) {
		runOnCounterpart(updateCommands);
	}
	
	//method
	private void runCommandOnControl(final IControl<?, ?> control, final IChainedNode command) {
		switch (command.getHeader()) { //NOSONAR: A switch-statement allows to add probable additional cases.
			case ControlCommandProtocol.RUN_HTML_EVENT:
				runRunHtmlEventCommandOnControl(control, command);
				updateCounterpartIfOpen();
				break;
			case ControlCommandProtocol.SET_FILE:
				
				final var uploader = (IUploader)control;
				
				final var fileString = command.getSingleChildNodeHeader();
				
				//Important: The received fileString is a Base 64 encoded string.
				final var bytes = Base64.getDecoder().decode(fileString.substring(fileString.indexOf(',') + 1));
				
				uploader.technicalSetFile(bytes);
				
				break;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.COMMAND, command);
		}
	}
	
	//method
	private void runControlCommand(final IChainedNode guiCommand) {
		
		final var command = guiCommand.getNextNode();
		final var internalControlId = guiCommand.getSingleChildNodeHeader();
		final var session = (WebClientSession<AC>)getStoredCurrentSession();
		final var gui = session.getStoredGui();
		final var controls = gui.getStoredControls();
		final var control = controls.getStoredFirstOrNull(c -> c.hasInternalId(internalControlId));
		
		//The Control could be removed on the server in the meanwhile.
		if (control != null) {
			runCommandOnControl(control, command);
		}
	}
	
	//method
	private void runGuiCommand(final IChainedNode guiCommand) {
		switch (guiCommand.getHeader()) {
			case ObjectProtocol.CONTROL_BY_FIXED_ID:
				runControlCommand(guiCommand);
				break;
			case CommandProtocol.SET_USER_INPUTS:
				runSetUserInputsCommand(guiCommand);
				break;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument("GUI command", guiCommand);
		}
	}
	
	//method
	private void runRunHtmlEventCommandOnControl(final IControl<?, ?> control, final IChainedNode runHtmlEventCommand) {
		
		final var htmlEvent = runHtmlEventCommand.getSingleChildNodeHeader();
		
		control.runHtmlEvent(htmlEvent);
	}
	
	//method
	private void runSetUserInputsCommand(final IChainedNode guiCommand) {
		
		final var session = (WebClientSession<AC>)getStoredCurrentSession();
		final var gui = session.getStoredGui();
		final var controls = gui.getStoredControls();
		
		for (final var p : guiCommand.getChildNodes()) {
			
			final var internalControlId = p.getChildNodeAt1BasedIndex(1).getHeader();
			final var userInput = p.getChildNodeAt1BasedIndex(2).getHeaderOrEmptyString();
			final var control = controls.getStoredFirstOrNull(c -> c.hasInternalId(internalControlId));
			
			//The Control could be removed on the server in the meanwhile.
			if (control != null) {
				control.setUserInput(userInput);
			}
		}
	}
	
	//method
	private void updateCounterpartIfOpen() {
		if (isOpen()) {
			((WebClientSession<AC>)getStoredCurrentSession()).refresh();
		}
	}
}
