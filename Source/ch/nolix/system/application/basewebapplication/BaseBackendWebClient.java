//package declaration
package ch.nolix.system.application.basewebapplication;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.ISingleContainer;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationTarget;
import ch.nolix.coreapi.webapi.sessionapi.ICookieManager;
import ch.nolix.system.application.basewebapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.main.BackendClient;

//class
public abstract class BaseBackendWebClient<
	BBWC extends BaseBackendWebClient<BBWC, AC>,
	AC
>
extends BackendClient<BBWC, AC>
implements ICookieManager {
	
	//attribute
	private final BaseBackendWebClientFileReader fileReader = BaseBackendWebClientFileReader.forBackendWebClient(this);
	
	//method
	@Override
	public final void deleteCookieByName(final String name) {
		
		final var deleteCookieCommand =
		BaseBackendWebClientCommandCreator.INSTANCE.createDeleteCookieByNameCommand(name);
		
		runOnCounterpart(deleteCookieCommand);
	}
	
	//method
	@Override
	public final String getCookieValueByCookieNameOrNull(final String cookieName) {
		
		final var getCookieValueRequest =
		BaseBackendWebClientRequestCreator.INSTANCE.createGetCookieValueRequestForCookieName(cookieName);
		
		return getCookieValueByCookieNameOrNullFromData(getDataFromCounterpart(getCookieValueRequest));
	}
	
	//method
	@Override
	public final void setOrAddCookieWithNameAndValue(final String name, final String value) {
		
		final var setOrAddCookieCommand =
		BaseBackendWebClientCommandCreator.INSTANCE.createSetOrAddCookieCommandForCookieWithNameAndValue(name, value);
		
		runOnCounterpart(setOrAddCookieCommand);
	}
	
	//method
	@Override
	protected final Node getDataFromHere(final ChainedNode request) {
		return getDataFromHereFromBaseBackendWebClient(request);
	}
	
	//method declaration
	protected abstract Node getDataFromHereFromBaseBackendWebClient(final ChainedNode request);
	
	//method
	@Override
	protected final void runHere(final ChainedNode command) {
		switch (command.getHeader()) {
			case CommandProtocol.RECEIVE_OPTIONAL_FILE:
				receiveOptionalFileFromCounterpart(command);
				break;
			default:
				runHereOnBaseBackendWebClient(command);
		}
	}
	
	//method declaration
	protected abstract void runHereOnBaseBackendWebClient(ChainedNode command);
	
	//method
	final IContainer<byte[]> internalGetFilesFromClipboardOfCounterpart() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "getFilesFromClipboard");
	}
	
	//method
	final String internalGetTextFromClipboardOfCounterpart() {
		
		final var getTextFromClipBoardCommand =
		BaseBackendWebClientRequestCreator.INSTANCE.createGetTextFromClipboardRequest();
		
		return getDataFromCounterpart(getTextFromClipBoardCommand).getHeader();
	}
	
	//method
	final void internalOpenNewTabOnCounterpartWithURL(final String pURL) {
		
		final var openNewTabCommand = BaseBackendWebClientCommandCreator.INSTANCE.createOpenNewTabCommand(pURL);
		
		runOnCounterpart(openNewTabCommand);
	}
	
	//method
	final ISingleContainer<byte[]> internalReadOptionalFileFromCounterpart() {
		return fileReader.readOptionalFileFromCounterpart();
	}
	
	//method
	final void internalRedirectCounterpartTo(final IApplicationTarget applicationTarget) {
		
		final var redirectCommand =
		BaseBackendWebClientCommandCreator.INSTANCE.createRedirectCommand(applicationTarget);
		
		runOnCounterpart(redirectCommand);
	}
	
	//method
	final void internalRunOnCounterpart(final ChainedNode command) {
		runOnCounterpart(command);
	}
	
	//method
	final void internalSaveFileOnCounterpart(final byte[] bytes) {
		
		final var saveFileCommand = BaseBackendWebClientCommandCreator.INSTANCE.createSaveFileCommand(bytes);
		
		runOnCounterpart(saveFileCommand);
	}
	
	//method
	private String getCookieValueByCookieNameOrNullFromData(final BaseNode<?> data) {
		
		if (!data.hasHeader()) {
			return null;
		}
		
		return data.getHeader();
	}
	
	//method
	private void receiveOptionalFileFromCounterpart(final ChainedNode receiveOptionalFileCommand) {
		fileReader.receiveOptionalFileFromCounterpart(receiveOptionalFileCommand);
	}
}
