package ch.nolix.systemapi.guiapi.presenceapi;

public interface PresenceSettable<PS extends PresenceSettable<PS>> extends PresenceRequestable {

  PS setCollapsed();

  PS setInvisible();

  PS setVisibility(boolean visible);

  PS setVisible();
}
