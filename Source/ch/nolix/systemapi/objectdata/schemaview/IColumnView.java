package ch.nolix.systemapi.objectdata.schemaview;

import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

public interface IColumnView<T> extends IDatabaseObject, IIdHolder, INameHolder {

  IContentModelView<T> getContentModel();

  T getStoredParentTable();

  boolean internalContainsGivenValueInPersistedData(String value);

  boolean internalContainsGivenValueInPersistedDataIgnoringGivenEntities(
    String value,
    IContainer<String> entitiesToIgnoreIds);
}
