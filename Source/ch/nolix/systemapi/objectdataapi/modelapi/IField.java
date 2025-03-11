package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.componentapi.datamodelcomponentapi.IEntityComponent;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.MandatorynessRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;
import ch.nolix.systemapi.objectschemaapi.fieldproperty.ContentType;

public interface IField
extends EmptinessRequestable, IDatabaseObject, IEntityComponent<IEntity>, INameHolder, MandatorynessRequestable {

  IContainer<IAbstractBackReference<IEntity>> getStoredAbstractBackReferencesThatReferencesBackThis();

  IContainer<IAbstractReference<IEntity>> getStoredAbstractReferencesThatAreBackReferencedFromThis();

  IColumnView<ITable<IEntity>> getStoredParentColumn();

  ContentType getType();

  IContainer<IField> internalGetStoredSubFields();

  void internalSetOptionalContent(Object content);

  boolean knowsParentColumn();

  boolean referencesBackEntity(IEntity entity);

  boolean referencesBackEntityWithId(String id);

  boolean referencesBackField(IField field);

  boolean referencesEntity(IEntity entity);

  boolean referencesUninsertedEntity();

  void setUpdateAction(Runnable updateAction);
}
