/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.atomiccontrol.itemmenu;

/**
 * @author Silvan Wyss
 */
public interface IItemMenuValidator {
  /**
   * @param itemMenu
   * @param item
   * @throws RuntimeException if the given itemMenu cannot add the given item.
   */
  void assertCanAddItem(IItemMenu<?, ?> itemMenu, IItemMenuItem<?> item);
}
