//package declaration
package ch.nolix.system.application.basewebapplication;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;

//class
final class BaseBackendWebClientFrontendReader implements IFrontEndReader {
	
	//static method
	public static BaseBackendWebClientFrontendReader forBackendWebClient(
		final BaseBackendWebClient<?, ?> backendWebClient
	) {
		return new BaseBackendWebClientFrontendReader(backendWebClient);
	}
	
	//attribute
	private final BaseBackendWebClient<?, ?> parentBackendWebClient;
	
	//constructor
	private BaseBackendWebClientFrontendReader(final BaseBackendWebClient<?, ?> parentBackendWebClient) {
		
		GlobalValidator.assertThat(parentBackendWebClient).thatIsNamed("parent backend web client").isNotNull();
		
		this.parentBackendWebClient = parentBackendWebClient;
	}
	
	//method
	@Override
	public IContainer<byte[]> getFilesFromClipboard() {
		return parentBackendWebClient.internalGetFilesFromClipboardOfCounterpart();
	}
	
	//method
	@Override
	public String getTextFromClipboard() {
		return parentBackendWebClient.internalGetTextFromClipboardOfCounterpart();
	}
	
	//method
	@Override
	public ISingleContainer<byte[]> readFileToBytes() {
		return parentBackendWebClient.internalReadOptionalFileFromCounterpart();
	}
}
