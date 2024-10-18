package ch.nolix.systemapi.guiapi.presenceapi;

public interface PresenceRequestable {

  Presence getPresence();

  boolean isCollapsed();

  boolean isInvisible();

  boolean isVisible();
}
