//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.environment.filesystem.FileAccessor;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementGetter;
import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
public final class Downloader extends TextLineWidget<Downloader, DownloaderLook> {
	
	//constants
	public static final String DEFAULT_TEXT = "Download";
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.HAND;
	
	//attribute
	private boolean providesFile;
	
	//optional attribute
	private IElementGetter<byte[]> fileProvider;
	
	//constructor
	public Downloader() {
		
		reset();
				
		getRefLook()
		.setTextColorForState(WidgetLookState.BASE, Color.DARK_BLUE)
		.setTextColorForState(WidgetLookState.HOVER, Color.BLUE);
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
	@Override
	protected DownloaderLook createLook() {
		return new DownloaderLook();
	}
	
	//method
	@Override
	protected String getDefaultText() {
		return DEFAULT_TEXT;
	}
	
	//method
	@Override
	protected int getTextWidthAddition() {
		return 0;
	}
	
	//method
	@Override
	protected void noteKeyPressOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {		
		if (providesFile) {
			getParentGUI().onFrontEnd().saveFile(readFileToBytes());
		}
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void paintTextLineWidgetContentArea(final IPainter painter, final DownloaderLook downloaderLook) {}
	
	//method
	@Override
	protected void resetBorderWidgetConfiguration() {}
	
	//method
	@Override
	protected void resetTextLineWidget() {
		setCustomCursorIcon(DEFAULT_CURSOR_ICON);
		removeFileProvider();
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
	private void supposeHasFileGetter() {
		if (!hasFileGetter()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "file getter");
		}
	}
}
