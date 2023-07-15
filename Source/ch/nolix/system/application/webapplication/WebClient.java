//package declaration
package ch.nolix.system.application.webapplication;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.application.basewebapplication.BaseBackendWebClient;
import ch.nolix.system.application.webapplicationcounterpartupdater.BackendWebClientCounterpartUpdater;
import ch.nolix.system.application.webapplicationcounterpartupdater.BackendWebClientPartialCounterpartUpdater;
import ch.nolix.system.application.webapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ControlCommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebClient<AC> extends BaseBackendWebClient<WebClient<AC>, AC> {
	
	//method
	@Override
	protected INode<?> getDataFromHereFromBaseBackendWebClient(final IChainedNode request) {
		throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.REQUEST, request);
	}
	
	//method
	@Override
	protected void runHereOnBaseBackendWebClient(final IChainedNode command) {
		switch (command.getHeader()) {
			case ObjectProtocol.GUI:
				runGuiCommand(command.getNextNode());				
				break;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.COMMAND, command);
		}
	}
	
	//method
	void internalUpdateControlOnCounterpart(final IControl<?, ?> control) {
		BackendWebClientPartialCounterpartUpdater
		.forCounterpartRunner(this::runOnCounterpart)
		.updateControlOnCounterpart(control);
	}
	
	//method
	void internalUpdateCounterpartFromWebGui(final IWebGui<?> webGui) {
		BackendWebClientCounterpartUpdater
		.forCounterpartRunner(this::runOnCounterpart, this::isOpen)
		.updateCounterpartFromWebGui(webGui);
	}
	
	//method
	void internalRunOnCounterpart(final IContainer<? extends IChainedNode> updateCommands) {
		runOnCounterpart(updateCommands);
	}
	
	//method
	private void runCommandOnControl(final IControl<?, ?> control, final IChainedNode command) {
		switch (command.getHeader()) {
			case ControlCommandProtocol.RUN_HTML_EVENT:
				runRunHtmlEventCommandOnControl(control, command);
				updateCounterpartIfOpen();
				break;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.COMMAND, command);
		}
	}
	
	//method
	private void runGuiCommand(final IChainedNode guiCommand) {
		switch (guiCommand.getHeader()) {
			case ObjectProtocol.CONTROL_BY_FIXED_ID:
				
				final var command = guiCommand.getNextNode();
				final var internalControlId = guiCommand.getSingleChildNodeHeader();
				final var session = (WebClientSession<AC>)getStoredCurrentSession();
				final var gui = session.getStoredGui();
				final var controls = gui.getStoredControls();
				final var control = controls.getStoredFirstOrNull(c -> c.hasInternalId(internalControlId));
				
				//The control could be removed on the server in the meanwhile.
				if (control != null) {
					runCommandOnControl(control, command);
				}
				
				break;
			case CommandProtocol.SET_USER_INPUTS:
				
				final var session2 = (WebClientSession<AC>)getStoredCurrentSession();
				final var gui2 = session2.getStoredGui();
				final var controls2 = gui2.getStoredControls();
				
				for (final var p : guiCommand.getChildNodes()) {
					
					final var internalControlId2 = p.getChildNodeAt1BasedIndex(1).getHeader();
					final var userInput = p.getChildNodeAt1BasedIndex(2).getHeaderOrEmptyString();
					final var control2 = controls2.getStoredFirstOrNull(c -> c.hasInternalId(internalControlId2));
					
					//The control could be removed on the server in the meanwhile.
					if (control2 != null) {
						control2.setUserInput(userInput);
					}
				}
				
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
	private void updateCounterpartIfOpen() {
		if (isOpen()) {
			((WebClientSession<AC>)getStoredCurrentSession()).updateCounterpart();
		}
	}
}
