//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.element.GUI.IGUIController;
import ch.nolix.element.GUI.Widget;
import ch.nolix.primitive.validator2.Validator;
import ch.nolix.system.GUIClient.FrontGUIClient;

//package-visible class
final class FrontGUIClientoidGUIController implements IGUIController {
	
	//attribute
	private final FrontGUIClientoid<?> frontGUIClient;
	
	//constructor
	public FrontGUIClientoidGUIController(final FrontGUIClientoid<?> frontGUIClient) {
		
		Validator.suppose(frontGUIClient).isInstanceOf(FrontGUIClient.class);
		
		this.frontGUIClient = frontGUIClient;
	}
	
	//method
	public FileProvider getFileProvider(Widget<?, ?> widget) {
		return frontGUIClient.getFileProvider(widget);
	}

	//method
	public void noteLeftMouseButtonPressCommand(final Widget<?, ?> widget) {
		frontGUIClient.noteLeftMouseButtonPressCommandOnCounterpart(widget);
	}

	//method
	public void noteLeftMouseButtonReleaseCommand(final Widget<?, ?> widget) {
		frontGUIClient.noteLeftMouseButtonReleaseCommandOnCounterpart(widget);
	}

	//method
	public void noteRightMouseButtonPressCommand(final Widget<?, ?> widget) {
		frontGUIClient.noteRightMouseButtonPressCommandOnCounterpart(widget);
	}

	//method
	public void noteRightMouseButtonReleaseCommand(final Widget<?, ?> widget) {
		frontGUIClient.noteRightMouseButtonReleaseCommandOnCounterpart(widget);
	}
}
