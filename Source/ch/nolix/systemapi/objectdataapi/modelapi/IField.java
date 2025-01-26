package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.componentapi.datamodelcomponentapi.IEntityComponent;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.MandatorynessRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IColumnView;

public interface IField
extends EmptinessRequestable, IDatabaseObject, IEntityComponent<IEntity>, INameHolder, MandatorynessRequestable {

  /**
   * @return the {@link IAbstractBackReference}s that reference back the current
   *         {@link IField}.
   */
  IContainer<IAbstractBackReference<IEntity>> getStoredAbstractBackReferencesThatReferencesBackThis();

  /**
   * @return the {@link IAbstractReference}s the current {@link IField} references
   *         back.
   */
  IContainer<IAbstractReference<IEntity>> getStoredAbstractReferencesThatAreBackReferencedFromThis();

  IColumnView getStoredParentColumn();

  ContentType getType();

  void internalSetOrClearContent(Object content);

  boolean knowsParentColumn();

  boolean referencesBackEntity(IEntity entity);

  boolean referencesBackField(IField field);

  boolean referencesEntity(IEntity entity);

  boolean referencesUninsertedEntity();

  void setUpdateAction(Runnable updateAction);
}
