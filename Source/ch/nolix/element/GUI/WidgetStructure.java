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
 * When a widget structure is asked to return an attribute the following approach must be proceeded.
 * Step 1: If the widget structure has currently a value for the attribute it must return that value.
 *         If the widget structure has currently no value for the attribute step 2 must be proceeded.
 * Step 2: If the widget structure has a normal structure it must return what its normal structure returns for the attribute.
 *         If the widget structure has no normal structure step 3 must be proceeded.
 * Step 3: If the widget structure has a default value for the attribute it must return that default value.
 *         If the widget structure has no default value for the attribute it must throw an UnexistingAttributeException.
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
