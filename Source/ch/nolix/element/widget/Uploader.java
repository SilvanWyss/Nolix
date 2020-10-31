//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementEnum.ContentPosition;
import ch.nolix.element.input.Key;

//class
public final class Uploader extends TextLineWidget<Uploader,UploaderLook> {
	
	//constant
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.GAINSBORO;
	public static final int DEFAULT_PADDING = 10;
	public static final String DEFAULT_TEXT = "Click to upload";
	
	//attribute
	@SuppressWarnings("unused")
	private boolean isHandlingLeftMouseButtonPress = false;
	
	//optional attribute
	private IElementTaker<byte[]> fileTaker;
	
	//constructor
	public Uploader() {
		resetAndApplyDefaultConfiguration();
	}
	
	//method
	public final boolean hasFileTaker() {
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Uploader reset() {
		
		super.reset();
		
		setText(DEFAULT_TEXT);
		
		return this;
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
	public boolean shortensShownTextWhenHasLimitedWidth() {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		
		setContentPosition(ContentPosition.Center);
		setCustomCursorIcon(CursorIcon.Hand);
		
		getRefBaseLook()
		.setBackgroundColor(DEFAULT_BACKGROUND_COLOR)
		.setPaddings(DEFAULT_PADDING);
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
}
