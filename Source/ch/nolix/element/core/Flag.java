//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.attributeAPI.Named;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.element.Element;

//class
public final class Flag extends Element<Flag> implements Named {

	//attribute
	private final String name;
	
	//constructor
	public Flag(final String name) {
		
		Validator
		.suppose(name)
		.thatIsNamed(VariableNameCatalogue.NAME)
		.isNotEmpty();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
