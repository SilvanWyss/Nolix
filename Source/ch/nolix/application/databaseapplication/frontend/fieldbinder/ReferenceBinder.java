package ch.nolix.application.databaseapplication.frontend.fieldbinder;

import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IReference;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;

public final class ReferenceBinder extends FieldBinder<IReference<IEntity>, IDropdownMenu> {

  @Override
  protected IDropdownMenu createControl() {
    return new DropdownMenu();
  }

  @Override
  protected void addSelectionOptionsToControlForField(
    final IDropdownMenu dropdownMenu,
    final IReference<IEntity> reference) {
    for (final var e : reference.getReferencedTable().getStoredEntities()) {
      dropdownMenu.addItemWithIdAndText(e.getId(), e.getShortDescription());
    }
  }

  @Override
  protected void setNoteUpdateActionToControl(
    final IDropdownMenu dropdownMenu,
    final Runnable noteUpdateValueAction) {
    dropdownMenu.setSelectAction(noteUpdateValueAction);
  }

  @Override
  protected void updateFieldFromControl(
    final IReference<IEntity> reference,
    final IDropdownMenu dropdownMenu) {

    final var selectedEntityId = dropdownMenu.getStoredSelectedItem().getId();

    reference.setEntityById(selectedEntityId);
  }

  @Override
  protected void updateControlFromField(
    final IDropdownMenu dropdownMenu,
    final IReference<IEntity> reference) {
    if (reference.containsAny()) {
      dropdownMenu.selectItemById(reference.getReferencedEntityId());
    }
  }
}
