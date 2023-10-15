//package declaration
package ch.nolix.systemapi.guiapi.presenceapi;

//interface
public interface PresenceRequestable {

  // method declaration
  Presence getPresence();

  // method declaration
  boolean isCollapsed();

  // method declaration
  boolean isInvisible();

  // method declaration
  boolean isVisible();
}
