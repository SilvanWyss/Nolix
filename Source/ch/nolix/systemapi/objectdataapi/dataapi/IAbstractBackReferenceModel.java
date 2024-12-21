package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IAbstractBackReferenceModel<C extends IColumn> extends IContentModel {

  C getBackReferencedColumn();
}
