//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDto;

//class
public final class ColumnDto implements IColumnDto {
	
	//attribute
	private final String id;
	
	//attribute
	private final String name;
	
	//attribute
	private final IParametrizedPropertyTypeDto parametrizedPropertyTypeDto;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public ColumnDto(
		final String id,
		final String name,
		final IParametrizedPropertyTypeDto parametrizedPropertyTypeDto
	) {
		
		if (id == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ID);
		}
		
		if (name == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.HEADER);
		}
		
		if (parametrizedPropertyTypeDto == null) {
			throw ArgumentIsNullException.forArgumentType(IParametrizedPropertyTypeDto.class);
		}
		
		this.id = id;
		this.name = name;
		this.parametrizedPropertyTypeDto = parametrizedPropertyTypeDto;
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
	public IParametrizedPropertyTypeDto getParametrizedPropertyType() {
		return parametrizedPropertyTypeDto;
	}
}
