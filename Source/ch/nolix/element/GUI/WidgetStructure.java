//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A widget structure stores state-dependent attributes of a widget.
 * 
 * When a widget structure is asked to return an active value the following approach must be proceeded.
 * Step 1: If the widget structure has a value it must return that value.
 *         If the widget structure has no value step 2 must be proceeded.
 * Step 2: If the widget structure has a normal structure it must return the active value of its normal structure.
 *         If the widget structure has no normal structure step 3 must be proceeded.
 * Step 3: The widget structure must return a default active value.
 * 
 * A default active value need not to be a valid value for the according setter method.
 * For example, the default active border size is 0, but the border size can be set only to a positive value.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 90
 * @param <WS> The type of a widget structure.
 */
public abstract class WidgetStructure<WS extends WidgetStructure<WS>> {
	
	//optional attribute
	private WS normalStructure;
	
	//abstract method
	/**
	 * Adds or changes the given attribute to this widget structure.
	 * 
	 * @param attribute
	 */
	protected abstract void addOrChangeAttribute(StandardSpecification attribute);
		
	//abstract method
	/**
	 * @return the attributes of this widget structure.
	 */
	protected abstract List<StandardSpecification> getAttributes();
	
	//method
	/**
	 * @return the normal structure of this widget structure.
	 * @throws UnexistingAttributeException if this widget structure has no normal structure.
	 */
	protected final WS getRefNormalStructure() {
		
		//Checks if this widget structure has a normal structure.
		if (!hasNormalStructure()) {
			throw new UnexistingAttributeException(this, "normal structure");
		}
		
		return normalStructure;
	}
	
	//method
	/**
	 * @return true if this widget structure has a normal structure.
	 */
	protected final boolean hasNormalStructure() {
		return (normalStructure != null);
	}
	
	//abstract method
	/**
	 * Removes all attributes of this widget structure.
	 */
	protected abstract void removeAttributes();
	
	//package-visible method
	/**
	 * Sets the normal structure of this widget structure.
	 * 
	 * @param normalStructure
	 * @throws NullArgumentException if the given normal structure is null.
	 */
	final void setNormalStructure(final WS normalStructure) {
		
		//Checks if the given normal structure is not null.
		Validator.supposeThat(normalStructure).thatIsNamed("normal structure").isNotNull();
		
		//Sets the normal structure of this widget structure.
		this.normalStructure = normalStructure;
	}
}
