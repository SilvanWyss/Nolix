package ch.nolix.systemapi.gui.visibility;

public interface VisibilitySettable<S extends VisibilitySettable<S>> extends VisibilityRequestable {

  S setInvisible();

  S setVisible();
}
