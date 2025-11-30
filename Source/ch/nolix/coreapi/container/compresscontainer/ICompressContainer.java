package ch.nolix.coreapi.container.compresscontainer;

import ch.nolix.coreapi.container.base.IContainer;

public interface ICompressContainer<E> extends IContainer<E> {
  boolean containsWithId(String id);

  String getIdOf(E element);

  E getStoredById(String id);

  String registerAndGetId(E element);

  void registerAtId(String id, E element);

  String registerIfNotRegisteredAndGetId(E element);
}
