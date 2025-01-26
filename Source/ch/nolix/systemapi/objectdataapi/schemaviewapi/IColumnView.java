package ch.nolix.systemapi.objectdataapi.schemaviewapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public interface IColumnView extends IDatabaseObject, IIdHolder, INameHolder {

  IContentModelView getContentModel();

  ITable<IEntity> getStoredParentTable();

  boolean internalContainsGivenValueInPersistedData(String value);

  boolean internalContainsGivenValueInPersistedDataIgnoringGivenEntities(
    String value,
    IContainer<String> entitiesToIgnoreIds);
}
