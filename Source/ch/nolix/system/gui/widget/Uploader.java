//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.functionuniversalapi.IElementTaker;
import ch.nolix.system.gui.color.Color;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;

//class
public final class Uploader extends TextLineWidget<Uploader, UploaderLook> {
	
	//constant
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.GAINSBORO;
	public static final int DEFAULT_PADDING = 10;
	public static final String DEFAULT_TEXT = "Click to upload";
	
	//attribute
	@SuppressWarnings("unused")
	private boolean isHandlingLeftMouseButtonPress;
	
	//optional attribute
	private IElementTaker<byte[]> fileTaker;
	
	//constructor
	public Uploader() {
		
		reset();
		
		setContentPosition(ContentPosition.CENTER);
				
		getRefActiveLook()
		.setBackgroundColorForState(WidgetLookState.BASE, DEFAULT_BACKGROUND_COLOR)
		.setPaddingForState(WidgetLookState.BASE, DEFAULT_PADDING);
	}
	
	//method
	@Override
	public String getShownText() {
		return getText();
	}
	
	//method
	public boolean hasFileTaker() {
		return (fileTaker != null);
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	public Uploader setFileTaker(final IElementTaker<byte[]> fileTaker) {
		
		GlobalValidator.assertThat(fileTaker).thatIsNamed("file taker").isNotNull();
		
		this.fileTaker = fileTaker;
		
		return this;
	}
	
	//method
	@Override
	protected UploaderLook createLook() {
		return new UploaderLook();
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
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		
		isHandlingLeftMouseButtonPress = true;
		
		GlobalSequencer.runInBackground(this::uploadFile);
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void paintTextLineWidgetContentArea(final IPainter painter, final UploaderLook uploaderLook) {
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
		setCustomCursorIcon(CursorIcon.HAND);
	}
	
	//method
	private void uploadFile() {
		try {
			
			final var optionalFile = getParentGUI().fromFrontEnd().readFileToBytes();
			
			if (hasFileTaker() && optionalFile.containsAny()) {
				fileTaker.run(optionalFile.getRefElement());
			}
		} finally {
			isHandlingLeftMouseButtonPress = false;
		}
	}
}
