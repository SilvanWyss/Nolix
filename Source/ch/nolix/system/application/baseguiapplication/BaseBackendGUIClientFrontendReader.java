//package declaration
package ch.nolix.system.application.baseguiapplication;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.container.SingleContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.gui.baseapi.IFrontEndReader;

//class
final class BaseBackendGUIClientFrontendReader implements IFrontEndReader {
	
	//attribute
	private final BaseBackendGUIClient<?, ?> parentBackGUIClient;
	
	//constructor
	public BaseBackendGUIClientFrontendReader(final BaseBackendGUIClient<?, ?> parentBackGUIClient) {
		
		Validator.assertThat(parentBackGUIClient).thatIsNamed("parent BackGUIClient").isNotNull();
		
		this.parentBackGUIClient = parentBackGUIClient;
	}
	
	//method
	@Override
	public String getTextFromClipboard() {
		return parentBackGUIClient.getTextFromClipboardFromCounterpart();
	}
	
	//method
	@Override
	public SingleContainer<byte[]> readFileToBytes() {
		return parentBackGUIClient.getFileFromCounterpart();
	}
	
	//method
	@Override
	public LinkedList<byte[]> getFilesFromClipboard() {
		throw new ArgumentDoesNotSupportMethodException(this, "getFilesFromClipboard");
	}
}
