//package declaration
package ch.nolix.coreapi.netapi.endpoint3api;

import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

//interface
public interface IServer extends Clearable, GroupCloseable {

  //method declaration
  void addDefaultSlot(ISlot defaultSlot);

  //method declaration
  void addSlot(ISlot slot);

  //method declaration
  boolean containsDefaultSlot();

  //method declaration
  boolean containsSlotWithName(String name);

  //method declaration
  SecurityMode getSecurityLevel();

  //method declaration
  void removeSlotByName(String name);
}
