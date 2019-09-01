//package declaration
package ch.nolix.element.widgets;

import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.core.PositiveInteger;

//class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 140
 */
public final class CheckboxLook extends BorderWidgetLook<CheckboxLook> {
	
	//default value
	public static final int DEFAULT_LINE_THICKNESS = 1;
	
	//optional attributes
	private PositiveInteger lineThickness;
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link CheckboxLook}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.LINE_THICKNESS:
				setLineThickness(attribute.getOneAttributeAsInt());
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
	public List<Node> getAttributes() {
		
		//Calls method of the base class.
		final List<Node> attributes = super.getAttributes();
		
		//Handles the case that the current check box look has a line thickness.
		if (hasLineThickness()) {
			attributes.addAtEnd(lineThickness.getSpecificationAs(PascalCaseNameCatalogue.LINE_THICKNESS));
		}
		
		return attributes;
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
	 * @return true if the current {@link CheckboxLook} has a line thickness.
	 */
	public boolean hasLineThickness() {
		return (lineThickness != null);
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
	 * Removes all attributes of the current {@link CheckboxLook}.
	 * 
	 * @return the current {@link CheckboxLook}.
	 */
	@Override
	public CheckboxLook reset() {
		
		//Calls method of the base class.
		super.reset();
		
		removeLineThickness();
		
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
}
