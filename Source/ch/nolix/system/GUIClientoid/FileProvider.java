//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI_API.IFileProvider;
import ch.nolix.system.GUIClient.FrontGUIClient;

//class
public final class FileProvider implements IFileProvider {
	
	final FrontGUIClientoid<?> frontGUIClient;
	final IContainer<Integer> widgetIndexPathOnRootGUI;
	
	//constructor
	public FileProvider(
		final FrontGUIClientoid<?> frontGUIClient,
		final IContainer<Integer> indexPathOnRootGUI
	) {
		
		Validator.suppose(frontGUIClient).isOfType(FrontGUIClient.class);
		Validator.suppose(indexPathOnRootGUI).isOfType(IContainer.class);
		
		this.frontGUIClient = frontGUIClient;
		widgetIndexPathOnRootGUI = indexPathOnRootGUI;
	}
	
	//method
	@Override
	public byte[] readFileToBytes() {
		return frontGUIClient.readFileToBytesFromCounterpart(widgetIndexPathOnRootGUI);
	}
}
