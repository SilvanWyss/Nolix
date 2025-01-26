package ch.nolix.systemapi.objectdataapi.schemaviewapi;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IContentModelView<T> {

  IAbstractBackReferenceModelView<?, T> asAbstractBackReferenceModel();

  IAbstractReferenceModelView<T> asAbstractReferenceModel();

  IAbstractValueModelView<?, T> asAbstractValueModel();

  boolean referencesTable(T table);

  ContentType getContentType();
}
