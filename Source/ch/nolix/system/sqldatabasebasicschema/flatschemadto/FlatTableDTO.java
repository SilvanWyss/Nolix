//package declaration
package ch.nolix.system.sqldatabasebasicschema.flatschemadto;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.flatschemadtoapi.IFlatTableDTO;

//class
public record FlatTableDTO(String name) implements IFlatTableDTO {
	
	//constructor
	public FlatTableDTO(final String name) { //NOSONAR: This implementations checks the given arguments.
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
