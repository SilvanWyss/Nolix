/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.net.endpoint2;

import ch.nolix.coreapi.net.securityproperty.SecurityMode;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.state.statemutation.Clearable;

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
