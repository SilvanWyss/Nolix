//package declaration
package ch.nolix.system.objectschema.schematool;

//own imports
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IParameterizedFieldTypeTool;

//class
public final class ParameterizedFieldTypeTool extends DatabaseObjectTool
implements IParameterizedFieldTypeTool {

  //method
  @Override
  public boolean isABaseBackReferenceType(final IParameterizedFieldType parameterizedFieldType) {
    return (parameterizedFieldType.getFieldType().getBaseType() == BaseContentType.BASE_BACK_REFERENCE);
  }

  //method
  @Override
  public boolean isABaseReferenceType(IParameterizedFieldType parameterizedFieldType) {
    return (parameterizedFieldType.getFieldType().getBaseType() == BaseContentType.BASE_REFERENCE);
  }

  //method
  @Override
  public boolean isABaseValueType(IParameterizedFieldType parameterizedFieldType) {
    return (parameterizedFieldType.getFieldType().getBaseType() == BaseContentType.BASE_VALUE);
  }
}
