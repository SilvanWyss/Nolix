/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.level1server;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * @author Silvan Wyss
 */
public interface IServer extends Clearable, GroupCloseable {
  void addDefaultSlot(ISlot defaultSlot);

  void addSlot(ISlot slot);

  boolean containsDefaultSlot();

  boolean containsSlotWithName(String name);

  SecurityMode getSecurityMode();

  void removeSlotByName(String name);
}
