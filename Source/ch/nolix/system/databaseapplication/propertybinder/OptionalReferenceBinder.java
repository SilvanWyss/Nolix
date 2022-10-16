//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;

//class
public final class OptionalReferenceBinder
extends PropertyBinder<IOptionalReference<?, IEntity<?>>, IDropdownMenu<?, ?>> {
	
	//method
	@Override
	protected void addSelectionOptionsToWidgetForProperty(
		final  IDropdownMenu<?, ?> dropdownMenu,
		final IOptionalReference<?, IEntity<?>> optionalReference
	) {
		
		dropdownMenu.addBlankItem();
		
		for (final var e : optionalReference.getReferencedTable().getRefAllEntities()) {
			dropdownMenu.addItemWithIdAndText(e.getId(), e.getShortDescription());
		}
	}
	
	//method
	@Override
	protected IDropdownMenu<?, ?> createWidget() {
		return new DropdownMenu();
	}
	
	//method
	@Override
	protected void setNoteUpdateActionToWidget(
		final  IDropdownMenu<?, ?> dropdownMenu,
		final IAction noteUpdateAction
	) {
		dropdownMenu.setSelectAction(noteUpdateAction);
	}
	
	//method
	@Override
	protected void updatePropertyFromWidget(
		final IOptionalReference<?, IEntity<?>> optionalReference,
		final  IDropdownMenu<?, ?> dropdownMenu
	) {
		if (dropdownMenu.blankItemIsSelected()) {
			optionalReference.clear();
		} else {
			
			final var selectedEntityId = dropdownMenu.getRefSelectedItem().getId();
			
			optionalReference.setEntityWithId(selectedEntityId);
		}
	}
	
	//method
	@Override
	protected void updateWidgetFromProperty(
		final  IDropdownMenu<?, ?> dropdownMenu,
		final IOptionalReference<?, IEntity<?>> optionalReference
	) {
		if (optionalReference.isEmpty()) {
			dropdownMenu.selectBlankItem();
		} else {
			dropdownMenu.selectItemById(optionalReference.getEntityId());
		}
	}
}
