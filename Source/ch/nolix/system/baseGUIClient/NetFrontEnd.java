//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.IFrontEnd;

//class
public final class NetFrontEnd implements IFrontEnd {
	
	//attribute
	/**
	 * The {@link BaseBackGUIClient} the current {@link NetFrontEnd} belongs to.
	 */
	private final BaseBackGUIClient<?> parentBackGUIClient;
	
	//constructor
	public NetFrontEnd(final BaseBackGUIClient<?> parentBackGUIClient) {
		
		Validator.suppose(parentBackGUIClient).thatIsNamed("parent BackGUIClient").isNotNull();
		
		this.parentBackGUIClient = parentBackGUIClient;
	}
	
	//method
	@Override
	public byte[] readFile() {
		return parentBackGUIClient.getFileFromCounterpart();
	}
	
	//method
	@Override
	public void saveFile(final byte[] bytes) {
		parentBackGUIClient.saveFileOnCounterpart(bytes);
	}
}
