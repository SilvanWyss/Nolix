package ch.nolix.application.databaseapplication.frontend.fieldbinder;

import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalReference;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;

public final class OptionalReferenceBinder
extends FieldBinder<IOptionalReference<IEntity>, IDropdownMenu> {

  @Override
  protected void addSelectionOptionsToControlForField(
    final IDropdownMenu dropdownMenu,
    final IOptionalReference<IEntity> optionalReference) {

    dropdownMenu.addBlankItem();

    for (final var e : optionalReference.getStoredReferencedTable().getStoredEntities()) {
      dropdownMenu.addItemWithIdAndText(e.getId(), e.getShortDescription());
    }
  }

  @Override
  protected IDropdownMenu createControl() {
    return new DropdownMenu();
  }

  @Override
  protected void setNoteUpdateActionToControl(
    final IDropdownMenu dropdownMenu,
    final Runnable noteUpdateAction) {
    dropdownMenu.setSelectAction(noteUpdateAction);
  }

  @Override
  protected void updateFieldFromControl(
    final IOptionalReference<IEntity> optionalReference,
    final IDropdownMenu dropdownMenu) {
    if (dropdownMenu.blankItemIsSelected()) {
      optionalReference.clear();
    } else {

      final var selectedEntityId = dropdownMenu.getStoredSelectedItem().getId();

      optionalReference.setEntityById(selectedEntityId);
    }
  }

  @Override
  protected void updateControlFromField(
    final IDropdownMenu dropdownMenu,
    final IOptionalReference<IEntity> optionalReference) {
    if (optionalReference.isEmpty()) {
      dropdownMenu.selectBlankItem();
    } else {
      dropdownMenu.selectItemById(optionalReference.getReferencedEntityId());
    }
  }
}
