//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.MandatorynessRequestable;
import ch.nolix.coreapi.generalstateapi.staterequestapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

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
