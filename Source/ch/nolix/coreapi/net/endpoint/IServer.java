package ch.nolix.coreapi.net.endpoint;

import ch.nolix.coreapi.net.securityproperty.SecurityMode;
import ch.nolix.coreapi.resourcecontrol.resourceclosing.GroupCloseable;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public interface IServer extends Clearable, GroupCloseable {

  void addDefaultSlot(ISlot defaultSlot);

  void addSlot(ISlot slot);

  boolean containsDefaultSlot();

  boolean containsSlotWithName(String name);

  SecurityMode getSecurityMode();

  void removeSlotByName(String name);
}
