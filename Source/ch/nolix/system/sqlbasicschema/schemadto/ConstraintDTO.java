//package declaration
package ch.nolix.system.sqlbasicschema.schemadto;

import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
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
		
		GlobalValidator.assertThat(type).thatIsNamed(ConstraintType.class).isNotNull();
		
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
