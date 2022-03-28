//package declaration
package ch.nolix.system.application.baseguiclient;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.gui.baseapi.IFrontEndWriter;

//class
final class BaseBackendGIUIClientFrontendWriter implements IFrontEndWriter {
	
	//attribute
	private final BaseBackendGUIClient<?, ?> parentBackendGUIClient;
	
	//constructor
	public BaseBackendGIUIClientFrontendWriter(final BaseBackendGUIClient<?, ?> parentBackendGUIClient) {
		
		Validator.assertThat(parentBackendGUIClient).thatIsNamed("parent backend GUI client").isNotNull();
		
		this.parentBackendGUIClient = parentBackendGUIClient;
	}
	
	//method
	@Override
	public void openNewTabWithURL(final String pURL) {
		parentBackendGUIClient.internalOpenNewTabOnCounterpartWithURL(pURL);	
	}
	
	//method
	@Override
	public void saveFile(byte[] bytes) {
		parentBackendGUIClient.saveFileOnCounterpart(bytes);
	}
}
