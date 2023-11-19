//package declaration
package ch.nolix.systemapi.objectschemaapi.schemahelperapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;

//interface
public interface IParameterizedPropertyTypeHelper extends IDatabaseObjectHelper {

  //method declaration
  boolean isABaseBackReferenceType(IParameterizedPropertyType parameterizedPropertyType);

  //method declaration
  boolean isABaseReferenceType(IParameterizedPropertyType parameterizedPropertyType);

  //method declaration
  boolean isABaseValueType(IParameterizedPropertyType parameterizedPropertyType);
}
