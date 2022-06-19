//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IElementGetter;
import ch.nolix.system.gui.color.Color;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

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
				
		getRefActiveLook()
		.setTextColorForState(WidgetLookState.BASE, Color.DARK_BLUE)
		.setTextColorForState(WidgetLookState.HOVER, Color.BLUE);
	}
	
	//method
	@Override
	public String getShownText() {
		return getText();
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
		
		GlobalValidator
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
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {		
		if (providesFile) {
			getParentGUI().onFrontEnd().saveFile(readFileToBytes());
		}
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void paintTextLineWidgetContentArea(final IPainter painter, final DownloaderLook downloaderLook) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void resetBorderWidgetConfiguration() {
		//Does nothing.
	}
	
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
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "file getter");
		}
	}
}
