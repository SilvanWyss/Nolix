package ch.nolix.coreapi.programstructureapi.cachingapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface ICachingContainer<E> extends IContainer<E> {

  boolean containsWithId(String id);

  String getIdOf(E element);

  E getStoredById(String id);

  String registerAndGetId(E element);

  void registerAtId(String id, E element);

  String registerIfNotRegisteredAndGetId(E element);
}
