//package declaration
package ch.nolix.system.objectschema.flatschemadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;

//class
public final class FlatTableDTO implements IFlatTableDTO {
	
	//attribute
	private final String name;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public FlatTableDTO(final String name) {
		
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
