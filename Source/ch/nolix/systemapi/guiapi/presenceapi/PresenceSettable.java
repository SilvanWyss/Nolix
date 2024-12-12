package ch.nolix.systemapi.guiapi.presenceapi;

public interface PresenceSettable<S extends PresenceSettable<S>> extends PresenceRequestable {

  S setCollapsed();

  S setInvisible();

  S setVisibility(boolean visible);

  S setVisible();
}
