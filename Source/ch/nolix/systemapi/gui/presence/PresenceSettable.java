package ch.nolix.systemapi.gui.presence;

/**
 * @author Silvan Wyss
 */
public interface PresenceSettable<S extends PresenceSettable<S>> extends PresenceRequestable {
  S setCollapsed();

  S setInvisible();

  S setVisibility(boolean visible);

  S setVisible();
}
