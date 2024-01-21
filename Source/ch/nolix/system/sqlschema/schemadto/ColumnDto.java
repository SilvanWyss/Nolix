//package declaration
package ch.nolix.system.sqlschema.schemadto;

//own imports
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
  private static final IContainer<IConstraintDto> EMPTY_CONSTRAINT_LIST = new LinkedList<>();

  //attribute
  private final String name;

  //attribute
  private final IDataTypeDto dataType;

  //multi-attribute
  private final IContainer<IConstraintDto> constraints;

  //constructor
  public ColumnDto(final String name, final IDataTypeDto dataType) {
    this(name, dataType, EMPTY_CONSTRAINT_LIST);
  }

  //constructor
  public ColumnDto(final String name, final IDataTypeDto dataType, final IContainer<IConstraintDto> constraints) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotNull();
    GlobalValidator.assertThat(dataType).thatIsNamed(LowerCaseVariableCatalogue.DATA_TYPE).isNotNull();

    this.name = name;
    this.dataType = dataType;

    this.constraints = LinkedList.fromIterable(constraints);
  }

  //constructor
  public ColumnDto(
    final String name,
    final IDataTypeDto dataType,
    final IConstraintDto constraint,
    final IConstraintDto... constraints) {
    this(name, dataType, ReadContainer.forElement(constraint, constraints));
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
