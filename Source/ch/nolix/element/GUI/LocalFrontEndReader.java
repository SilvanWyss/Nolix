//package declaration
package ch.nolix.element.GUI;

//Java import
import javax.swing.JFileChooser;

//own imports
import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.fileSystem.FileAccessor;
import ch.nolix.element.baseGUI_API.IFrontEndReader;

//class
public final class LocalFrontEndReader implements IFrontEndReader {
	
	//method
	@Override
	public SingleContainer<byte[]> readFileToBytes() {
		
		final var fileChooser = new JFileChooser();
		
		if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			return new SingleContainer<>();
		}
		
		final var filePath = fileChooser.getSelectedFile().getPath();
		
		return new SingleContainer<>(new FileAccessor(filePath).readFileToBytes());
	}
}
