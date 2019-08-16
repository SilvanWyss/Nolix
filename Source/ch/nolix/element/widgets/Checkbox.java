//package declaration
package ch.nolix.element.widgets;

import ch.nolix.core.containers.List;
import ch.nolix.core.math.Calculator;
import ch.nolix.core.node.Node;
import ch.nolix.core.node.BaseNode;
import ch.nolix.element.GUI_API.Widget;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.Boolean;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 280
 */
public final class Checkbox extends BorderWidget<Checkbox, CheckboxLook> {

	//constant
	public static final String TYPE_NAME = "Checkbox";
	
	//constant
	private static final String CHECK_FLAG_HEADER = "Checked";
	
	//attribute
	private boolean checked = false;
	
	//constructor
	/**
	 * Creates a new {@link Checkbox}.
	 */
	public Checkbox() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link Checkbox}.
	 * 
	 * @param checked
	 */
	public Checkbox(final boolean checked) {
		
		resetAndApplyDefaultConfiguration();
		
		setCheckFlag(checked);
	}
	
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
	 * Checks the current {@link Checkbox}.
	 * 
	 * @return the current {@link Checkbox}.
	 */
	public Checkbox check() {
		
		checked = true;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Node> getInteractionAttributes() {
		
		//Calls method of the base class.
		final var interactionAttributes = super.getInteractionAttributes();
		
		interactionAttributes.addAtEnd(
			new Boolean(isChecked()).getSpecificationAs(CHECK_FLAG_HEADER)
		);
		
		return interactionAttributes;
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
	 * @return true if the current {@link Checkbox} is checked.
	 */
	public boolean isChecked() {
		return checked;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonPressOnViewAreaWhenEnabled() {
		setCheckFlag(!isChecked());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Checkbox reset() {
		
		super.reset();
		
		uncheck();
		
		return this;
	}
	
	//method
	/**
	 * Sets the check flag of the current {@link Checkbox}.
	 * 
	 * @param checked
	 * @return the current {@link Checkbox}.
	 */
	public Checkbox setCheckFlag(boolean checked) {
		
		if (!checked) {
			uncheck();
		}
		else {
			check();
		}
		
		return this;
	}
	
	//method
	/**
	 * Unchecks the current {@link Checkbox}.
	 * 
	 * @return the current {@link Checkbox}.
	 */
	public Checkbox uncheck() {
		
		checked = false;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		getRefBaseLook()
		.setTextSize(20)
		.setTextColor(Color.BLACK);
	}
	
	//method
	/**
	 * @return a new widget look for the current {@link Checkbox}.
	 */
	@Override
	protected CheckboxLook createLook() {
		return new CheckboxLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpChildWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpPaintableWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaHeight() {
		return getRefLook().getRecursiveOrDefaultTextSize();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaWidth() {
		return getRefLook().getRecursiveOrDefaultTextSize();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final CheckboxLook checkBoxLook, final IPainter painter) {
		
		//Calls method of the base class.
		super.paintContentArea(checkBoxLook, painter);
		
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
				
				bottomLeftToTopRightLineXs[0] = a;		bottomLeftToTopRightLineYs[0] = s;
				bottomLeftToTopRightLineXs[1] = s;		bottomLeftToTopRightLineYs[1] = a;
				bottomLeftToTopRightLineXs[2] = s - a;	bottomLeftToTopRightLineYs[2] = 0;
				bottomLeftToTopRightLineXs[3] = 0;		bottomLeftToTopRightLineYs[3] = s - a;
				
				painter.paintFilledPolygon(bottomLeftToTopRightLineXs, bottomLeftToTopRightLineYs);
				
			//Paints the line from the bottom right corner to the top left corner of the cross.
				final int[] bottomRightToTopLeftLineXs = new int[4];
				final int[] bottomRightToTopLeftLineYs = new int[4];
				
				bottomRightToTopLeftLineXs[0] = s;		bottomRightToTopLeftLineYs[0] = s - a;
				bottomRightToTopLeftLineXs[1] = a;		bottomRightToTopLeftLineYs[1] = 0;
				bottomRightToTopLeftLineXs[2] = 0;		bottomRightToTopLeftLineYs[2] = a;
				bottomRightToTopLeftLineXs[3] = s - a;	bottomRightToTopLeftLineYs[3] = s;
				
				painter.paintFilledPolygon(bottomRightToTopLeftLineXs, bottomRightToTopLeftLineYs);
		}
	}
}
