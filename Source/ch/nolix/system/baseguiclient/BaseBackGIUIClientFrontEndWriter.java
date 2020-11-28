//package declaration
package ch.nolix.system.baseguiclient;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.element.baseguiapi.IFrontEndWriter;

//class
final class BaseBackGIUIClientFrontEndWriter implements IFrontEndWriter {
	
	//attribute
	private final BaseBackGUIClient<?> parentBackGUIClient;
	
	//constructor
	public BaseBackGIUIClientFrontEndWriter(final BaseBackGUIClient<?> parentBackGUIClient) {
		
		Validator.assertThat(parentBackGUIClient).thatIsNamed("parent BackGUIClient").isNotNull();
		
		this.parentBackGUIClient = parentBackGUIClient;
	}
	
	//method
	@Override
	public void saveFile(byte[] bytes) {
		parentBackGUIClient.saveFileOnCounterpart(bytes);
	}
}
