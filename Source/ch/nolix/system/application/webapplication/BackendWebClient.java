//package declaration
package ch.nolix.system.application.webapplication;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.application.basewebapplication.BaseBackendWebClient;
import ch.nolix.system.application.webapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ControlCommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;

//class
public final class BackendWebClient<AC> extends BaseBackendWebClient<BackendWebClient<AC>, AC> {
	
	//attribute
	private final BackendWebClientCounterpartUpdater counterpartUpdater =
	BackendWebClientCounterpartUpdater.forBackendWebClient(this);
	
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
				runGUICommand(command.getNextNode());				
				break;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.COMMAND, command);
		}
	}
	
	//method
	void internalUpdateCounterpartFromWebGUI(final IWebGUI<?> webGUI) {
		counterpartUpdater.updateCounterpartFromWebGUI(webGUI);
	}
	
	//method
	void internalRunOnCounterpart(final IContainer<? extends IChainedNode> updateCommands) {
		runOnCounterpart(updateCommands);
	}
	
	//method
	private void runCommandOnControl(final IControl<?, ?> control, final IChainedNode command) {
		switch (command.getHeader()) {
			case ControlCommandProtocol.RUN_HTML_EVENT:
				runRunHTMLEventCommandOnControl(control, command);
				updateCounterpartIfOpen();
				break;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.COMMAND, command);
		}
	}
	
	//method
	private void runGUICommand(final IChainedNode pGUICommand) {
		switch (pGUICommand.getHeader()) {
			case ObjectProtocol.CONTROL_BY_FIXED_ID:
				
				final var command = pGUICommand.getNextNode();
				
				//TODO: pGUICommand.getSingleChildNodeHeader()
				final var controlFixedId = pGUICommand.getSingleChildNode().getHeader();
				
				final var session = (BackendWebClientSession<AC>)getRefCurrentSession();
				final var gui = session.getRefGUI();
				final var control = gui.getRefControlByFixedId(controlFixedId);
				
				runCommandOnControl(control, command);
				
				break;
			case CommandProtocol.SET_USER_INPUTS:
				
				final var session2 = (BackendWebClientSession<AC>)getRefCurrentSession();
				final var gui2 = session2.getRefGUI();
				final var controls2 = gui2.getRefControls();
				
				for (final var p : pGUICommand.getChildNodes()) {
					final var fixedControlId2 = p.getChildNodeAt1BasedIndex(1).getHeader();
					final var userInput = p.getChildNodeAt1BasedIndex(2).getHeaderOrEmptyString();
					final var control2 = controls2.getRefFirst(c -> c.hasFixedId(fixedControlId2));
					control2.setUserInput(userInput);
				}
				
				break;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument("GUI command", pGUICommand);
		}
	}
	
	//method
	private void runRunHTMLEventCommandOnControl(final IControl<?, ?> control, final IChainedNode runHTMLEventCommand) {
		
		//TODO: runHTMLEventCommand.getSingleChildNodeHeader();
		final var lHTMLEvent = runHTMLEventCommand.getSingleChildNode().getHeader();
		
		control.runHTMLEvent(lHTMLEvent);
	}
	
	//method
	private void updateCounterpartIfOpen() {
		if (isOpen()) {
			((BackendWebClientSession<AC>)getRefCurrentSession()).updateCounterpart();
		}
	}
}
