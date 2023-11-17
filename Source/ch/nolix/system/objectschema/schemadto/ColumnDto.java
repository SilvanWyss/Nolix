//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public final class ColumnDto implements IColumnDto {

  //attribute
  private final String id;

  //attribute
  private final String name;

  //attribute
  private final IParameterizedPropertyTypeDto parameterizedPropertyTypeDto;

  //constructor
  //For a better performance, this implementation does not use all comfortable
  //methods.
  public ColumnDto(
    final String id,
    final String name,
    final IParameterizedPropertyTypeDto parameterizedPropertyTypeDto) {

    if (id == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ID);
    }

    if (name == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.HEADER);
    }

    if (parameterizedPropertyTypeDto == null) {
      throw ArgumentIsNullException.forArgumentType(IParameterizedPropertyTypeDto.class);
    }

    this.id = id;
    this.name = name;
    this.parameterizedPropertyTypeDto = parameterizedPropertyTypeDto;
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
  public IParameterizedPropertyTypeDto getParameterizedPropertyType() {
    return parameterizedPropertyTypeDto;
  }
}
