//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.MandatorynessRequestable;
import ch.nolix.coreapi.methodapi.requestapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;

//interface
public interface IProperty extends EmptinessRequestable, IDatabaseObject, MandatorynessRequestable, INameHolder {

  //method declaration
  boolean belongsToEntity();

  //method declaration
  IContainer<IProperty> getStoredBackReferencingProperties();

  //method
  IColumn getStoredParentColumn();

  //method declaration
  IEntity getStoredParentEntity();

  //method declaration
  IContainer<IProperty> getStoredReferencingProperties();

  //method declaration
  PropertyType getType();

  //method declaration
  boolean knowsParentColumn();

  //method declaration
  boolean referencesBackEntity(IEntity entity);

  //method declaration
  boolean referencesBackProperty(IProperty property);

  //method declaration
  boolean referencesEntity(IEntity entity);

  //method declaration
  boolean referencesUninsertedEntity();

  //method declaration
  void setUpdateAction(Runnable updateAction);

  //method declaration
  IContentFieldDto technicalToContentField();
}
