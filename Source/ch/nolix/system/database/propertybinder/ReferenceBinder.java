//package declaration
package ch.nolix.system.database.propertybinder;

//own imports
import ch.nolix.common.functionapi.IAction;
import ch.nolix.element.widget.DropdownMenu;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.Reference;

//class
public final class ReferenceBinder extends PropertyBinder<Reference<Entity>, DropdownMenu> {
	
	//method
	@Override
	protected DropdownMenu createWidget() {
		return new DropdownMenu();
	}
	
	//method
	@Override
	protected void addSelectionOptionsToWidgetForProperty(
		final DropdownMenu dropdownMenu,
		final Reference<Entity> reference
	) {
		for (final var e : reference.getRefEntitySetOfReferencedEntities().getRefEntities()) {
			dropdownMenu.addItem(e.getIdAsString(), e.getShortDescription());
		}
	}
	
	//method
	@Override
	protected void setNoteUpdateActionToWidget(final DropdownMenu dropdownMenu, final IAction noteUpdateValueAction) {
		dropdownMenu.setSelectAction(noteUpdateValueAction);
	}
	
	//method
	@Override
	protected void updatePropertyFromWidget(final Reference<Entity> reference, final DropdownMenu dropdownMenu) {
		
		final var selectedEntityId = dropdownMenu.getRefSelectedItem().getId();
		final var selectedEntity = reference.getRefEntitySetOfReferencedEntities().getRefEntityById(selectedEntityId);
		
		reference.set(selectedEntity);
	}
	
	//method
	@Override
	protected void updateWidgetFromProperty(final DropdownMenu dropdownMenu, final Reference<Entity> reference) {
		if (reference.referencesEntity()) {
			dropdownMenu.selectItemById(reference.getReferencedEntityIdAsString());
		}
	}
}
