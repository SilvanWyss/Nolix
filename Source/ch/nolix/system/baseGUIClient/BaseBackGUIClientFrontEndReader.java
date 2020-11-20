//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.baseGUI_API.IFrontEndReader;

//class
final class BaseBackGUIClientFrontEndReader implements IFrontEndReader {
	
	//attribute
	private final BaseBackGUIClient<?> parentBackGUIClient;
	
	//constructor
	public BaseBackGUIClientFrontEndReader(final BaseBackGUIClient<?> parentBackGUIClient) {
		
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
		
		//TODO: Implement.
		return new LinkedList<>();
	}
}
