//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.fileSystem.FileAccessor;
import ch.nolix.common.functionAPI.IElementGetter;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.CursorIcon;
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
	public LinkedList<Node> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		if (providesFile()) {
			attributes.addAtEnd(
					new Node(FILE_GETTER_HEADER)
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
		.assertThat(fileProvider)
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
		
		if (viewAreaIsUnderCursor() && providesFile) {
			getRefGUI().onFrontEnd().saveFile(readFileToBytes());
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
			throw new ArgumentDoesNotHaveAttributeException(this, "file getter");
		}
	}
}
