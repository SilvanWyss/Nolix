//package declaration
package ch.nolix.element.widget;

import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 140
 */
public final class CheckboxLook extends BorderWidgetLook<CheckboxLook> {
	
	//constant
	public static final int DEFAULT_LINE_THICKNESS = 1;
	
	//optional attributes
	private int lineThickness;
	
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
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final LinkedList<Node> attributes = super.getAttributes();
		
		//Handles the case that the current check box look has a line thickness.
		if (hasLineThickness()) {
			attributes.addAtEnd(new Node(PascalCaseNameCatalogue.LINE_THICKNESS, lineThickness));
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
			return lineThickness;
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
		return (lineThickness != -1);
	}
	
	//method
	/**
	 * Removes the line thickness of the current {@link CheckboxLook}.
	 * 
	 * @return the current {@link CheckboxLook}.
	 */
	public CheckboxLook removeLineThickness() {
		
		lineThickness = -1;
		
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
		
		//Asserts that the given lineThickness is positive.
		Validator.assertThat(lineThickness).thatIsNamed(VariableNameCatalogue.LINE_THICKNESS).isPositive();
		
		//Sets the line thickness of the current CheckBoxLook.
		this.lineThickness = lineThickness;
		
		return this;
	}
}
