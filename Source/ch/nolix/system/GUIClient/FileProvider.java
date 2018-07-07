//package declaration
package ch.nolix.system.GUIClient;

//own import
import ch.nolix.element.GUI.IFileProvider;
import ch.nolix.element.GUI.Widget;
import ch.nolix.primitive.validator2.Validator;

//class
public final class FileProvider implements IFileProvider {
	
	final FrontGUIClient frontGUIClient;
	final Widget<?, ?> widget;
	
	//constructor
	public FileProvider(
		final FrontGUIClient frontGUIClient,
		final Widget<?, ?> widget
	) {
		
		Validator
		.suppose(frontGUIClient)
		.thatIsOfType(FrontGUIClient.class)
		.isNotNull();
		
		Validator
		.suppose(widget)
		.thatIsOfType(Widget.class)
		.isNotNull();
		
		this.frontGUIClient = frontGUIClient;
		this.widget = widget;
	}

	//method
	public byte[] readFileToBytes() {
		return frontGUIClient.readFileToBytesFromCounterpart(widget);
	}
}
