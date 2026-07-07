/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndLoginNameCaptor}
 */
public interface IAndLoginNameCaptor<S> extends ArgumentCaptor<S> {
  S andLoginName(String loginName);

  String getLoginName();
}
