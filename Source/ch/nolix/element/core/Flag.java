//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.skillAPI.Named;
import ch.nolix.core.validator2.Validator;

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
