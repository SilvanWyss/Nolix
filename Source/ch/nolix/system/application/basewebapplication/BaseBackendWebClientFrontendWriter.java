//package declaration
package ch.nolix.system.application.basewebapplication;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.targetuniversalapi.IApplicationTarget;
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
	public void openNewTabWithURL(final String pURL) {
		parentBackendWebClient.internalOpenNewTabOnCounterpartWithURL(pURL);
	}
	
	//method
	@Override
	public void redirectTo(final IApplicationTarget applicationTarget) {
		parentBackendWebClient.internalRedirectCounterpartTo(applicationTarget);
	}
	
	//method
	@Override
	public void saveFile(final byte[] bytes) {
		parentBackendWebClient.internalSaveFileOnCounterpart(bytes);
	}
}
