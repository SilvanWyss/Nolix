package ch.nolix.system.objectschema.schemadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

public final class ColumnDto implements IColumnDto {

  private final String id;

  private final String name;

  private final IParameterizedFieldTypeDto parameterizedFieldTypeDto;

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

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public IParameterizedFieldTypeDto getParameterizedFieldType() {
    return parameterizedFieldTypeDto;
  }
}
