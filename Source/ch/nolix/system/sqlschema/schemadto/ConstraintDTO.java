//package declaration
package ch.nolix.system.sqlschema.schemadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ConstraintType;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IConstraintDTO;

//class
public final class ConstraintDTO implements IConstraintDTO {
	
	//attribute
	private final ConstraintType type;
	
	//optional attribute
	private final String parameter;
	
	//constructor
	public ConstraintDTO(final ConstraintType type) {
		
		Validator.assertThat(type).thatIsNamed(ConstraintType.class).isNotNull();
		
		this.type = type;
		parameter = null;
	}
	
	//constructor
	public ConstraintDTO(final ConstraintType type, final String parameter) {
		
		Validator.assertThat(type).thatIsNamed(ConstraintType.class).isNotNull();
		Validator.assertThat(parameter).thatIsNamed(LowerCaseCatalogue.PARAMETER).isNotNull();
		
		this.type = type;
		this.parameter = parameter;
	}
	
	//method
	@Override
	public String getParameter() {
		
		assertHasParameter();
		
		return parameter;
	}
	
	//method
	@Override
	public ConstraintType getType() {
		return type;
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
