//package declaration
package ch.nolix.system.sqlbasicschema.flatschemadto;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;
import ch.nolix.systemapi.sqlbasicschemaapi.flatschemadtoapi.IFlatDatabaseDTO;

//class
public final class FlatDatabaseDTO implements IFlatDatabaseDTO {
	
	//attribute
	private final String name;
	
	//constructor
	public FlatDatabaseDTO(final String name) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
