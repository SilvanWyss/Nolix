//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementenum.ContentPosition;
import ch.nolix.element.gui.CursorIcon;
import ch.nolix.element.input.Key;
import ch.nolix.element.painterapi.IPainter;

//class
public final class Uploader extends TextLineWidget<Uploader,UploaderLook> {
	
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
		
		setContentPosition(ContentPosition.CENTER);
		setCustomCursorIcon(CursorIcon.HAND);
		
		getRefBaseLook()
		.setBackgroundColor(DEFAULT_BACKGROUND_COLOR)
		.setPaddings(DEFAULT_PADDING);
	}
	
	//method
	public boolean hasFileTaker() {
		return (fileTaker != null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyPressOnContentAreaWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		
		isHandlingLeftMouseButtonPress = true;
		
		try {
			final var optionalFile = getParentGUI().fromFrontEnd().readFileToBytes();
			
			if (optionalFile.containsAny() && hasFileTaker()) {
				fileTaker.run(optionalFile.getRefElement());
			}
		}
		finally {
			isHandlingLeftMouseButtonPress = false;
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentAreaStage2(final IPainter painter, final UploaderLook uploaderLook) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetConfigurationOnSelfStage3() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetStage4() {
		setText(DEFAULT_TEXT);
	}
}
