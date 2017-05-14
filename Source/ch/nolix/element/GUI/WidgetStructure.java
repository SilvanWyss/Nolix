//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A widget structure stores state-dependent attributes of a widget.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 80
 * @param <WS> - The type of a widget structure.
 */
public abstract class WidgetStructure<WS extends WidgetStructure<WS>> {
	
	//optional attribute
	private WS normalStructure;
	
	//method
	/**
	 * Adds or changes the given attribute to this widget structure.
	 * 
	 * @param attribute
	 */
	protected void addOrChangeAttribute(final Specification attribute) {}
		
	//method
	/**
	 * @return the attributes of this widget structure.
	 */
	protected List<Specification> getAttributes() {
		return new List<Specification>();
	}
	
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
	
	//method
	/**
	 * Removes all attributes of this widget structure.
	 */
	protected void removeAttributes() {}
	
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
