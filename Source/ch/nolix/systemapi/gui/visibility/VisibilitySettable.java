package ch.nolix.systemapi.gui.visibility;

/**
 * @author Silvan Wyss
 */
public interface VisibilitySettable<S extends VisibilitySettable<S>> extends VisibilityRequestable {
  S setInvisible();

  S setVisible();
}
