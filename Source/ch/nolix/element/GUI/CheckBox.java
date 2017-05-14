//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;

//own imports

import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;

//class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 180
 */
public final class CheckBox extends BackgroundWidget<CheckBoxStructure, CheckBox> {

	//constant
	public static final String SIMPLE_CLASS_NAME = "CheckBox";
	
	//attribute headers
	private final String CHECKED_HEADER = "Checked";
	private final String UNCHECKED_HEADER = "Unchecked";
	
	//attribute
	private boolean checked = false;
	
	//constructor
	/**
	 * Creates new check box with default values.
	 */
	public CheckBox() {
		
		//Calls constructor of the base class.
		super(
			new CheckBoxStructure(),
			new CheckBoxStructure(),
			new CheckBoxStructure()
		);
		
		resetConfiguration();
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this check box.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case CHECKED_HEADER:
				check();
				break;
			case UNCHECKED_HEADER:
				uncheck();
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Checks this check box.
	 * 
	 * @return this check box.
	 */
	public CheckBox check() {
		
		checked = true;
		
		return this;
	}
	
	//method
	/**
	 * @return the attributes of this check box.
	 */
	public List<Specification> getAttributes() {
		
		//Calls method of the base class.
		final List<Specification> attributes = super.getAttributes();
		
		if (isChecked()) {
			attributes.addAtEnd(new Specification(CHECKED_HEADER));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return true if this check box has the given role.
	 */
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * @return true if this check box is checked.
	 */
	public boolean isChecked() {
		return checked;
	}
	
	//method
	/**
	 * Unchecks this check box.
	 * 
	 * @return this check box.
	 */
	public CheckBox uncheck() {
		
		checked = false;
		
		return this;
	}
	
	//method
	/**
	 * @return the height of this check box when it is not collapsed. 
	 */
	protected int getHeightWhenNotCollapsed() {
		return getRefCurrentStructure().getActiveSize();
	}
	
	//method
	/**
	 * @return the width of this check box when it is not collapsed.
	 */
	protected final int getWidthWhenNotCollapsed() {
		return getRefCurrentStructure().getActiveSize();
	}
	
	//method
	/**
	 * Paints this check box using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param graphics
	 */
	protected void paint(final CheckBoxStructure widgetStructure, final Graphics graphics) {
		
		//Calls method of the base class.
		super.paint(widgetStructure, graphics);
		
		final int s = widgetStructure.getActiveSize();
		final int t = widgetStructure.getActiveLineThickness();
		
		graphics.setColor(widgetStructure.getActiveLineColor().getJavaColor());
		
		//Paints the left line of this check box.
		graphics.fillRect(0, 0, t, s);
		
		//Paints the right line of this check box.
		graphics.fillRect(s - t, 0, t, s);
		
		//Paints the top line of this check box.
		graphics.fillRect(0, 0, s, t);
		
		//Paints the bottom line of this check box.
		graphics.fillRect(s - t, 0, s, t);
		
		//Paints the icon of this check box if it is checked.
		if (isChecked()) {
			
			final int[] x = new int[4];
			final int[] y = new int[4];
			
			x[0] = 0;		y[0] = t;
			x[1] = t;		y[1] = 0;
			x[2] = s;		y[2] = s - t;
			x[3] = s - t;	y[3] = s;
			
			graphics.fillPolygon(x, y, 4);
		}
	}
}
