package ch.nolix.systemapi.objectdataapi.schemaviewapi;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IContentModelView {

  IAbstractBackReferenceModelView<?> asAbstractBackReferenceModel();

  IAbstractReferenceModelView<?> asAbstractReferenceModel();

  IAbstractValueModelView<?> asAbstractValueModel();

  boolean referencesTable(Object table);

  ContentType getContentType();
}
