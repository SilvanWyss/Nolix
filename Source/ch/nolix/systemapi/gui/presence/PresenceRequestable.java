package ch.nolix.systemapi.gui.presence;

public interface PresenceRequestable {

  Presence getPresence();

  boolean isCollapsed();

  boolean isInvisible();

  boolean isVisible();
}
