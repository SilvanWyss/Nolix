//package declaration
package ch.nolix.element.dialog;

//Java import
import java.awt.Graphics;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.util.Validator;
import ch.nolix.element.data.Size;

//class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 160
 */
public final class CheckBox extends Rectangle<CheckBoxStructure, CheckBox> {

	//constants
	public static final String SIMPLE_CLASS_NAME = "CheckBox";
	public static final int THICKNESS = 2;
	public static final int MIN_SIZE = 5;
	
	//default values
	public static final int DEFAULT_SIZE = 10;
	
	//attribute specifications
	private final String CHECKED = "Checked";
	private final String UNCHECKED = "Unchecked";
	
	//attributes
	private boolean checked = false;
	private final Size size = new Size();
	
	//constructor
	/**
	 * Creates new check box with default attributes.
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
	 * Applies the default configuration to this check box.
	 */
	public final void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		setSize(DEFAULT_SIZE);
	}
	
	//method
	/**
	 * Checks this check box.
	 */
	public final void check() {
		checked = true;
	}
	
	//method
	/**
	 * @return the attributes of this check box
	 */
	public final List<Specification> getAttributes() {
		
		//Calls constructor of the base class.
		List<Specification> attributes = super.getAttributes();
		
		if (isChecked()) {
			attributes.addAtEnd(new Specification(CHECKED));
		}
		
		if (getSize() != DEFAULT_SIZE) {
			attributes.addAtEnd(size.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the size of this check box
	 */
	public final int getSize() {
		return size.getValue();
	}
	
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * @return true if this check box is checked
	 */
	public final boolean isChecked() {
		return checked;
	}
	
	//method
	/**
	 * Sets the given attribute to this check box.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public final void addOrChangeAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
			case CHECKED:
				check();
				break;
			case UNCHECKED:
				uncheck();
				break;
			case Size.SIMPLE_CLASS_NAME:
				setSize(attribute.getOneAttributeToInteger());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Sets the size of this check box.
	 * 
	 * @param size
	 * @throws Exception if the given size is smaller than the min size of a check box
	 */
	public final void setSize(int size) {
		
		Validator.throwExceptionIfValueIsSmaller("size", MIN_SIZE, size);
		
		this.size.setValue(size);
	}
	
	//method
	/**
	 * Unchecks this check box.
	 */
	public final void uncheck() {
		checked = false;
	}
	
	//method
	/**
	 * @return the current height of the content of this check box
	 */
	protected final int getContentHeight() {
		return getSize();
	}
	
	//method
	/**
	 * @return the current width of the content of this check box
	 */
	protected final int getContentWidth() {
		return getSize();
	}
	
	//method
	/**
	 * Paints this check box using the given rectangle structure and graphics.
	 * 
	 * @param rectangleStructure
	 * @param graphics
	 */
	protected final void paintContent(CheckBoxStructure rectangleStructure, Graphics graphics) {
		
		//Paints left line of this check box.
		graphics.drawRect(0, 0, THICKNESS, getSize());
		
		//Paints right line of this check box.
		graphics.drawRect(getSize() - THICKNESS, 0, THICKNESS, getSize());
		
		//Paints top line of this check box.
		graphics.drawRect(0, 0, getSize(), THICKNESS);
		
		//Paints bottom line of this check box.
		graphics.drawRect(getSize() - THICKNESS, 0, getSize(), THICKNESS);		
	}
}
