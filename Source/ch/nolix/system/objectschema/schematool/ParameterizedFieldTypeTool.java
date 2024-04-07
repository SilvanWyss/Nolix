//package declaration
package ch.nolix.system.objectschema.schematool;

//own imports
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.fieldapi.mainapi.BaseFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IParameterizedFieldTypeTool;

//class
public final class ParameterizedFieldTypeTool extends DatabaseObjectTool
implements IParameterizedFieldTypeTool {

  //method
  @Override
  public boolean isABaseBackReferenceType(final IParameterizedFieldType parameterizedFieldType) {
    return (parameterizedFieldType.getFieldType().getBaseType() == BaseFieldType.BASE_BACK_REFERENCE);
  }

  //method
  @Override
  public boolean isABaseReferenceType(IParameterizedFieldType parameterizedFieldType) {
    return (parameterizedFieldType.getFieldType().getBaseType() == BaseFieldType.BASE_REFERENCE);
  }

  //method
  @Override
  public boolean isABaseValueType(IParameterizedFieldType parameterizedFieldType) {
    return (parameterizedFieldType.getFieldType().getBaseType() == BaseFieldType.BASE_VALUE);
  }
}
