//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI.IFileProvider;
import ch.nolix.element.GUI.IGUIController;
import ch.nolix.system.GUIClient.FrontGUIClient;

//package-visible class
final class FrontGUIClientoidGUIController implements IGUIController {
	
	//attribute
	private final FrontGUIClientoid<?> frontGUIClient;
	
	//constructor
	public FrontGUIClientoidGUIController(final FrontGUIClientoid<?> frontGUIClient) {
		
		Validator.suppose(frontGUIClient).isOfType(FrontGUIClient.class);
		
		this.frontGUIClient = frontGUIClient;
	}
	
	//method
	@Override
	public IFileProvider getFileProvider(IContainer<Integer> indexPathOnRootGUI) {
		return frontGUIClient.getFileProvider(indexPathOnRootGUI);
	}
	
	//method
	@Override
	public void noteLeftMouseButtonPressCommand(IContainer<Integer> indexPathOnRootGUI) {
		frontGUIClient.noteLeftMouseButtonPressCommandOnCounterpart(indexPathOnRootGUI);
	}
	
	//method
	@Override
	public void noteLeftMouseButtonReleaseCommand(IContainer<Integer> indexPathOnRootGUI) {
		frontGUIClient.noteLeftMouseButtonReleaseCommandOnCounterpart(indexPathOnRootGUI);
	}
	
	//method
	@Override
	public void noteRightMouseButtonPressCommand(IContainer<Integer> indexPathOnRootGUI) {
		frontGUIClient.noteRightMouseButtonPressCommandOnCounterpart(indexPathOnRootGUI);
	}
	
	//method
	@Override
	public void noteRightMouseButtonReleaseCommand(IContainer<Integer> indexPathOnRootGUI) {
		frontGUIClient.noteRightMouseButtonReleaseCommandOnCounterpart(indexPathOnRootGUI);
	}
}
