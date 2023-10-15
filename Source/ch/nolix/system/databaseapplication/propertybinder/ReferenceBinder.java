//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;

//class
public final class ReferenceBinder extends PropertyBinder<IReference<IEntity>, IDropdownMenu> {

  // method
  @Override
  protected IDropdownMenu createControl() {
    return new DropdownMenu();
  }

  // method
  @Override
  protected void addSelectionOptionsToControlForProperty(
      final IDropdownMenu dropdownMenu,
      final IReference<IEntity> reference) {
    for (final var e : reference.getReferencedTable().getStoredEntities()) {
      dropdownMenu.addItemWithIdAndText(e.getId(), e.getShortDescription());
    }
  }

  // method
  @Override
  protected void setNoteUpdateActionToControl(
      final IDropdownMenu dropdownMenu,
      final IAction noteUpdateValueAction) {
    dropdownMenu.setSelectAction(noteUpdateValueAction);
  }

  // method
  @Override
  protected void updatePropertyFromControl(
      final IReference<IEntity> reference,
      final IDropdownMenu dropdownMenu) {

    final var selectedEntityId = dropdownMenu.getStoredSelectedItem().getId();

    reference.setEntityById(selectedEntityId);
  }

  // method
  @Override
  protected void updateControlFromProperty(
      final IDropdownMenu dropdownMenu,
      final IReference<IEntity> reference) {
    if (reference.containsAny()) {
      dropdownMenu.selectItemById(reference.getReferencedEntityId());
    }
  }
}
