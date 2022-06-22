//package declaration
package ch.nolix.system.application.baseguiapplication;

//Java imports
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.environment.localcomputer.PopupWindowProvider;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.application.main.FrontendClient;
import ch.nolix.system.gui.main.GUI;
import ch.nolix.systemapi.guiapi.inputapi.IInput;

//class
/**
 * @author Silvan Wyss
 * @date 2018-09-16
 * @param <BFGUIC> is the type of a {@link BaseFrontendGUIClient}.
 */
public abstract class BaseFrontendGUIClient<BFGUIC extends BaseFrontendGUIClient<BFGUIC>>
extends FrontendClient<BFGUIC> {
	
	//attribute
	private final BaseFrontendGUIClientGUIHandler mGUIHandler = new BaseFrontendGUIClientGUIHandler(this);
	
	//method
	/**
	 * Lets the counterpart of the current {@link BaseFrontendGUIClient} note the given input.
	 * Does nothing if the current {@link BaseFrontendGUIClient} is closed or not connected.
	 * 
	 * @param input
	 */
	public final void noteInputOnCounterpart(final IInput<?> input) {
		if (isOpen() && isConnected()) {
			noteInputOnCounterpartWhenIsOpenAndConnected(input);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Node getDataFromHere(final ChainedNode request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case ObjectProtocol.GUI:
				return getDataFromGUI(request.getNextNode());
			case CommandProtocol.GET_TEXT_FROM_CLIPBOARD:
				return Node.withHeader(getRefGUI().fromFrontEnd().getTextFromClipboard());
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.REQUEST, request);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void runHere(final ChainedNode command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case ObjectProtocol.GUI:
				mGUIHandler.runGUICommand(command.getNextNode());
				break;
			case CommandProtocol.OPEN_NEW_TAB:
				runOpenNewTabCommand(command);
				break;
			case CommandProtocol.REDIRECT:
				runRedirectCommand(command);
				break;
			case CommandProtocol.SAVE_FILE:
				saveFile(command.getOneAttributeAsString().getBytes(StandardCharsets.UTF_8));
				break;
			case CommandProtocol.SEND_OPTIONAL_FILE:
				sendOptionalFile();
				break;
			case CommandProtocol.SHOW_ERROR_MESSAGE:
				PopupWindowProvider.showErrorWindow(command.getOneAttributeAsString());
				break;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.COMMAND, command);
		}
	}
	
	//method
	/**
	 * @param pGUIDataRequest
	 * @return the data the given pGUIDataRequest requests
	 * from the {@link GUI} of the current {@link BaseFrontendGUIClient}.
	 * @throws InvalidArgumentException if the given pGUIDataRequest is not valid.
	 */
	private Node getDataFromGUI(final ChainedNode pGUIDataRequest) {
		
		//Enumerates the header of the given pGUIDataRequest.
		switch (pGUIDataRequest.getHeader()) {
			case ObjectProtocol.VIEW_AREA_SIZE:
				return Node.fromIntPair(getRefGUI().getViewAreaSize());
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument("GUI data request", pGUIDataRequest);
		}
	}
	
	//method
	/**
	 * @return the {@link GUI} of the current {@link BaseFrontendGUIClient}.
	 */
	private GUI<?> getRefGUI() {
		return mGUIHandler.getRefGUI();
	}
	
	//method
	private String getURLFromOpenNewTabCommand(final ChainedNode openNewTabCommand) {
		return openNewTabCommand.getFirstAttributeWithHeader(ObjectProtocol.URL).getOneAttributeHeader();
	}
	
	//method
	/**
	 * Lets the counterpart of the current {@link BaseFrontendGUIClient} note the given input for the case that
	 * the current {@link BaseFrontedGUIClient} is open and connected.
	 * 
	 * @param input
	 */
	private void noteInputOnCounterpartWhenIsOpenAndConnected(final IInput<?> input) {
		runOnCounterpart(
			ChainedNode.withHeaderAndAttributesFromNodes(CommandProtocol.NOTE_INPUT, input.getSpecification())
		);
	}

	//method
	/**
	 * Lets the current {@link BaseFrontendGUIClient} open a new tab with the given URL.
	 * 
	 * @param pURL
	 */
	private void openNewTabWithURL(final String pURL) {
		mGUIHandler.getRefGUI().onFrontEnd().openNewTabWithURL(pURL);
	}
	
	//method
	/**
	 * @return a file from the current {@link BaseFrontendGUIClient}.
	 */
	private SingleContainer<byte[]> readFileToBytes() {
		return getRefGUI().fromFrontEnd().readFileToBytes();
	}
	
	//method
	private void redirectTo(final String pURL) {
		mGUIHandler.getRefGUI().onFrontEnd().openNewTabWithURL(pURL);
	}
	
	//method
	/**
	 * Lets the current {@link BaseFrontendGUIClient} run the given openNewTabCommand.
	 * 
	 * @param openNewTabCommand
	 */
	private void runOpenNewTabCommand(final ChainedNode openNewTabCommand) {
		
		final var lURL = getURLFromOpenNewTabCommand(openNewTabCommand);
		
		openNewTabWithURL(lURL);
	}
	
	//method
	private void runRedirectCommand(final ChainedNode redirectCommand) {
		redirectTo(redirectCommand.getOneAttributeHeader());		
	}
	
	//method
	/**
	 * Lets the current {@link BaseFrontendGUIClient} save a file with the given content.
	 * 
	 * @param content
	 */
	private void saveFile(final byte[] content) {
		getRefGUI().onFrontEnd().saveFile(content);
	}
	
	//method
	/**
	 * Lets the current {@link BaseFrontendGUIClient} send an optional file.
	 */
	private void sendOptionalFile() {
		
		final var fileContainer = readFileToBytes();
		
		if (fileContainer.isEmpty()) {
			runOnCounterpart(ChainedNode.withHeader(CommandProtocol.RECEIVE_OPTIONAL_FILE));
		}
		
		final var fileBytes = fileContainer.getRefElement();
		final var fileString = Base64.getEncoder().encodeToString(fileBytes);
		runOnCounterpart(
			ChainedNode.withHeaderAndAttribute(
				CommandProtocol.RECEIVE_OPTIONAL_FILE,
				ChainedNode.withHeader(fileString)
			)
		);
	}
}
