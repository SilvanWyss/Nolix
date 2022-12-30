//package declaration
package ch.nolix.system.objectschema.flatschemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;

//class
public record FlatTableDTO(String id, String name) implements IFlatTableDTO {
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public FlatTableDTO(final String id, final String name) { //NOSONAR: This implementations checks the given arguments.
		
		if (id == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ID);
		}
		
		if (name == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.NAME);
		}
		
		this.id = id;
		this.name = name;
	}
	
	//method
	@Override
	public String getId() {
		return id;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
