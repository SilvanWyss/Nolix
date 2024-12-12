package ch.nolix.systemapi.guiapi.visibilityapi;

public interface VisibilitySettable<S extends VisibilitySettable<S>> extends VisibilityRequestable {

  S setInvisible();

  S setVisible();
}
