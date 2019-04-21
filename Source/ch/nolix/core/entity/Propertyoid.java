//package declaration
package ch.nolix.core.entity;

import ch.nolix.core.attributeAPI.Named;
//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.validator.Validator;

//abstract class
/**
* @author Silvan Wyss
* @month 2017-10
* @lines 120
* @param <V> The type of the values of a property.
*/
public abstract class Propertyoid<V> implements Named {
	
	//attributes
	private final String name;
	private final IElementTakerElementGetter<DocumentNodeoid, V> valueCreator;
	private final IElementTakerElementGetter<V, DocumentNode> specificationCreator;
	
	//package-visible constructor
	Propertyoid(
		final String name,
		final IElementTakerElementGetter<DocumentNodeoid, V> valueCreator,
		final IElementTakerElementGetter<V, DocumentNode> specificationCreator
	) {
		
		this.name = Validator.suppose(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank().andReturn();
		
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
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName() {
		return name;
	}
	
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
	abstract IContainer<V> getRefValues();
}
