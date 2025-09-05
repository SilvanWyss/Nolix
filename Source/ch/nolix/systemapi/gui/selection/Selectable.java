package ch.nolix.systemapi.gui.selection;

public interface Selectable extends SelectionRequestable {
  void select();

  void unselect();
}
