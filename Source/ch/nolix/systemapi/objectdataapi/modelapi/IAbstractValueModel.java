package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IAbstractValueModel<V> extends IContentModel {

  Class<V> getValueType();
}
