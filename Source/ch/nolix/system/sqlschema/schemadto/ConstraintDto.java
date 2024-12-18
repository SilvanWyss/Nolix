package ch.nolix.system.sqlschema.schemadto;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqlschemaapi.columnconstaintproperty.ConstraintType;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IConstraintDto;

public record ConstraintDto(ConstraintType type, ImmutableList<String> parameters) implements IConstraintDto {

  public ConstraintDto(final ConstraintType type, final String parameter, final String... parameters) {
    this(type, ImmutableList.withElement(parameter, parameters));
  }

  public ConstraintDto(final ConstraintType type, ImmutableList<String> parameters) { //NOSONAR: This implementations
                                                                                      //checks the given arguments.

    GlobalValidator.assertThat(type).thatIsNamed(ConstraintType.class).isNotNull();

    this.type = type;
    this.parameters = parameters;
  }

  @Override
  public IContainer<String> getParameters() {
    return parameters;
  }

  @Override
  public ConstraintType getType() {
    return type;
  }
}
