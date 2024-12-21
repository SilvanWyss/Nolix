package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalSaveStampHolder;
import ch.nolix.coreapi.componentapi.datamodelcomponentapi.ITableComponent;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

public interface IEntity
extends Deletable, IDatabaseObject, IIdHolder, IOptionalSaveStampHolder, ITableComponent<ITable<? extends IEntity>> {

  String getParentTableName();

  String getShortDescription();

  IDatabase getStoredParentDatabase();

  IContainer<? extends IField> internalGetStoredFields();

  void internalSetId(String id);

  void internalSetLoaded();

  void internalSetParentTable(ITable<? extends IEntity> parentTable);

  boolean isReferencedInPersistedData();

  boolean isReferencedInPersistedDataIgnoringGivenEntities(IContainer<String> entitiesToIgnoreIds);
}
