//package declaration
package ch.nolix.system.sqlschema.schemadto;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IConstraintDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IDataTypeDto;

//class
public final class ColumnDto implements IColumnDto {

  //constant
  private static final IContainer<IConstraintDto> EMPTY_CONSTRAINTS_LIST = ImmutableList.createEmpty();

  //attribute
  private final String name;

  //attribute
  private final IDataTypeDto dataType;

  //multi-attribute
  private final IContainer<IConstraintDto> constraints;

  //constructor
  private ColumnDto(final String name, final IDataTypeDto dataType, final IContainer<IConstraintDto> constraints) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotNull();
    GlobalValidator.assertThat(dataType).thatIsNamed(LowerCaseVariableCatalogue.DATA_TYPE).isNotNull();

    this.name = name;
    this.dataType = dataType;

    this.constraints = LinkedList.fromIterable(constraints);
  }

  //static method
  public static ColumnDto withNameAndDataType(final String name, final IDataTypeDto dataType) {
    return withNameAndDataTypeAndConstraints(name, dataType, EMPTY_CONSTRAINTS_LIST);
  }

  //static method
  public static ColumnDto withNameAndDataTypeAndConstraint(
    final String name,
    final IDataTypeDto dataType,
    final IConstraintDto constraint,
    final IConstraintDto... constraints) {
    return withNameAndDataTypeAndConstraints(name, dataType, ReadContainer.forElement(constraint, constraints));
  }

  //static method
  public static ColumnDto withNameAndDataTypeAndConstraints(
    final String name,
    final IDataTypeDto dataType,
    final IContainer<IConstraintDto> constraints) {
    return new ColumnDto(name, dataType, constraints);
  }

  //method
  @Override
  public IContainer<IConstraintDto> getConstraints() {
    return constraints;
  }

  //method
  @Override
  public IDataTypeDto getDataType() {
    return dataType;
  }

  //method
  @Override
  public String getName() {
    return name;
  }
}
