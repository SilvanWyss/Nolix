//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.PositiveInteger;

//class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 260
 */
public final class CheckBoxStructure
extends BackgroundWidgetStructure<CheckBoxStructure> {
	
	//default values
	public static final int DEFAULT_SIZE = 10;
	public static final int DEFAULT_LINE_THICKNESS = 1;
	public static final Color DEFAULT_LINE_COLOR = Color.BLACK;
	
	//attribute headers
	private static final String SIZE_HEADER = "Size";
	private static final String LINE_THICKNESS_HEADER = "LineThickness";
	private static final String LINE_COLOR_HEADER = "LineColor";
	
	//optional attributes
	private PositiveInteger size;
	private PositiveInteger lineThickness;
	private Color lineColor;
	
	//method
	/**
	 * @return the active line color of this check box structure.
	 */
	public Color getActiveLineColor() {
		
		//Handles the case if this check box structure has a line color.
		if (hasLineColor()) {
			return lineColor;
		}
		
		//Handles the case if this check box structure
		//has no line color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveLineColor();
		}
		
		//Handles the case if this check box structure
		//has no line color and no normal structure.
		return DEFAULT_LINE_COLOR;
	}
	
	//method
	/**
	 * @return the active line thickness of this check box structure.
	 */
	public int getActiveLineThickness() {
		
		//Handles the case if this check box structure has a line thickness.
		if (hasLineThickness()) {
			return lineThickness.getValue();
		}
		
		//Handles the case if this check box structure
		//has no line thickness but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveLineThickness();
		}
		
		//Handles the case if this check box structure
		//has no line thickness and no normal structure.
		return DEFAULT_LINE_THICKNESS;
	}
	
	//method
	/**
	 * @return the active size of this check box structure.
	 */
	public int getActiveSize() {
		
		//Handles the case if this check box structure has a size.
		if (hasSize()) {
			return size.getValue();
		}
		
		//Handles the case if this check box structure
		//has no size but a normal structure.
		if (hasNormalStructure()) {
			getRefNormalStructure().getActiveSize();
		}
		
		//Handles the case if this check box structure
		//has no size and no normal structure.
		return DEFAULT_SIZE;
	}
	
	//method
	/**
	 * @return true if this check box structure has a line color.
	 */
	public boolean hasLineColor() {
		return (lineColor != null);
	}
	
	//method
	/**
	 * @return true if this check box structure has a line thickness.
	 */
	public boolean hasLineThickness() {
		return (lineThickness != null);
	}
	
	//method
	/**
	 * @return the size of this check box structure.
	 */
	public boolean hasSize() {
		return (size != null);
	}
	
	//method
	/**
	 * Removes all attributes of this check box structure.
	 */
	public void clearProperties() {
		
		//Calls method of the base class.
		super.clearProperties();
		
		removeSize();
		removeLineThickness();
		removeLineColor();
	}
	
	//method
	/**
	 * Removes the line color of this check box structure.
	 */
	public void removeLineColor() {
		lineColor = null;
	}
	
	//method
	/**
	 * Removes the line thickness of this check box structure.
	 */
	public void removeLineThickness() {
		lineThickness = null;
	}
	
	//method
	/**
	 * Removes the size of this check box structure.
	 */
	public void removeSize() {
		size = null;
	}
	
	//method
	/**
	 * Sets the line color of this check box structure.
	 * 
	 * @param lineColor
	 * @return this check box structure.
	 * @throws NullArgumentException if the given line color is null.
	 */
	public CheckBoxStructure setLineColor(final Color lineColor) {
		
		//Checks if the given line color is not null.
		Validator.suppose(lineColor).thatIsNamed("line color").isNotNull();
		
		//Sets the line color of this check box structure.
		this.lineColor = lineColor;
		
		return this;
	}
	
	//method
	/**
	 * Sets the line thickness of this check box structure.
	 * 
	 * @param lineThickness
	 * @return this check box structure.
	 * @throws NonPositiveArgumentException if the given line thickness is not positive.
	 */
	public CheckBoxStructure setLineThickness(final int lineThickness) {
		
		//Sets the line thickness of this check box structure.
		this.lineThickness = new PositiveInteger(lineThickness);
		
		return this;
	}
	
	//method
	/**
	 * Sets the size of this check box structure.
	 * 
	 * @param size
	 * @return this check box structure.
	 * @throws NonPositiveArgumentException if the given size is not positive.
	 */
	public CheckBoxStructure setSize(final int size) {
		
		//Sets the size of this check box structure.
		this.size = new PositiveInteger(size);
		
		return this;
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this check box structure.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case SIZE_HEADER:
				setSize(attribute.getOneAttributeToInteger());
				break;
			case LINE_THICKNESS_HEADER:
				setLineThickness(attribute.getOneAttributeToInteger());
				break;
			case LINE_COLOR_HEADER:
				setLineColor(new Color(attribute.getOneAttributeToString()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of this check box structure.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the option that this check box structure has a size.
		if (hasSize()) {
			attributes.addAtEnd(size.getSpecificationAs(SIZE_HEADER));
		}
		
		//Handles the option that this check box structure has a line thickness.
		if (hasLineThickness()) {
			attributes.addAtEnd(lineThickness.getSpecificationAs(LINE_THICKNESS_HEADER));
		}
		
		//Handles the option that this check box structure has a line color.
		if (hasLineColor()) {
			attributes.addAtEnd(lineColor.getSpecificationAs(LINE_COLOR_HEADER));
		}
		
		return attributes;
	}
}
