//package declaration
package ch.nolix.system.application.webapplication;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.application.basewebapplication.BaseBackendWebClient;
import ch.nolix.system.application.webapplicationprotocol.ControlCommandProtocol;
import ch.nolix.system.application.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;

//class
public final class BackendWebClient<AC> extends BaseBackendWebClient<BackendWebClient<AC>, AC> {
	
	//attribute
	private final BackendWebClientCounterpartUpdater counterpartUpdates =
	BackendWebClientCounterpartUpdater.forBackendWebClient(this);
	
	//method
	@Override
	protected Node getDataFromHereFromBaseBackendWebClient(final ChainedNode request) {
		throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.REQUEST, request);
	}
	
	//method
	@Override
	protected void runHereOnBaseBackendWebClient(final ChainedNode command) {
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
		counterpartUpdates.updateCounterpartFromWebGUI(webGUI);
	}
	
	//method
	void internalRunOnCounterpart(final IContainer<ChainedNode> updateCommands) {
		runOnCounterpart(updateCommands);
	}
	
	//method
	private void runCommandOnControl(final IControl<?, ?> control, final ChainedNode command) {
		switch (command.getHeader()) {
			case ControlCommandProtocol.NOTE_KEY_TYPING:
				final var key = Key.fromSpecification(command.getSingleChildNodeAsNode());
				control.noteKeyTyping(key);
				break;
			case ControlCommandProtocol.NOTE_LEFT_MOUSE_BUTTON_PRESS:
				control.noteLeftMouseButtonPress();
				break;
			case ControlCommandProtocol.NOTE_LEFT_MOUSE_BUTTON_RELEASE:
				control.noteLeftMouseButtonRelease();
				break;
			case ControlCommandProtocol.NOTE_MOUSE_WHEEL_PRESS:
				control.noteMouseWheelPress();
				break;
			case ControlCommandProtocol.NOTE_MOUSE_WHEEL_RELEASE:
				control.noteMouseWheelRelease();
				break;
			case ControlCommandProtocol.NOTE_RIGHT_MOUSE_BUTTON_PRESS:
				control.noteRightMouseButtonPress();
				break;
			case ControlCommandProtocol.NOTE_RIGHT_MOUSE_BUTTON_RELEASE:
				control.noteRightMouseButtonRelease();
				break;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.COMMAND, command);
		}
	}
	
	//method
	private void runGUICommand(final ChainedNode pGUICommand) {
		switch (pGUICommand.getHeader()) {
			case ObjectProtocol.CONTROL_BY_FIXED_ID:
				
				final var command = pGUICommand.getNextNode();
				final var controlFixedId = pGUICommand.getSingleChildNodeHeader();
				final var session = (BackendWebClientSession<AC>)getRefCurrentSession();
				final var gui = session.getRefGUI();
				final var control = gui.getRefControlByFixedId(controlFixedId);
				
				runCommandOnControl(control, command);
				
				break;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument("GUI command", pGUICommand);
		}
	}
}
