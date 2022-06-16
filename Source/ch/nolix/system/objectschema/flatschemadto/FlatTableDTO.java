//package declaration
package ch.nolix.system.objectschema.flatschemadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;

//class
public final class FlatTableDTO implements IFlatTableDTO {
	
	//attribute
	private final String id;
	
	//attribute
	private final String name;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public FlatTableDTO(final String id, final String name) {
		
		if (id == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ID);
		}
		
		if (name == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.NAME);
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
