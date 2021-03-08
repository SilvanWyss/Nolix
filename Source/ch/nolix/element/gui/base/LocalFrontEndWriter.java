//package declaration
package ch.nolix.element.gui.base;

//Java import
import javax.swing.JFileChooser;

import ch.nolix.common.environment.filesystem.FileSystemAccessor;
import ch.nolix.common.environment.localcomputer.PopupWindowProvider;
import ch.nolix.common.programcontrol.processproperty.WriteMode;
import ch.nolix.element.gui.baseapi.IFrontEndWriter;

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
					FileSystemAccessor.createFile(path, WriteMode.OVERWRITE_WHEN_TARGET_EXISTS_ALREADY, bytes);
				}
			} else {
				FileSystemAccessor.createFile(path, bytes);
			}
		}
	}
}
