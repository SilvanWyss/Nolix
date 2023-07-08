//package declaration
package ch.nolix.system.application.basewebapplication;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programcontrolapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;

//class
final class BaseBackendWebClientFrontendWriter implements IFrontEndWriter {
	
	//static method
	public static BaseBackendWebClientFrontendWriter forBackendWebClient(
		final BaseBackendWebClient<?, ?> backendWebClient
	) {
		return new BaseBackendWebClientFrontendWriter(backendWebClient);
	}
	
	//attribute
	private final BaseBackendWebClient<?, ?> parentBackendWebClient;
	
	//constructor
	private BaseBackendWebClientFrontendWriter(final BaseBackendWebClient<?, ?> parentBackendWebClient) {
		
		GlobalValidator.assertThat(parentBackendWebClient).thatIsNamed("parent backend web client").isNotNull();
		
		this.parentBackendWebClient = parentBackendWebClient;
	}
	
	//method
	@Override
	public void openNewTabWithUrl(final String url) {
		parentBackendWebClient.internalOpenNewTabOnCounterpartWithUrl(url);
	}
	
	//method
	@Override
	public void redirectTo(final IApplicationInstanceTarget applicationInstanceTarget) {
		parentBackendWebClient.internalRedirectCounterpartTo(applicationInstanceTarget);
	}
	
	//method
	@Override
	public void redirectToUrl(final String url) {
		parentBackendWebClient.internalRedirectCounterpartToUrl(url);		
	}
	
	//method
	@Override
	public void saveFile(final byte[] bytes) {
		parentBackendWebClient.internalSaveFileOnCounterpart(bytes);
	}
	
	//method
	@Override
	public void writeTextToClipboard(final String text) {
		parentBackendWebClient.internalWriteTextToClipboardOfCounterpart(text);
	}
}
