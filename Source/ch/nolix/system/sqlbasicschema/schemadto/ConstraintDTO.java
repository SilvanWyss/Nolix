//package declaration
package ch.nolix.system.sqlbasicschema.schemadto;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ConstraintType;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IConstraintDTO;

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
