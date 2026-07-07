/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndLoginPasswordCaptor}
 */
public interface IAndLoginPasswordCaptor<S> extends ArgumentCaptor<S> {
  S andLoginPassword(String loginPassword);

  String getLoginPassword();
}
