//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.filesystem.FileAccessor;
import ch.nolix.common.functionapi.IElementGetter;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.CursorIcon;
import ch.nolix.element.input.Key;

//class
public final class Downloader extends TextLineWidget<Downloader, DownloaderLook> {
	
	//constants
	public static final String DEFAULT_TEXT = "Download";
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.HAND;
	
	//constant
	private static final String FILE_GETTER_HEADER = "FileGetter";
	
	//attribute
	private boolean providesFile;
	
	//optional attribute
	private IElementGetter<byte[]> fileProvider;
	
	//constructor
	public Downloader() {
		setCustomCursorIcon(DEFAULT_CURSOR_ICON);	
		getRefBaseLook().setTextColor(Color.DARK_BLUE);	
		getRefHoverLook().setTextColor(Color.BLUE);
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
			attributes.addAtEnd(Node.withHeader(FILE_GETTER_HEADER));
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
	public void reset() {
		
		super.reset();
		
		setText(DEFAULT_TEXT);
		removeFileProvider();
	}
	
	//method
	public Downloader setFile(final byte[] file) {
		return setFileProvider(() -> file);
	}
	
	//method
	public Downloader setFileProvider(final FileAccessor fileAccessor) {
		return setFileProvider(fileAccessor::readFileToBytes);
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyPressOnContentAreaWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {		
		if (providesFile) {
			getParentGUI().onFrontEnd().saveFile(readFileToBytes());
		}
	}
	
	//method
	@Override
	protected DownloaderLook createLook() {
		return new DownloaderLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetConfigurationOnSelfStage3() {}
	
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
