/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.messaging;

/**
 * @author Silvan Wyss
 * @param <C> the type of the content of a {@link IndexedPackage}.
 */
public final class IndexedPackage<C> extends AbstractIndexedPackage<C> {
  /**
   * Creates a new {@link IndexedPackage} with the given index and content.
   * 
   * @param index
   * @param content
   * @throws RuntimeException if the given content is null
   */
  private IndexedPackage(final int index, final C content) {
    super(index, content);
  }

  /**
   * @param index
   * @param content
   * @param <T>     the type of the given content
   * @return a new {@link IndexedPackage} with the given index and content
   * @throws RuntimeException if the given content is null
   */
  public static <T> AbstractIndexedPackage<T> withIndexAndContent(final int index, final T content) {
    return new IndexedPackage<>(index, content);
  }
}
