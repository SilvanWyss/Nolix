/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForPasswordCaptor}
 */
public interface IForPasswordCaptor<S> extends ArgumentCaptor<S> {
  S forPassword(String password);

  String getPassword();
}
