//package declaration
package ch.nolix.system.sqlschema.schemadto;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ConstraintType;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IConstraintDTO;

//class
public final class ConstraintDTO implements IConstraintDTO {
	
	//attribute
	private final ConstraintType type;
	
	//multi-attribute
	private final IContainer<String> parameters;
	
	//constructor
	public ConstraintDTO(final ConstraintType type, final String... parameters) {
		
		Validator.assertThat(type).thatIsNamed(ConstraintType.class).isNotNull();
		
		this.type = type;
		this.parameters = ReadContainer.forArray(parameters);
	}
	
	//method
	@Override
	public IContainer<String> getParameters() {
		return parameters;
	}
	
	//method
	@Override
	public ConstraintType getType() {
		return type;
	}
}
