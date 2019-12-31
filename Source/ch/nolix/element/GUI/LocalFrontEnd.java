//package declaration
package ch.nolix.element.GUI;

//Java import
import javax.swing.JFileChooser;

//own imports
import ch.nolix.common.fileSystem.FileAccessor;
import ch.nolix.common.fileSystem.FileSystemAccessor;
import ch.nolix.common.localComputer.PopupWindowProvider;

//class
final class LocalFrontEnd implements IFrontEnd {
	
	//method
	@Override
	public byte[] readFile() {
		
		final var fileChooser = new JFileChooser();
		
		if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		
		final var path = fileChooser.getSelectedFile().getPath();
		return new FileAccessor(path).readFileToBytes();
	}
	
	//method
	@Override
	public void saveFile(final byte[] content) {
		
		final var fileChooser = new JFileChooser();
		
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			
			final var path = fileChooser.getSelectedFile().getPath();
			
			if (FileSystemAccessor.exists(path)) {
				if (PopupWindowProvider.showRequestWindow(
					"The file '" + path + "' exists already. Do you want to overwrite it?")
				) {
					overwriteFile(path, content);
				}
			}
			else {
				createFile(path, content);
			}
		}
	}
	
	//method
	private void createFile(final String path, final byte[] content) {
		FileSystemAccessor.createFile(path, content);
		new FileAccessor(path).openParentFolder();
	}
	
	//method
	private void overwriteFile(final String path, final byte[] content) {
		FileSystemAccessor.overwriteFile(path, content);
		new FileAccessor(path).openParentFolder();
	}
}
