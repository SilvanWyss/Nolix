//package declaration
package ch.nolix.element.core;

import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;

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
