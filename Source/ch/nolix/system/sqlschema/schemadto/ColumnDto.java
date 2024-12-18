package ch.nolix.system.sqlschema.schemadto;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadto.DataTypeDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IConstraintDto;

public final class ColumnDto implements IColumnDto {

  private static final IContainer<IConstraintDto> EMPTY_CONSTRAINTS_LIST = ImmutableList.createEmpty();

  private final String name;

  private final DataTypeDto dataType;

  private final IContainer<IConstraintDto> constraints;

  private ColumnDto(final String name, final DataTypeDto dataType, final IContainer<IConstraintDto> constraints) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotNull();
    GlobalValidator.assertThat(dataType).thatIsNamed(LowerCaseVariableCatalogue.DATA_TYPE).isNotNull();

    this.name = name;
    this.dataType = dataType;

    this.constraints = LinkedList.fromIterable(constraints);
  }

  public static ColumnDto withNameAndDataType(final String name, final DataTypeDto dataType) {
    return withNameAndDataTypeAndConstraints(name, dataType, EMPTY_CONSTRAINTS_LIST);
  }

  public static ColumnDto withNameAndDataTypeAndConstraint(
    final String name,
    final DataTypeDto dataType,
    final IConstraintDto constraint,
    final IConstraintDto... constraints) {

    final var allConstraints = ContainerView.forElementAndArray(constraint, constraints);

    return withNameAndDataTypeAndConstraints(name, dataType, allConstraints);
  }

  public static ColumnDto withNameAndDataTypeAndConstraints(
    final String name,
    final DataTypeDto dataType,
    final IContainer<IConstraintDto> constraints) {
    return new ColumnDto(name, dataType, constraints);
  }

  @Override
  public IContainer<IConstraintDto> getConstraints() {
    return constraints;
  }

  @Override
  public DataTypeDto getDataType() {
    return dataType;
  }

  @Override
  public String getName() {
    return name;
  }
}
