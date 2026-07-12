/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IWithNameCaptor}.
 */
public interface IWithNameCaptor<S> extends ArgumentCaptor<S> {
  String getName();

  S withName(final String Name);
}
