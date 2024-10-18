package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalSaveStampHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

public interface IEntity extends Deletable, IDatabaseObject, IIdHolder, IOptionalSaveStampHolder {

  boolean belongsToTable();

  String getParentTableName();

  String getShortDescription();

  IDatabase getStoredParentDatabase();

  ITable<? extends IEntity> getStoredParentTable();

  IContainer<? extends IField> internalGetStoredFields();

  boolean isReferencedInPersistedData();

  boolean isReferencedInPersistedDataIgnoringGivenEntities(IContainer<String> entitiesToIgnoreIds);
}
