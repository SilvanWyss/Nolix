//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.skillInterfaces.Named;
import ch.nolix.core.validator2.Validator;

//class
public final class Flag extends Element implements Named {

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
	public String getName() {
		return name;
	}
}
