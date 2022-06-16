//package declaration
package ch.nolix.system.objectschema.schemadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public final class ColumnDTO implements IColumnDTO {
	
	//attribute
	private final String id;
	
	//attribute
	private final String name;
	
	//attribute
	private final IParametrizedPropertyTypeDTO parametrizedPropertyTypeDTO;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public ColumnDTO(
		final String id,
		final String name,
		final IParametrizedPropertyTypeDTO parametrizedPropertyTypeDTO
	) {
		
		if (id == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ID);
		}
		
		if (name == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.HEADER);
		}
		
		if (parametrizedPropertyTypeDTO == null) {
			throw new ArgumentIsNullException(IParametrizedPropertyTypeDTO.class);
		}
		
		this.id = id;
		this.name = name;
		this.parametrizedPropertyTypeDTO = parametrizedPropertyTypeDTO;
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
	
	//method
	@Override
	public IParametrizedPropertyTypeDTO getParametrizedPropertyType() {
		return parametrizedPropertyTypeDTO;
	}
}
