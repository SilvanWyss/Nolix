//package declaration
package ch.nolix.element.widgets;

//Java import
import javax.swing.JFileChooser;

import ch.nolix.core.containers.List;
import ch.nolix.core.fileSystem.FileAccessor;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.core.functionAPI.IElementGetter;
import ch.nolix.core.invalidArgumentExceptions.ArgumentMissesAttributeException;
import ch.nolix.core.localComputer.PopupWindowProvider;
import ch.nolix.core.node.Node;
import ch.nolix.core.node.BaseNode;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI_API.CursorIcon;
import ch.nolix.element.color.Color;

//class
public final class Downloader extends TextLineWidget<Downloader, DownloaderLook> {
	
	//constant
	public static final String DEFAULT_TEXT = "Download";
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.Hand;
	
	//constant
	private static final String FILE_GETTER_HEADER = "FileGetter";
	
	//attribute
	private boolean providesFile = false;
	
	//optional attribute
	private IElementGetter<byte[]> fileProvider;
	
	//constructor
	public Downloader() {
		resetAndApplyDefaultConfiguration();
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		switch (attribute.getHeader()) {
			case FILE_GETTER_HEADER:
				setProvideFile();
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	@Override
	public List<Node> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		if (providesFile()) {
			attributes.addAtEnd(
				Node.withHeader(FILE_GETTER_HEADER)
			);
		}
		
		return attributes;
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
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
		return getFileGetter().getOutput();
	}
	
	//method
	public Downloader removeFileProvider() {
		
		providesFile = false;
		fileProvider = null;
		
		return this;
	}
	
	//method
	@Override
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
		.isNotNull();
		
		providesFile = true;
		this.fileProvider = fileProvider;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean shortensShownTextWhenHasLimitedWidth() {
		return getRefLook().getRecursiveOrDefaultShortensTextWhenLimitedFlag();
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseWhenEnabled() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonReleaseWhenEnabled();
		
		//Handles the case that the view area of the current Downloader is under the cursor.
		if (viewAreaIsUnderCursor()) {
		
			//TODO: Open file chooser on client side.
			if (providesFile()) {
				final var fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					
					final var destinationFilePath = fileChooser.getSelectedFile().getPath();
					
					if (FileSystemAccessor.exists(destinationFilePath)) {
						if (PopupWindowProvider.showRequestWindow(
								"The file '" + destinationFilePath + "' exists already. Do you want to overwrite it?")
							) {
							FileSystemAccessor.overwriteFile(destinationFilePath, readFile());
							new FileAccessor(destinationFilePath).openParentFolder();
						}
					}
					else {
						FileSystemAccessor.createFile(destinationFilePath, readFile());
						new FileAccessor(destinationFilePath).openParentFolder();
					}
				}
			}
		}
	}

	//method
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		setCustomCursorIcon(DEFAULT_CURSOR_ICON);	
		getRefBaseLook().setTextColor(Color.DARK_BLUE);	
		getRefHoverLook().setTextColor(Color.BLUE);
	}
	
	//method
	@Override
	protected DownloaderLook createLook() {
		return new DownloaderLook();
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
			throw new ArgumentMissesAttributeException(this, "file getter");
		}
	}
}
