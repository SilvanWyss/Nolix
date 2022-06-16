//package declaration
package ch.nolix.system.objectschema.flatschemadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatDatabaseDTO;

//class
public final class FlatDatabaseDTO implements IFlatDatabaseDTO {
	
	//attribute
	private final String name;
	
	//constructor
	//For a better performance, this implementation does not use all comforDatabase methods.
	public FlatDatabaseDTO(final String name) {
		
		if (name == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.NAME);
		}
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
