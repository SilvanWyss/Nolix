//package declaration
package ch.nolix.system.sqlbasicschema.schemadto;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IDataTypeDTO;

//class
public final class DataTypeDTO implements IDataTypeDTO {
	
	//attribute
	private final String name;
	
	//optional attribute
	private final String parameter;
	
	//constructor
	public DataTypeDTO(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		
		this.name = name;
		parameter = null;
	}
	
	//constructor
	public DataTypeDTO(final String name, final String parameter) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		Validator.assertThat(parameter).thatIsNamed(LowerCaseCatalogue.PARAMETER).isNotNull();
		
		this.name = name;
		this.parameter = parameter;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getParameter() {
		
		assertHasParameter();
		
		return parameter;
	}
	
	//method
	@Override
	public boolean hasParameter() {
		return (parameter != null);
	}
	
	//method
	private void assertHasParameter() {
		if (!hasParameter()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.PARAMETER);
		}
	}
}
