//package declaration
package ch.nolix.system.database.propertybinder;

//own imports
import ch.nolix.common.functionapi.IAction;
import ch.nolix.element.widget.DropdownMenu;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.OptionalReference;

//class
public final class OptionalReferenceBinder extends PropertyBinder<OptionalReference<Entity>, DropdownMenu>{
	
	//method
	@Override
	protected void addSelectionOptionsToWidgetForProperty(
		final DropdownMenu dropdownMenu,
		final OptionalReference<Entity> optionalReference
	) {
		
		dropdownMenu.addEmtyItem();
		
		for (final var e : optionalReference.getRefEntitySetOfReferencedEntities().getRefEntities()) {
			dropdownMenu.addItem(e.getIdAsString(), e.getShortDescription());
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
		final OptionalReference<Entity> optionalReference,
		final DropdownMenu dropdownMenu
	) {
		if (dropdownMenu.emptyItemIsSelected()) {
			optionalReference.clear();
		} else {
			
			final var selectedEntityId = dropdownMenu.getRefSelectedItem().getId();
			
			final var selectedEntity =
			optionalReference.getRefEntitySetOfReferencedEntities().getRefEntityById(selectedEntityId);
			
			optionalReference.set(selectedEntity);	
		}
	}
	
	//method
	@Override
	protected void updateWidgetFromProperty(
		final DropdownMenu dropdownMenu,
		final OptionalReference<Entity> optionalReference
	) {
		if (optionalReference.isEmpty()) {
			dropdownMenu.selectEmptyItem();
		} else {
			dropdownMenu.selectItemById(optionalReference.getReferencedEntityIdAsString());
		}
	}
}
