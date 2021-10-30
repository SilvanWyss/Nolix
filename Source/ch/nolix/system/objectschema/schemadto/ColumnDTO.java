//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public final class ColumnDTO implements IColumnDTO {
	
	//attributes
	private String header;
	private IParametrizedPropertyTypeDTO parametrizedPropertyTypeDTO;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public ColumnDTO(final String header, final IParametrizedPropertyTypeDTO parametrizedPropertyTypeDTO) {
		
		if (header == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.HEADER);
		}
		
		if (parametrizedPropertyTypeDTO == null) {
			throw new ArgumentIsNullException(IParametrizedPropertyTypeDTO.class);
		}
		
		this.header = header;
		this.parametrizedPropertyTypeDTO = parametrizedPropertyTypeDTO;
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
	
	//method
	@Override
	public IParametrizedPropertyTypeDTO getParametrizedPropertyType() {
		return parametrizedPropertyTypeDTO;
	}
}
