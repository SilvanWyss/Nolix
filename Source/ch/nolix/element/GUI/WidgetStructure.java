//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A widget structure stores state-dependent attributes of a widget.
 * All attributes a widget structure can have are optional.
 * 
 * For each attribute A, a widget structure has a method hasRecursiveA().
 * A method hasRecursiveAA must have the following scheme.
 * Step 1: If the widget structure has a value, the hasRecursiveA() must return true.
 * Step 2: If the widget structure has a normal structure, hasRecursiveA()
 *         must return hasRecursiveA() of the normal structure.
 * Step 3: hasRecursiveA() must return false.
 * 
 * For each attribute A, a widget structure has a method getActiveA().
 * Step 1: If the widget structure has a value, getActiveA must return that value.
 * Step 2: If the widget structure has a normal structure, getActiveA()
 *         must return getActiveA() of the normal structure.
 * Step 3: If the widget structure has a condition for a smart default value
 *         and the condition is fulfilled,
 *         getActiveOrDefaultA must return the smart default value.
 * Step 4: The widget structure must return a default value.
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
