//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.common.container.LinkedList;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.SelectionMenu;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.Reference;

//class
public final class ReferencePropertySession extends HeaderedSession {
	
	//attributes
	private final Reference<Entity> reference;
	
	//constructor
	public ReferencePropertySession(
		final Reference<Entity> referenceProperty
	) {
		super(
			referenceProperty.getRefEntitySetOfReferencedEntities().getName()
		);
		
		this.reference = referenceProperty;
	}

	//method
	@Override
	protected LinkedList<Button> createLinkButtons() {
		return new LinkedList<>();
	}

	//method
	@Override
	protected Widget<?, ?> createSubSubContentWidget() {
		return
		new VerticalStack(
			createReferencesSelectionMenu(),
			new HorizontalStack(
				new Button("Select")
				.setLeftMouseButtonPressAction(() -> select()),
				new Button("Cancel")
				.setLeftMouseButtonPressAction(() -> cancel())
			)
		);
	}
	
	//method
	private void cancel() {
		pop(reference.getRefEntity().getId());
	}

	//method
	private SelectionMenu createReferencesSelectionMenu() {
		
		final var referencesSelectionMenu = new SelectionMenu().setId("ReferencesSelectionMenu");
		
		for (final var e : reference.getRefEntitySetOfReferencedEntities().getRefEntities()) {
			referencesSelectionMenu.addItem(String.valueOf(e.getId()), e.getIdAsString());
		}
		
		if (reference.referencesEntity()) {
			referencesSelectionMenu.selectItemById(String.valueOf(reference.getRefEntity().getId()));
		}
		
		return referencesSelectionMenu;
	}
	
	//method
	private void select() {
		
		final SelectionMenu referencesSelectionMenu = getRefGUI().getRefWidgetById("ReferencesSelectionMenu");
		
		reference.set(
			reference
			.getRefEntitySetOfReferencedEntities()
			.getRefEntityById(Long.valueOf(referencesSelectionMenu.getSelectedItemId()))
		);
		
		pop(reference.getRefEntity().getId());
	}
}
