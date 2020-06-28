//package declaration
package ch.nolix.element.GUI;

//Java import
import javax.swing.JFileChooser;

//own imports
import ch.nolix.common.fileSystem.FileSystemAccessor;
import ch.nolix.common.localComputer.PopupWindowProvider;
import ch.nolix.common.processProperty.WriteMode;
import ch.nolix.element.baseGUI_API.IFrontEndWriter;

//class
public final class LocalFrontEndWriter implements IFrontEndWriter {
	
	//method
	@Override
	public void saveFile(final byte[] bytes) {
		
		final var fileChooser = new JFileChooser();
		
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			
			final var path = fileChooser.getSelectedFile().getPath();
			
			if (FileSystemAccessor.exists(path)) {
				if (
					PopupWindowProvider.showRequestWindow(
						"The file '" + path + "' exists already. Do you want to overwrite it?"
					)
				) {
					FileSystemAccessor.createFile(path, WriteMode.OVERWRITE_WHEN_EXISTS_ALREADY, bytes);
				}
			}
			else {
				FileSystemAccessor.createFile(path, bytes);
			}
		}
	}
}
