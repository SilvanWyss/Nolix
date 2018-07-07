//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.element.GUI.IGUIController;
import ch.nolix.element.GUI.Widget;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
final class FrontGUIClientGUIController implements IGUIController {
	
	//attribute
	private final FrontGUIClient frontGUIClient;
	
	//constructor
	public FrontGUIClientGUIController(final FrontGUIClient frontGUIClient) {
		
		Validator
		.suppose(frontGUIClient)
		.thatIsOfType(FrontGUIClient.class)
		.isNotNull();
		
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
