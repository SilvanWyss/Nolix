//package declaration
package ch.nolix.system.database.databaseapplication;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.SelectionMenu;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.Reference;

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
		new VerticalStack()
		.addWidget(
			createReferencesSelectionMenu(),
			new HorizontalStack()
			.addWidget(
				new Button()
				.setText("Select")
				.setLeftMouseButtonPressAction(this::select),
				new Button()
				.setText("Cancel")
				.setLeftMouseButtonPressAction(this::cancel)
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
