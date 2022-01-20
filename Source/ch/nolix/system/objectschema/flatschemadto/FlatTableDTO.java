//package declaration
package ch.nolix.system.objectschema.flatschemadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;

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
