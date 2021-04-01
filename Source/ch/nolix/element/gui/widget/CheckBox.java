//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.math.Calculator;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2016-06-01
 * @lines 340
 */
public final class CheckBox extends BorderWidget<CheckBox, OldCheckBoxLook> {
	
	//constant
	public static final String TYPE_NAME = "Checkbox";
	
	//constant
	private static final String CHECK_FLAG_HEADER = "Checked";
	
	//attribute
	private boolean checked;
	
	//constructor
	/**
	 * Creates a new {@link CheckBox}.
	 */
	public CheckBox() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case CHECK_FLAG_HEADER:
				setCheckFlag(attribute.getOneAttributeAsBoolean());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Checks the current {@link CheckBox}.
	 * 
	 * @return the current {@link CheckBox}.
	 */
	public CheckBox check() {
		
		checked = true;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of base class.
		super.fillUpAttributesInto(list);
		
		list.addAtEnd(Node.withHeaderAndAttribute(CHECK_FLAG_HEADER, isChecked()));
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
		return checked;
	}
	
	//method
	/**
	 * Sets the check flag of the current {@link CheckBox}.
	 * 
	 * @param checked
	 * @return the current {@link CheckBox}.
	 */
	public CheckBox setCheckFlag(boolean checked) {
		
		if (!checked) {
			uncheck();
		} else {
			check();
		}
		
		return this;
	}
	
	//method
	/**
	 * Unchecks the current {@link CheckBox}.
	 * 
	 * @return the current {@link CheckBox}.
	 */
	public CheckBox uncheck() {
		
		checked = false;
		
		return this;
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
	 * @return a new widget look for the current {@link CheckBox}.
	 */
	@Override
	protected OldCheckBoxLook createLook() {
		return new OldCheckBoxLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaHeight() {
		return getRefLook().getRecursiveOrDefaultTextSize();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaWidth() {
		return getRefLook().getRecursiveOrDefaultTextSize();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyPressOnSelfWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
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
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final OldCheckBoxLook checkBoxLook, final IPainter painter) {
				
		final var s = checkBoxLook.getRecursiveOrDefaultTextSize();
		final var t = checkBoxLook.getRecursiveOrDefaultLineThickness();
		
		painter.setColor(checkBoxLook.getRecursiveOrDefaultTextColor());
		
		//Paints the left border of the current check box.
		painter.paintFilledRectangle(0, 0, t, s);
		
		//Paints the right border of the current check box.
		painter.paintFilledRectangle(s - t, 0, t, s);
		
		//Paints the top border of the current check box.
		painter.paintFilledRectangle(0, 0, s, t);
		
		//Paints the bottom border of the current check box.
		painter.paintFilledRectangle(0, s - t, s, t);
		
		//Paints the icon of the current check box if it is checked.
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
	protected void recalculateBorderWidget() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidgetConfigurationOnSelf() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidget() {
		uncheck();
	}
}
