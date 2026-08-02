/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.function;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link ToByteFunction} maps to bytes
 */
public interface ToByteFunction<E> {
  byte mapElementToByte(E element);
}
