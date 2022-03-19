//package declaration
package ch.nolix.system.application.baseguiclient;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.gui.baseapi.IFrontEndWriter;

//class
final class BaseBackendGIUIClientFrontendWriter implements IFrontEndWriter {
	
	//attribute
	private final BaseBackendGUIClient<?, ?> parentBackGUIClient;
	
	//constructor
	public BaseBackendGIUIClientFrontendWriter(final BaseBackendGUIClient<?, ?> parentBackGUIClient) {
		
		Validator.assertThat(parentBackGUIClient).thatIsNamed("parent BackGUIClient").isNotNull();
		
		this.parentBackGUIClient = parentBackGUIClient;
	}
	
	//method
	@Override
	public void saveFile(byte[] bytes) {
		parentBackGUIClient.saveFileOnCounterpart(bytes);
	}
}
