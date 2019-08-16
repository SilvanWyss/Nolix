//package declaration
package ch.nolix.element.base;

import ch.nolix.core.node.BaseNode;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.baseAPI.IMutableElement;

//abstract class
/**
 * 
 * A {@link SpecifiableEntity} is a {@link Element} that is specifiable..
 * 
 * @author Silvan Wyss
 * @month 2018-02
 * @lines 40
 */
public abstract class SpecifiableEntity<SE extends SpecifiableEntity<SE>>
extends Element<SE>
implements IMutableElement<SE> {
	
	//class
	/**
	 * Creates a new {@link SpecifiableEntity}.
	 * 
	 * @throws InvalidArgumentException
	 * if the current {@link SpecifiableEntity} does not contain a mutable property.
	 */
	public SpecifiableEntity() {
		
		//Checks if the current SpecifiableEntity does not contain a a mutable property.
		Validator.suppose(getRefProperties()).contains(p -> p.isMutable());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Calls method of the base class.
		super.addOrChangeAttribute(attribute);
	}
}
