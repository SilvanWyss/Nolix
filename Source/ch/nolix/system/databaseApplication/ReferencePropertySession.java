//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.common.containers.List;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.SelectionMenu;
import ch.nolix.element.widgets.VerticalStack;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.Reference;

//class
public final class ReferencePropertySession extends HeaderedSession {
	
	//attributes
	private final Reference<Entity> reference;
	
	//constructor
	public ReferencePropertySession(
		final Reference<Entity> referenceProperty
	) {
		super(
			referenceProperty.getReferencedEntitySet().getName()
		);
		
		this.reference = referenceProperty;
	}

	//method
	@Override
	protected List<Button> createLinkButtons() {
		return new List<>();
	}

	//method
	@Override
	protected Widget<?, ?> createSubSubContentWidget() {
		return
		new VerticalStack(
			createReferencesSelectionMenu(),
			new HorizontalStack(
				new Button("Select")
				.setLeftMouseButtonPressCommand(() -> select()),
				new Button("Cancel")
				.setLeftMouseButtonPressCommand(() -> cancel())
			)
		);
	}
	
	//method
	private void cancel() {
		getParentClient().popSession();
	}

	//method
	private SelectionMenu createReferencesSelectionMenu() {
		
		final var referencesSelectionMenu = new SelectionMenu().setName("ReferencesSelectionMenu");
		
		for (final var e : reference.getReferencedEntitySet().getRefEntities()) {
			referencesSelectionMenu.addItem(String.valueOf(e.getId()), /*e.getParentEntitySet().getName() +*/ " " + e.getId());
		}
		
		if (reference.referencesEntity()) {
			referencesSelectionMenu.selectItemById(String.valueOf(reference.getEntity().getId()));
		}
		
		return referencesSelectionMenu;
	}
	
	//method
	private void select() {
		
		final SelectionMenu referencesSelectionMenu =
		getRefGUI().getRefWidgetByName("ReferencesSelectionMenu");
		
		reference.set(
			reference
			.getReferencedEntitySet()
			.getRefEntityById(Long.valueOf(referencesSelectionMenu.getSelectedItemId()))
		);
		
		getParentClient().popSession();
	}
}
