/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.senderandreplierserver;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.baseapi.net.netattribute.SecurityModeHolder;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * @author Silvan Wyss
 */
public interface Server extends Clearable, GroupCloseable, SecurityModeHolder {
  /**
   * Adds the given defaultSlot as default {@link Slot} to the current
   * {@link Server}.
   * 
   * @param defaultSlot
   * @throws RuntimeException if the current {@link Server} contains already a
   *                          default {@link Slot}
   */
  void addDefaultSlot(Slot defaultSlot);

  /**
   * Adds the given slot to the current {@link Server}.
   * 
   * @param slot
   * @throws RuntimeException if the current {@link Server} contains already a
   *                          {@link Slot} with the name of the given slot
   */
  void addSlot(Slot slot);

  /**
   * @return true if the current {@link Server} contains a default {@link Slot},
   *         false otherwise
   */
  boolean containsDefaultSlot();

  /**
   * @param name
   * @return true if the current {@link Server} contains a {@link Slot} with the
   *         given name, false otherwise
   */
  boolean containsSlotWithName(String name);

  /**
   * Removes the {@link Slot} with the given name from the current {@link Server}.
   * 
   * @param name
   * @throws RuntimeException if the current {@link Server} does not contain a
   *                          {@link Slot} with the given name
   */
  void removeSlotByName(String name);
}
