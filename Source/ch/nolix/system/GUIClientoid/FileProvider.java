//package declaration
package ch.nolix.system.GUIClientoid;

import ch.nolix.core.validator2.Validator;
//own import
import ch.nolix.element.GUI.IFileProvider;
import ch.nolix.element.GUI.Widget;
import ch.nolix.system.GUIClient.FrontGUIClient;

//class
public final class FileProvider implements IFileProvider {
	
	final FrontGUIClientoid<?> frontGUIClient;
	final Widget<?, ?> widget;
	
	//constructor
	public FileProvider(
		final FrontGUIClientoid<?> frontGUIClient,
		final Widget<?, ?> widget
	) {
		
		Validator.suppose(frontGUIClient).isInstanceOf(FrontGUIClient.class);		
		Validator.suppose(widget).isInstanceOf(Widget.class);
		
		this.frontGUIClient = frontGUIClient;
		this.widget = widget;
	}

	//method
	public byte[] readFileToBytes() {
		return frontGUIClient.readFileToBytesFromCounterpart(widget);
	}
}
