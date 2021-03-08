//package declaration
package ch.nolix.element.gui.base;

//Java imports
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.environment.filesystem.FileAccessor;
import ch.nolix.common.environment.filesystem.FileSystemAccessor;
import ch.nolix.common.errorcontrol.exception.WrapperException;
import ch.nolix.element.gui.baseapi.IFrontEndReader;

//class
public final class LocalFrontEndReader implements IFrontEndReader {
	
	//method
	@Override
	public LinkedList<byte[]> getFilesFromClipboard() {
		return getFilePathsFromClipboard().to(FileSystemAccessor::readFileToBytes);
	}
	
	//method
	@Override
	public String getTextFromClipboard() {
		try {
			return Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString();
		} catch (final IOException | UnsupportedFlavorException exception) {
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
	
	//method
	private LinkedList<String> getFilePathsFromClipboard() {
		try {
			
			@SuppressWarnings("unchecked")
			final var files =
			(List<File>)(Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.javaFileListFlavor));
			
			return ReadContainer.forIterable(files).to(File::getPath);
		} catch (final IOException | UnsupportedFlavorException exception) {
			throw new WrapperException(exception);
		}
	}
}
