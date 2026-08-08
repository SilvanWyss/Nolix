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
  void addDefaultSlot(Slot defaultSlot);

  void addSlot(Slot slot);

  boolean containsDefaultSlot();

  boolean containsSlotWithName(String name);

  void removeSlotByName(String name);
}
