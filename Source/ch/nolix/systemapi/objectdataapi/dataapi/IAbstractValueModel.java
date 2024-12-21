package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IAbstractValueModel<V> extends IContentModel {

  Class<V> getValueType();
}
