//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.element.elementenum.ContentPosition;
import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

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
				
		getRefLook()
		.setBackgroundColorForState(WidgetLookState.BASE, DEFAULT_BACKGROUND_COLOR)
		.setPaddingForState(WidgetLookState.BASE, DEFAULT_PADDING);
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
		
		Validator.assertThat(fileTaker).thatIsNamed("file taker").isNotNull();
		
		this.fileTaker = fileTaker;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected UploaderLook createLook() {
		return new UploaderLook();
	}
	
	//method
	@Override
	protected void noteKeyPressOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		
		isHandlingLeftMouseButtonPress = true;
		
		try {
			final var optionalFile = getParentGUI().fromFrontEnd().readFileToBytes();
			
			if (optionalFile.containsAny() && hasFileTaker()) {
				fileTaker.run(optionalFile.getRefElement());
			}
		} finally {
			isHandlingLeftMouseButtonPress = false;
		}
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void paintTextLineWidgetContentArea(final IPainter painter, final UploaderLook uploaderLook) {}
	
	//method
	@Override
	protected void resetBorderWidgetConfiguration() {}
	
	//method
	@Override
	protected void resetBorderWidget() {
		setText(DEFAULT_TEXT);
		setCustomCursorIcon(CursorIcon.HAND);
	}
}
