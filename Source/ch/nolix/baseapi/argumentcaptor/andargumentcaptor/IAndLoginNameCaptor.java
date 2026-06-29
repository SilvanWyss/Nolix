/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndLoginNameCaptor}
 */
public interface IAndLoginNameCaptor<S> {
  S andLoginName(String loginName);

  String getLoginName();
}
