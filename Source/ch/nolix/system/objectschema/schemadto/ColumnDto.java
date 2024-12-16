package ch.nolix.system.objectschema.schemadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

public final class ColumnDto implements IColumnDto {

  private final String id;

  private final String name;

  private final IContentModelDto contentModelDto;

  //For a better performance, this implementation does not use all comfortable methods.
  public ColumnDto(
    final String id,
    final String name,
    final IContentModelDto contentModelDto) {

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.ID);
    }

    if (name == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.HEADER);
    }

    if (contentModelDto == null) {
      throw ArgumentIsNullException.forArgumentType(IContentModelDto.class);
    }

    this.id = id;
    this.name = name;
    this.contentModelDto = contentModelDto;
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
  public IContentModelDto getParameterizedFieldType() {
    return contentModelDto;
  }
}
