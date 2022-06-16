//package declaration
package ch.nolix.system.sqlbasicschema.flatschemadto;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.sqlbasicschemaapi.flatschemadtoapi.IFlatTableDTO;

//class
public final class FlatTableDTO implements IFlatTableDTO {
	
	//attribute
	private final String name;
	
	//constructor
	public FlatTableDTO(final String name) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
