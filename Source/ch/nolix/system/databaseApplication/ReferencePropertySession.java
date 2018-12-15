//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Reference;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.SelectionMenu;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.GUI.Widget;

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
		return new List<Button>();
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
			referencesSelectionMenu.addItem((int)e.getId(), /*e.getParentEntitySet().getName() +*/ " " + e.getId());
		}
		
		if (reference.referencesEntity()) {
			referencesSelectionMenu.select((int)reference.getEntity().getId());
		}
		
		return referencesSelectionMenu;
	}
	
	//method
	private void select() {
		
		final SelectionMenu referencesSelectionMenu =
		getRefGUI().getRefWidgetByNameRecursively("ReferencesSelectionMenu");
		
		reference.set(
			reference
			.getReferencedEntitySet()
			.getRefEntityById(referencesSelectionMenu.getSelectedItemId())
		);
		
		getParentClient().popSession();
	}
}
