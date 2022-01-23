//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

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
