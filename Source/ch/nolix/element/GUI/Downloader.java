//package declaration
package ch.nolix.element.GUI;

//Java import
import javax.swing.JFileChooser;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.fileSystem.FileAccessor;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.core.functionAPI.IElementGetter;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.util.PopupWindowProvider;
import ch.nolix.core.validator2.Validator;

//class
public final class Downloader extends TextLineWidget<Downloader> {
	
	//constant
	public static final String DEFAULT_TEXT = "Download";
	public CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.Hand;
	
	//constant
	private static final String FILE_GETTER_HEADER = "FileGetter";
	
	//attribute
	private boolean providesFile = false;
	
	//optional attribute
	private IElementGetter<byte[]> fileProvider;
	
	//constructor
	public Downloader() {
		reset();
		approveProperties();
		applyUsableConfiguration();
	}
	
	//method
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {	
		switch (attribute.getHeader()) {
			case FILE_GETTER_HEADER:
				setProvideFile();
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	public List<DocumentNode> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		if (providesFile()) {
			attributes.addAtEnd(
				DocumentNode.createSpecificationWithHeader(FILE_GETTER_HEADER)
			);
		}
		
		return attributes;
	}
	
	//method
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public boolean keepsFocus() {
		return true;
	}
	
	//method
	public void noteLeftMouseButtonRelease() {
		
		super.noteLeftMouseButtonRelease();
		
		if (providesFile()) {
			final var fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				
				final var destinationFilePath = fileChooser.getSelectedFile().getPath();
				
				if (new FileSystemAccessor().fileSystemItemExists(destinationFilePath)) {
					if (PopupWindowProvider.showRequestWindow(
							"The file '" + destinationFilePath + "' exists already. Do you want to overwrite it?")
						) {
						new FileSystemAccessor().overwriteFile(destinationFilePath, readFile());
						new FileAccessor(destinationFilePath).openParentFolder();
					}
				}
				else {
					new FileSystemAccessor().createFile(destinationFilePath, readFile());
					new FileAccessor(destinationFilePath).openParentFolder();
				}
			}
		}	
	}
	
	//method
	public boolean providesFile() {
		return providesFile;
	}
	
	//method
	public String readFile() {
		return new String(readFileToBytes());
	}
	
	//method
	public byte[] readFileToBytes() {
		if (!hasFileGetter()) {
			
			if (belongsToGUI() && getParentGUI().hasController()) {
				final var fileProvider = getParentGUI().getRefController().getFileProvider(this);
				return fileProvider.readFileToBytes();
			}
			
			throw new InvalidStateException(this, "does not provide a file");
		}
		else {
			return getFileGetter().getOutput();
		}
	}
	
	//method
	public Downloader removeFileProvider() {
		
		providesFile = false;
		fileProvider = null;
		
		return this;
	}
	
	//method
	public Downloader reset() {
		
		super.reset();
		
		setText(DEFAULT_TEXT);
		removeFileProvider();
		
		return this;
	}
	
	//method
	public Downloader setFile(final byte[] file) {
		return setFileProvider(() -> file);
	}
	
	//method
	public Downloader setFileProvider(final FileAccessor fileAccessor) {
		return setFileProvider(() -> fileAccessor.readFileToBytes());
	}
	
	//method
	public Downloader setFileProvider(final IElementGetter<byte[]> fileProvider) {
		
		Validator
		.suppose(fileProvider)
		.thatIsNamed("file provider")
		.isInstance();
		
		providesFile = true;
		this.fileProvider = fileProvider;
		
		return this;
	}
	
	//method
	protected void applyUsableConfigurationWhenConfigurationIsReset() {		
		setCustomCursorIcon(DEFAULT_CURSOR_ICON);
	}
	
	//method
	private IElementGetter<byte[]> getFileGetter() {
		
		supposeHasFileGetter();
		
		return fileProvider;
	}
	
	//method
	private boolean hasFileGetter() {
		return (fileProvider != null);
	}
	
	//method
	private void setProvideFile() {		
		providesFile = true;
	}
	
	//method
	private void supposeHasFileGetter() {
		if (!hasFileGetter()) {
			throw new UnexistingAttributeException(this, "file getter");
		}
	}
}
