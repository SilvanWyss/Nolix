//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.gui.widget.DropdownMenu;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;

//class
public final class OptionalReferenceBinder extends PropertyBinder<IOptionalReference<?, IEntity<?>>, DropdownMenu>{
	
	//method
	@Override
	protected void addSelectionOptionsToWidgetForProperty(
		final DropdownMenu dropdownMenu,
		final IOptionalReference<?, IEntity<?>> optionalReference
	) {
		
		dropdownMenu.addEmtyItem();
		
		for (final var e : optionalReference.getReferencedTable().getRefAllEntities()) {
			dropdownMenu.addItem(e.getId(), e.getShortDescription());
		}
	}
	
	//method
	@Override
	protected DropdownMenu createWidget() {
		return new DropdownMenu();
	}
	
	//method
	@Override
	protected void setNoteUpdateActionToWidget(final DropdownMenu dropdownMenu, final IAction noteUpdateAction) {
		dropdownMenu.setSelectAction(noteUpdateAction);
	}
	
	//method
	@Override
	protected void updatePropertyFromWidget(
		final IOptionalReference<?, IEntity<?>> optionalReference,
		final DropdownMenu dropdownMenu
	) {
		if (dropdownMenu.emptyItemIsSelected()) {
			optionalReference.clear();
		} else {
			
			final var selectedEntityId = dropdownMenu.getRefSelectedItem().getId();
			
			optionalReference.setEntityWithId(selectedEntityId);
		}
	}
	
	//method
	@Override
	protected void updateWidgetFromProperty(
		final DropdownMenu dropdownMenu,
		final IOptionalReference<?, IEntity<?>> optionalReference
	) {
		if (optionalReference.isEmpty()) {
			dropdownMenu.selectEmptyItem();
		} else {
			dropdownMenu.selectItemById(optionalReference.getEntityId());
		}
	}
}
