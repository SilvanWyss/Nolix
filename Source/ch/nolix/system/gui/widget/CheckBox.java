//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.math.Calculator;
import ch.nolix.system.element.MutableValue;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
/**
 * @author Silvan Wyss
 * @date 2016-06-01
 */
public final class CheckBox extends BorderWidget<CheckBox, CheckBoxLook> {
	
	//constant
	public static final boolean DEFAULT_CHECK_STATE = false;
	
	//constant
	private static final String CHECK_FLAG_HEADER = "Checked";
	
	//attribute
	private final MutableValue<Boolean> checked =
	MutableValue.forBoolean(CHECK_FLAG_HEADER, DEFAULT_CHECK_STATE, this::setCheckFlag);
	
	//constructor
	/**
	 * Creates a new {@link CheckBox}.
	 */
	public CheckBox() {
		reset();
	}
	
	//method
	/**
	 * Checks the current {@link CheckBox}.
	 */
	public void check() {
		checked.setValue(true);
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
	 * @return true if the current {@link CheckBox} is checked.
	 */
	public boolean isChecked() {
		return checked.getValue();
	}
	
	//method
	/**
	 * Unchecks the current {@link CheckBox}.
	 */
	public void uncheck() {
		checked.setValue(false);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CheckBoxLook createLook() {
		return new CheckBoxLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpChildWidgets(final LinkedList<IWidget<?, ?>> list) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<IWidget<?, ?>> list) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaHeight() {
		return getRefActiveLook().getTextSize();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaWidth() {
		return getRefActiveLook().getTextSize();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		setCheckFlag(!isChecked());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {
		//Does nothing.
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final IPainter painter, final CheckBoxLook checkBoxLook) {
				
		final var s = checkBoxLook.getTextSize();
		final var t = checkBoxLook.getLineThickness();
		
		painter.setColor(checkBoxLook.getTextColor());
		
		//Paints the left border of the current CheckBox.
		painter.paintFilledRectangle(0, 0, t, s);
		
		//Paints the right border of the current CheckBox.
		painter.paintFilledRectangle(s - t, 0, t, s);
		
		//Paints the top border of the current CheckBox.
		painter.paintFilledRectangle(0, 0, s, t);
		
		//Paints the bottom border of the current CheckBox.
		painter.paintFilledRectangle(0, s - t, s, t);
		
		//Paints the icon of the current CheckBox if it is checked.
		if (isChecked()) {
			
			final var a = Calculator.getMax(1, (int)(0.75 * t));
			
			//Paints the line from the bottom left corner to to the top right corner of the cross.	
			final int[] bottomLeftToTopRightLineXs = new int[4];
			final int[] bottomLeftToTopRightLineYs = new int[4];
			bottomLeftToTopRightLineXs[0] = a;
			bottomLeftToTopRightLineYs[0] = s;
			bottomLeftToTopRightLineXs[1] = s;
			bottomLeftToTopRightLineYs[1] = a;
			bottomLeftToTopRightLineXs[2] = s - a;
			bottomLeftToTopRightLineYs[2] = 0;
			bottomLeftToTopRightLineXs[3] = 0;
			bottomLeftToTopRightLineYs[3] = s - a;
			painter.paintFilledPolygon(bottomLeftToTopRightLineXs, bottomLeftToTopRightLineYs);
			
			//Paints the line from the bottom right corner to the top left corner of the cross.
			final int[] bottomRightToTopLeftLineXs = new int[4];
			final int[] bottomRightToTopLeftLineYs = new int[4];
			bottomRightToTopLeftLineXs[0] = s;
			bottomRightToTopLeftLineYs[0] = s - a;
			bottomRightToTopLeftLineXs[1] = a;
			bottomRightToTopLeftLineYs[1] = 0;
			bottomRightToTopLeftLineXs[2] = 0;
			bottomRightToTopLeftLineYs[2] = a;
			bottomRightToTopLeftLineXs[3] = s - a;
			bottomRightToTopLeftLineYs[3] = s;
			painter.paintFilledPolygon(bottomRightToTopLeftLineXs, bottomRightToTopLeftLineYs);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateBorderWidget() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidgetConfiguration() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidget() {
		uncheck();
	}
	
	//method
	/**
	 * Sets the check flag of the current {@link CheckBox}.
	 * 
	 * @param checked
	 */
	private void setCheckFlag(final boolean checked) {
		if (!checked) {
			uncheck();
		} else {
			check();
		}
	}
}
