//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @date 2016-06-01
 * @lines 130
 */
public final class CheckBoxLook extends BorderWidgetLook<CheckBoxLook> {
	
	//constant
	public static final int DEFAULT_LINE_THICKNESS = 1;
	
	//optional attributes
	private int lineThickness = -1;
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link CheckBoxLook}.
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
	 * @return the attributes of the current {@link CheckBoxLook}.
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final LinkedList<Node> attributes = super.getAttributes();
		
		//Handles the case that the current check box look has a line thickness.
		if (hasLineThickness()) {
			attributes.addAtEnd(Node.withHeaderAndAttribute(PascalCaseNameCatalogue.LINE_THICKNESS, lineThickness));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the recursive or default line thickness of the current {@link CheckBoxLook}.
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
	 * @return true if the current {@link CheckBoxLook} has a line thickness.
	 */
	public boolean hasLineThickness() {
		return (lineThickness != -1);
	}
	
	//method
	/**
	 * Removes the line thickness of the current {@link CheckBoxLook}.
	 * 
	 * @return the current {@link CheckBoxLook}.
	 */
	public CheckBoxLook removeLineThickness() {
		
		lineThickness = -1;
		
		return this;
	}
	
	//method
	/**
	 * Sets the line thickness of the current {@link CheckBoxLook}.
	 * 
	 * @param lineThickness
	 * @return the current {@link CheckBoxLook}.
	 * @throws NonPositiveArgumentException if the given line thickness is not positive.
	 */
	public CheckBoxLook setLineThickness(final int lineThickness) {
		
		//Asserts that the given lineThickness is positive.
		Validator.assertThat(lineThickness).thatIsNamed(VariableNameCatalogue.LINE_THICKNESS).isPositive();
		
		//Sets the line thickness of the current CheckBoxLook.
		this.lineThickness = lineThickness;
		
		return this;
	}
}
