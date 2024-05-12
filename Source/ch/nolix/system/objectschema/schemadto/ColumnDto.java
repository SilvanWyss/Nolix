//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

//class
public final class ColumnDto implements IColumnDto {

  //attribute
  private final String id;

  //attribute
  private final String name;

  //attribute
  private final IParameterizedFieldTypeDto parameterizedFieldTypeDto;

  //constructor
  //For a better performance, this implementation does not use all comfortable methods.
  public ColumnDto(
    final String id,
    final String name,
    final IParameterizedFieldTypeDto parameterizedFieldTypeDto) {

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.ID);
    }

    if (name == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.HEADER);
    }

    if (parameterizedFieldTypeDto == null) {
      throw ArgumentIsNullException.forArgumentType(IParameterizedFieldTypeDto.class);
    }

    this.id = id;
    this.name = name;
    this.parameterizedFieldTypeDto = parameterizedFieldTypeDto;
  }

  //method
  @Override
  public String getId() {
    return id;
  }

  //method
  @Override
  public String getName() {
    return name;
  }

  //method
  @Override
  public IParameterizedFieldTypeDto getParameterizedFieldType() {
    return parameterizedFieldTypeDto;
  }
}
