//package declaration
package ch.nolix.system.sqlschema.flatschemadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.systemapi.sqlschemaapi.flatschemadtoapi.IFlatDatabaseDTO;

//class
public final class FlatDatabaseDTO implements IFlatDatabaseDTO {
	
	//attribute
	private final String name;
	
	//constructor
	public FlatDatabaseDTO(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
