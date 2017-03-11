/*
 * file:	RectangleStructure.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	80
 */

//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.util.Validator;

//class
/**
 * A rectangle structure stores a state-dependent part of a rectangle.
 */
public abstract class WidgetStructure<RS extends WidgetStructure<RS>> {
	
	//optional attribute
	private RS normalStructure;
		
	//method
	/**
	 * @return the attributes of this rectangle structure
	 */
	protected List<Specification> getAttributes() {
		return new List<Specification>();
	}
	
	//method
	/**
	 * @return the normal structure of this rectangle
	 * @throws Exception if this rectangle structure has no normal structure
	 */
	protected final RS getRefNormalStructure() {
		
		if (!hasNormalStructure()) {
			throw new UnexistingAttributeException(this, "normal structure");
		}
		
		return normalStructure;
	}
	
	//abstract method
	/**
	 * Sets the given attribute to this rectangle structure.
	 * 
	 * @param attribute
	 */
	protected void setAttribute(Specification attribute) {}
	
	//method
	/**
	 * @return true if this rectangle structure has a normal structure
	 */
	final boolean hasNormalStructure() {
		return (normalStructure != null);
	}
	
	//method
	/**
	 * Sets the normal structure of this rectangle structure.
	 * 
	 * @param normalStructure
	 * @throws Exception if:
	 * -the given normal structure is null
	 * -this rectangle structure already has a normal structure
	 */
	@SuppressWarnings("unchecked")
	final void setNormalStructure(WidgetStructure<?> normalStructure) {
		
		Validator.throwExceptionIfValueIsNull("normal structure", normalStructure);
		
		if (hasNormalStructure()) {
			throw new RuntimeException("Rectangle structure already has a normal structure.");
		}
		
		this.normalStructure = (RS)normalStructure;
	}
}
