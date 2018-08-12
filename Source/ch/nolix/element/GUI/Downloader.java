//package declaration
package ch.nolix.element.GUI;

//Java import
import javax.swing.JFileChooser;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.fileSystem.FileAccessor;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.core.functionInterfaces.IElementGetter;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.util.PopupWindowProvider;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

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
	private IElementGetter<byte[]> fileGetter;
	
	//constructor
	public Downloader() {
		reset();
		approveProperties();
		applyUsableConfiguration();
	}
	
	public void addOrChangeAttribute(final Specification attribute) {
		
		switch (attribute.getHeader()) {
			case FILE_GETTER_HEADER:
				setProvidingFile();
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	public List<StandardSpecification> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		if (providesFile()) {
			attributes.addAtEnd(
				StandardSpecification.createSpecificationWithHeaderOnly(FILE_GETTER_HEADER)
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
			
			throw new InvalidStateException(this, "provides no file");
		}
		else {
			return getFileGetter().getOutput();
		}
	}
	
	//method
	public Downloader removeFileGetter() {
		
		providesFile = false;
		fileGetter = null;
		
		return this;
	}
	
	//method
	public Downloader reset() {
		
		super.reset();
		
		setText(DEFAULT_TEXT);
		removeFileGetter();
		
		return this;
	}
	
	//method
	public Downloader setFileGetter(final IElementGetter<byte[]> fileGetter) {
		
		Validator
		.suppose(fileGetter)
		.thatIsNamed("file getter")
		.isNotNull();
		
		providesFile = true;
		this.fileGetter = fileGetter;
		
		return this;
	}
	
	//method
	public Downloader setProvidingFile() {
		
		providesFile = true;
		
		return this;
	}
	
	//method
	protected void applyUsableConfigurationWhenConfigurationIsReset() {		
		setCustomCursorIcon(DEFAULT_CURSOR_ICON);
	}
	
	//method
	private IElementGetter<byte[]> getFileGetter() {
		
		supposeHasFileGetter();
		
		return fileGetter;
	}
	
	//method
	private boolean hasFileGetter() {
		return (fileGetter != null);
	}
	
	//method
	private void supposeHasFileGetter() {
		if (!hasFileGetter()) {
			throw new UnexistingAttributeException(this, "file getter");
		}
	}
}
