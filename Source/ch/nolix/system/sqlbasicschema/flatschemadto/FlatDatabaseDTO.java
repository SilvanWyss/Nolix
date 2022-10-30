//package declaration
package ch.nolix.system.sqlbasicschema.flatschemadto;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.sqlbasicschemaapi.flatschemadtoapi.IFlatDatabaseDTO;

//class
public record FlatDatabaseDTO(String name) implements IFlatDatabaseDTO {
	
	//constructor
	public FlatDatabaseDTO(final String name) { //NOSONAR
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
