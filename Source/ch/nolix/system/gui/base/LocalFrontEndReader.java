//package declaration
package ch.nolix.system.gui.base;

//Java imports
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.SingleContainer;
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.systemapi.guiapi.baseapi.IFrontEndReader;

//class
public final class LocalFrontEndReader implements IFrontEndReader {
	
	//static attribute
	private static final JFileChooser fileChooser;
	
	//static initializing
	static {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (
			final
			ClassNotFoundException
			| InstantiationException
			| IllegalAccessException
			| UnsupportedLookAndFeelException
		exception) {
			throw new WrapperException(exception);
		}
		
		fileChooser = new JFileChooser();
	}
	
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
