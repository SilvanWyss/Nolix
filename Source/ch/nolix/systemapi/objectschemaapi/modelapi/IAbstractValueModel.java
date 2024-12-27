package ch.nolix.systemapi.objectschemaapi.modelapi;

public interface IAbstractValueModel<V> extends IContentModel {

  Class<V> getValueClass();
}
