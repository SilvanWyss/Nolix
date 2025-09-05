package ch.nolix.systemapi.objectdata.schemaview;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public interface IContentModelView<T> {
  IBaseBackReferenceModelView<?, T> asAbstractBackReferenceModel();

  IBaseReferenceModelView<T> asAbstractReferenceModel();

  IBaseValueModelView<?, T> asAbstractValueModel();

  boolean referencesTable(T table);

  FieldType getFieldType();
}
