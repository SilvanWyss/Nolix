/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndNameCaptor}
 */
public interface IAndNameCaptor<S> extends ArgumentCaptor<S> {
  String getName();

  S andName(final String name);
}
