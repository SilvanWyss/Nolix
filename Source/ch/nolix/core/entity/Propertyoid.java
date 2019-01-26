//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
* @author Silvan Wyss
* @month 2017-10
* @lines 120
* @param <V> The type of the values of a property.
*/
public abstract class Propertyoid<V>
extends NamedElement {
	
	//attributes
	private final IElementTakerElementGetter<DocumentNodeoid, V> valueCreator;
	private final IElementTakerElementGetter<V, DocumentNode> specificationCreator;
	
	//package-visible constructor
	Propertyoid(
		final String name,
		final IElementTakerElementGetter<DocumentNodeoid, V> valueCreator,
		final IElementTakerElementGetter<V, DocumentNode> specificationCreator
	) {
		
		//Calls constructor of the base class.
		super(name);
		
		//Checks if the given value creator is not null.
		Validator
		.suppose(valueCreator)
		.thatIsNamed("value creator")
		.isNotNull();
		
		//Checks if the given specification creator is not null.
		Validator
		.suppose(specificationCreator)
		.thatIsNamed("specificaiton creator")
		.isNotNull();
		
		this.valueCreator = valueCreator;
		this.specificationCreator = specificationCreator;
	}
	
	//abstract method
	/**
	 * @return true if this property is approved.
	 */
	public abstract boolean isApproved();
	
	//abstract method
	public abstract boolean isEmpty();
	
	//abstract method
	/**
	 * @return true if this property is mutable.
	 */
	public abstract boolean isMutable();
	
	//package-visible abstract method
	/**
	 * Adds or change the given value to this property.
	 * 
	 * @param value
	 */
	abstract void addOrChangeValue(V value);
	
	//package-visible method
	/**
	 * Adds or changes the value from the given specification to this property.
	 * 
	 * @param specification
	 */
	final void addOrChangeValueFromSpecification(
		final DocumentNodeoid specification
	) {
		addOrChangeValue(valueCreator.getOutput(specification));
	}
	
	//package-visible method
	/**
	 * Approves this property.
	 */
	abstract void approve();
	
	//package-visible method
	final void fillUpAttributes(final List<DocumentNode> attributes) {
		
		//Iterates the values of this property.
		for (final var v : getRefValues()) {
			
			//Creates a specification for the current value.
			final var specification = specificationCreator.getOutput(v);
			specification.setHeader(getName());
			
			attributes.addAtEnd(specification);
		}
	}
	
	//package-visible method
	final List<DocumentNode> getAttributes() {
		
		final var attributes = new List<DocumentNode>();
		fillUpAttributes(attributes);
				
		return attributes;
	}
	
	//package-visible abstract method
	/**
	 * @return the values of this property.
	 */
	abstract ReadContainer<V> getRefValues();
}
