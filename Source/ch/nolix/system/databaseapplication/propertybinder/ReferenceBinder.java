//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.core.functionapi.IAction;
import ch.nolix.system.gui.widget.DropdownMenu;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;

//class
public final class ReferenceBinder extends PropertyBinder<IReference<?, IEntity<?>>, DropdownMenu> {
	
	//method
	@Override
	protected DropdownMenu createWidget() {
		return new DropdownMenu();
	}
	
	//method
	@Override
	protected void addSelectionOptionsToWidgetForProperty(
		final DropdownMenu dropdownMenu,
		final IReference<?, IEntity<?>> reference
	) {
		for (final var e : reference.getReferencedTable().getRefAllEntities()) {
			dropdownMenu.addItem(e.getId(), e.getShortDescription());
		}
	}
	
	//method
	@Override
	protected void setNoteUpdateActionToWidget(final DropdownMenu dropdownMenu, final IAction noteUpdateValueAction) {
		dropdownMenu.setSelectAction(noteUpdateValueAction);
	}
	
	//method
	@Override
	protected void updatePropertyFromWidget(final IReference<?, IEntity<?>> reference, final DropdownMenu dropdownMenu) {
		
		final var selectedEntityId = dropdownMenu.getRefSelectedItem().getId();
		
		reference.setEntityWithId(selectedEntityId);
	}
	
	//method
	@Override
	protected void updateWidgetFromProperty(final DropdownMenu dropdownMenu, final IReference<?, IEntity<?>> reference) {
		if (reference.containsAny()) {
			dropdownMenu.selectItemById(reference.getEntityId());
		}
	}
}
