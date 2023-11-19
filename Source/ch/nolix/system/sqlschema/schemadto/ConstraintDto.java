//package declaration
package ch.nolix.system.sqlschema.schemadto;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ConstraintType;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IConstraintDto;

//class
public record ConstraintDto(ConstraintType type, ImmutableList<String> parameters) implements IConstraintDto {

  //constructor
  public ConstraintDto(final ConstraintType type, final String parameter, final String... parameters) {
    this(type, ImmutableList.withElement(parameter, parameters));
  }

  //constructor
  public ConstraintDto(final ConstraintType type, ImmutableList<String> parameters) { //NOSONAR: This implementations
                                                                                      //checks the given arguments.

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
