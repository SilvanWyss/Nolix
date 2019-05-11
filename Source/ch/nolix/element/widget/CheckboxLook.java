//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.PositiveInteger;

//class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 280
 */
public final class CheckboxLook
extends BackgroundWidgetLook<CheckboxLook> {
	
	//default values
	public static final int DEFAULT_SIZE = 20;
	public static final int DEFAULT_LINE_THICKNESS = 1;
	public static final Color DEFAULT_LINE_COLOR = Color.BLACK;
	
	//optional attributes
	private PositiveInteger size;
	private PositiveInteger lineThickness;
	private Color lineColor;
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link CheckboxLook}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.SIZE:
				setSize(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseNameCatalogue.LINE_THICKNESS:
				setLineThickness(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseNameCatalogue.LINE_COLOR:
				setLineColor(new Color(attribute.getOneAttributeAsString()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of the current {@link CheckboxLook}.
	 */
	@Override
	public List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		final List<DocumentNode> attributes = super.getAttributes();
		
		//Handles the case that the current check box look has a size.
		if (hasSize()) {
			attributes.addAtEnd(size.getSpecificationAs(PascalCaseNameCatalogue.SIZE));
		}
		
		//Handles the case that the current check box look has a line thickness.
		if (hasLineThickness()) {
			attributes.addAtEnd(lineThickness.getSpecificationAs(PascalCaseNameCatalogue.LINE_THICKNESS));
		}
		
		//Handles the case that the current check box look has a line color.
		if (hasLineColor()) {
			attributes.addAtEnd(lineColor.getSpecificationAs(PascalCaseNameCatalogue.LINE_COLOR));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the recursive or default line color of the current {@link CheckboxLook}.
	 */
	public Color getRecursiveOrDefaultLineColor() {
		
		//Handles the case that the current check box look has a line color.
		if (hasLineColor()) {
			return lineColor;
		}
		
		//Handles the case that the current check box look
		//does not have a line color but a normal structure.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultLineColor();
		}
		
		//Handles the case that the current check box look
		//does not have a line color or normal structure.
		return DEFAULT_LINE_COLOR;
	}
	
	//method
	/**
	 * @return the recursive or default line thickness of the current {@link CheckboxLook}.
	 */
	public int getRecursiveOrDefaultLineThickness() {
		
		//Handles the case that the current check box look has a line thickness.
		if (hasLineThickness()) {
			return lineThickness.getValue();
		}
		
		//Handles the case that the current check box look
		//does not have a line thickness but a normal structure.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultLineThickness();
		}
		
		//Handles the case that the current check box look
		//does not have a line thickness or normal structure.
		return DEFAULT_LINE_THICKNESS;
	}
	
	//method
	/**
	 * @return the active size of the current {@link CheckboxLook}.
	 */
	public int getRecursiveOrDefaultSize() {
		
		//Handles the case that the current check box look has a size.
		if (hasSize()) {
			return size.getValue();
		}
		
		//Handles the case that the current check box look
		//does not have a size but a normal structure.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultSize();
		}
		
		//Handles the case that the current check box look
		//does not have a size or normal structure.
		return DEFAULT_SIZE;
	}
	
	//method
	/**
	 * @return true if the current {@link CheckboxLook} has a line color.
	 */
	public boolean hasLineColor() {
		return (lineColor != null);
	}
	
	//method
	/**
	 * @return true if the current {@link CheckboxLook} has a line thickness.
	 */
	public boolean hasLineThickness() {
		return (lineThickness != null);
	}
	
	//method
	/**
	 * @return the size of the current {@link CheckboxLook}.
	 */
	public boolean hasSize() {
		return (size != null);
	}
	
	//method
	/**
	 * Removes the line color of the current {@link CheckboxLook}.
	 * 
	 * @return the current {@link CheckboxLook}.
	 */
	public CheckboxLook removeLineColor() {
		
		lineColor = null;
		
		return this;
	}
	
	//method
	/**
	 * Removes the line thickness of the current {@link CheckboxLook}.
	 * 
	 * @return the current {@link CheckboxLook}.
	 */
	public CheckboxLook removeLineThickness() {
		
		lineThickness = null;
		
		return this;
	}
	
	//method
	/**
	 * Removes the size of the current {@link CheckboxLook}.
	 * 
	 * @return the current {@link CheckboxLook}.
	 */
	public CheckboxLook removeSize() {
		
		size = null;
		
		return this;
	}
	
	//method
	/**
	 * Removes all attributes of the current {@link CheckboxLook}.
	 * 
	 * @return the current {@link CheckboxLook}.
	 */
	@Override
	public CheckboxLook reset() {
		
		//Calls method of the base class.
		super.reset();
		
		removeSize();
		removeLineThickness();
		removeLineColor();
		
		return this;
	}
	
	//method
	/**
	 * Sets the line color of the current {@link CheckboxLook}.
	 * 
	 * @param lineColor
	 * @return the current {@link CheckboxLook}.
	 * @throws NullArgumentException if the given line color is null.
	 */
	public CheckboxLook setLineColor(final Color lineColor) {
		
		//Checks if the given line color is not null.
		Validator.suppose(lineColor).thatIsNamed("line color").isNotNull();
		
		//Sets the line color of the current {@link CheckBoxLook}.
		this.lineColor = lineColor;
		
		return this;
	}
	
	//method
	/**
	 * Sets the line thickness of the current {@link CheckboxLook}.
	 * 
	 * @param lineThickness
	 * @return the current {@link CheckboxLook}.
	 * @throws NonPositiveArgumentException if the given line thickness is not positive.
	 */
	public CheckboxLook setLineThickness(final int lineThickness) {
		
		//Sets the line thickness of the current {@link CheckBoxLook}.
		this.lineThickness = new PositiveInteger(lineThickness);
		
		return this;
	}
	
	//method
	/**
	 * Sets the size of the current {@link CheckboxLook}.
	 * 
	 * @param size
	 * @return the current {@link CheckboxLook}.
	 * @throws NonPositiveArgumentException if the given size is not positive.
	 */
	public CheckboxLook setSize(final int size) {
		
		//Sets the size of the current {@link CheckBoxLook}.
		this.size = new PositiveInteger(size);
		
		return this;
	}
}
