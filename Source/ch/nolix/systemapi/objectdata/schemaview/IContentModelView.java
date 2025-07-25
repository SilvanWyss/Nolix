package ch.nolix.systemapi.objectdata.schemaview;

import ch.nolix.systemapi.midschema.fieldproperty.ContentType;

public interface IContentModelView<T> {

  IAbstractBackReferenceModelView<?, T> asAbstractBackReferenceModel();

  IAbstractReferenceModelView<T> asAbstractReferenceModel();

  IAbstractValueModelView<?, T> asAbstractValueModel();

  boolean referencesTable(T table);

  ContentType getContentType();
}
