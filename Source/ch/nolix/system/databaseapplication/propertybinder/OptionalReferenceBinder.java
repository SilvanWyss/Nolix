//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;

//class
public final class OptionalReferenceBinder
extends PropertyBinder<IOptionalReference<IEntity>, IDropdownMenu> {

  //method
  @Override
  protected void addSelectionOptionsToControlForProperty(
    final IDropdownMenu dropdownMenu,
    final IOptionalReference<IEntity> optionalReference) {

    dropdownMenu.addBlankItem();

    for (final var e : optionalReference.getReferencedTable().getStoredEntities()) {
      dropdownMenu.addItemWithIdAndText(e.getId(), e.getShortDescription());
    }
  }

  //method
  @Override
  protected IDropdownMenu createControl() {
    return new DropdownMenu();
  }

  //method
  @Override
  protected void setNoteUpdateActionToControl(
    final IDropdownMenu dropdownMenu,
    final Runnable noteUpdateAction) {
    dropdownMenu.setSelectAction(noteUpdateAction);
  }

  //method
  @Override
  protected void updatePropertyFromControl(
    final IOptionalReference<IEntity> optionalReference,
    final IDropdownMenu dropdownMenu) {
    if (dropdownMenu.blankItemIsSelected()) {
      optionalReference.clear();
    } else {

      final var selectedEntityId = dropdownMenu.getStoredSelectedItem().getId();

      optionalReference.setEntityById(selectedEntityId);
    }
  }

  //method
  @Override
  protected void updateControlFromProperty(
    final IDropdownMenu dropdownMenu,
    final IOptionalReference<IEntity> optionalReference) {
    if (optionalReference.isEmpty()) {
      dropdownMenu.selectBlankItem();
    } else {
      dropdownMenu.selectItemById(optionalReference.getReferencedEntityId());
    }
  }
}
