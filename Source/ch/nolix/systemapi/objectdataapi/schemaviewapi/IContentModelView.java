package ch.nolix.systemapi.objectdataapi.schemaviewapi;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public interface IContentModelView {

  IAbstractBackReferenceModelView<?> asAbstractBackReferenceModel();

  IAbstractReferenceModelView<?> asAbstractReferenceModel();

  IAbstractValueModelView<?> asAbstractValueModel();

  boolean referencesTable(ITable<?> table);

  ContentType getContentType();
}
