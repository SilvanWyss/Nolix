//package declaration
package ch.nolix.system.application.basewebapplication;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationInstanceTarget;
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
	
	//constant
	private static final BaseBackendWebClientCommandCreator BACKEND_WEB_CLIENT_COMMAND_CREATOR =
	new BaseBackendWebClientCommandCreator();
	
	//constant
	private static final BaseBackendWebClientRequestCreator BACKEND_WEB_CLIENT_REQUEST_CREATOR =
	BaseBackendWebClientRequestCreator.INSTANCE;
	
	//attribute
	private final BaseBackendWebClientFileReader fileReader = BaseBackendWebClientFileReader.forBackendWebClient(this);
	
	//method
	@Override
	public final void deleteCookieByName(final String name) {
		
		final var deleteCookieCommand =
		BACKEND_WEB_CLIENT_COMMAND_CREATOR.createDeleteCookieByNameCommand(name);
		
		runOnCounterpart(deleteCookieCommand);
	}
	
	//method
	@Override
	public final String getCookieValueByCookieNameOrNull(final String cookieName) {
		
		final var getCookieValueRequest =
		BACKEND_WEB_CLIENT_REQUEST_CREATOR.createGetCookieValueRequestForCookieName(cookieName);
		
		return getCookieValueByCookieNameOrNullFromData(getDataFromCounterpart(getCookieValueRequest));
	}
	
	//method
	public final String getUrlParameterValueByUrlParameterNameOrNull(final String urlParameterName) {
		
		final var getUrlParameterValueRequest =
		BACKEND_WEB_CLIENT_REQUEST_CREATOR.createGetUrlParameterValueRequestForUrlParameterName(urlParameterName);
		
		final var urlParameterValueReply = getDataFromCounterpart(getUrlParameterValueRequest);
		
		return urlParameterValueReply.getHeaderOrNull();
	}
	
	//method
	@Override
	public final void setOrAddCookieWithNameAndValue(final String name, final String value) {
		
		final var setOrAddCookieCommand =
		BACKEND_WEB_CLIENT_COMMAND_CREATOR.createSetOrAddCookieCommandForCookieWithNameAndValue(name, value);
		
		runOnCounterpart(setOrAddCookieCommand);
	}
	
	//method
	@Override
	protected final INode<?> getDataFromHere(final IChainedNode request) {
		return getDataFromHereFromBaseBackendWebClient(request);
	}
	
	//method declaration
	protected abstract INode<?> getDataFromHereFromBaseBackendWebClient(final IChainedNode request);
	
	//method
	@Override
	protected final void runHere(final IChainedNode command) {
		switch (command.getHeader()) {
			case CommandProtocol.RECEIVE_OPTIONAL_FILE:
				receiveOptionalFileFromCounterpart(command);
				break;
			default:
				runHereOnBaseBackendWebClient(command);
		}
	}
	
	//method declaration
	protected abstract void runHereOnBaseBackendWebClient(IChainedNode command);
	
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
	final void internalOpenNewTabOnCounterpartWithUrl(final String url) {
		
		final var openNewTabCommand = BACKEND_WEB_CLIENT_COMMAND_CREATOR.createOpenNewTabCommand(url);
		
		runOnCounterpart(openNewTabCommand);
	}
	
	//method
	final ISingleContainer<byte[]> internalReadOptionalFileFromCounterpart() {
		return fileReader.readOptionalFileFromCounterpart();
	}
	
	//method
	final void internalRedirectCounterpartTo(final IApplicationInstanceTarget applicationInstanceTarget) {
		
		final var redirectCommand =
		BACKEND_WEB_CLIENT_COMMAND_CREATOR.createRedirectCommand(applicationInstanceTarget);
		
		runOnCounterpart(redirectCommand);
	}
	
	//method
	final void internalRedirectCounterpartToUrl(final String url) {
		//TODO: Implement.
	}
	
	//method
	final void internalRunOnCounterpart(final ChainedNode command) {
		runOnCounterpart(command);
	}
	
	//method
	final void internalSaveFileOnCounterpart(final byte[] bytes) {
		
		final var saveFileCommand = BACKEND_WEB_CLIENT_COMMAND_CREATOR.createSaveFileCommand(bytes);
		
		runOnCounterpart(saveFileCommand);
	}
	
	//method
	final void internalWriteTextToClipboardOfCounterpart(final String text) {
		
		final var writeTextToClipboardCommand = BACKEND_WEB_CLIENT_COMMAND_CREATOR.createWriteTextToClipBoardCommand(text);
		
		runOnCounterpart(writeTextToClipboardCommand);
	}
	
	//method
	private String getCookieValueByCookieNameOrNullFromData(final INode<?> data) {
		
		if (!data.hasHeader()) {
			return null;
		}
		
		return data.getHeader();
	}
	
	//method
	private void receiveOptionalFileFromCounterpart(final IChainedNode receiveOptionalFileCommand) {
		fileReader.receiveOptionalFileFromCounterpart(receiveOptionalFileCommand);
	}
}
