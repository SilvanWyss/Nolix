package ch.nolix.coreapi.netapi.endpoint2api;

import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public interface IServer extends Clearable, GroupCloseable {

  void addDefaultSlot(ISlot defaultSlot);

  void addSlot(ISlot slot);

  boolean containsDefaultSlot();

  boolean containsSlotWithName(String name);

  SecurityMode getSecurityMode();

  void removeSlotByName(String name);
}
