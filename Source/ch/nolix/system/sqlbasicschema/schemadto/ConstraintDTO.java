//package declaration
package ch.nolix.system.sqlbasicschema.schemadto;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ConstraintType;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IConstraintDTO;

//class
public record ConstraintDTO(ConstraintType type, ImmutableList<String> parameters) implements IConstraintDTO {
	
	//constructor
	public ConstraintDTO(final ConstraintType type, final String... parameters) {
		this(type, ImmutableList.withElements(parameters));
		
	}
	
	//constructor
	public ConstraintDTO(final ConstraintType type, ImmutableList<String> parameters) { //NOSONAR: This implementations checks the given arguments.
		
		GlobalValidator.assertThat(type).thatIsNamed(ConstraintType.class).isNotNull();
		
		this.type = type;
		this.parameters = parameters;
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
