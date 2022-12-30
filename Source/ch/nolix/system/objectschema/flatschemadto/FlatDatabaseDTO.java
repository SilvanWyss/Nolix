//package declaration
package ch.nolix.system.objectschema.flatschemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatDatabaseDTO;

//class
public record FlatDatabaseDTO(String name) implements IFlatDatabaseDTO {
	
	//constructor
	//For a better performance, this implementation does not use all comforDatabase methods.
	public FlatDatabaseDTO(final String name) { //NOSONAR: This implementations checks the given arguments.
		
		if (name == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.NAME);
		}
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
