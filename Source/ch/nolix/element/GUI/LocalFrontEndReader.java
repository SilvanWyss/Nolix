//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JFileChooser;

//own imports
import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.fileSystem.FileAccessor;
import ch.nolix.common.wrapperException.WrapperException;
import ch.nolix.element.baseGUI_API.IFrontEndReader;

//class
public final class LocalFrontEndReader implements IFrontEndReader {
	
	//method
	@Override
	public String getTextFromClipboard() {
		try {
			return Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString();
		}
		catch (final IOException | UnsupportedFlavorException exception) {
			throw new WrapperException(exception);
		}
	}
	
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
