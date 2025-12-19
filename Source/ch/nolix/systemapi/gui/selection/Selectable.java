package ch.nolix.systemapi.gui.selection;

/**
 * @author Silvan Wyss
 */
public interface Selectable extends SelectionRequestable {
  void select();

  void unselect();
}
