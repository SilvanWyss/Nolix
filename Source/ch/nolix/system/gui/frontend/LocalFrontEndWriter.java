//package declaration
package ch.nolix.system.gui.frontend;

//Java imports
import javax.swing.JFileChooser;

//own imports
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.environment.localcomputer.PopupWindowProvider;
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.netapi.targetuniversalapi.IApplicationTarget;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;

//class
public final class LocalFrontEndWriter implements IFrontEndWriter {
	
	//method
	@Override
	public void openNewTabWithURL(final String pURL) {
		ShellProvider.startDefaultWebBrowserOpeningURL(pURL);
	}
	
	//method
	@Override
	public void redirectTo(final IApplicationTarget applicationTarget) {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "redirectTo");
	}
	
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
