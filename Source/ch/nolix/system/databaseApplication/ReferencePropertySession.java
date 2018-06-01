//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.ReferenceProperty;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.SelectionMenu;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.GUI.Widget;

//class
public final class ReferencePropertySession extends HeaderedSession {
	
	//attributes
	private final ReferenceProperty<Entity> referenceProperty;
	
	//constructor
	public ReferencePropertySession(
		final DatabaseApplicationContext databaseApplicationContext,
		final ReferenceProperty<Entity> referenceProperty
	) {
		super(
			databaseApplicationContext,
			referenceProperty.getReferencedEntitySet().getName()
		);
		
		this.referenceProperty = referenceProperty;
	}
	
	//method
	public void Cancel() {
		getParentClient().popCurrentSession();
	}
	
	//method
	public void Select() {
		
		final SelectionMenu referencesSelectionMenu =
		getParentClient()
		.getRefGUI()
		.getRefWidgetByNameRecursively("ReferencesSelectionMenu");
		
		referenceProperty.set(
			referenceProperty
			.getReferencedEntitySet()
			.getRefEntityById(referencesSelectionMenu.getSelectedItemId())
		);
		
		getParentClient().popCurrentSession();
	}

	//method
	protected List<Button> createLinkButtons() {
		return new List<Button>();
	}

	//method
	protected Widget<?, ?> createSubSubContentWidget() {
		return
		new VerticalStack(
			createReferencesSelectionMenu(),
			new HorizontalStack(
				new Button("Select")
				.setLeftMouseButtonPressCommand("Select"),
				new Button("Cancel")
				.setLeftMouseButtonPressCommand("Cancel")
			)
		);
	}

	//method
	private SelectionMenu createReferencesSelectionMenu() {
		
		final var referencesSelectionMenu = new SelectionMenu().setName("ReferencesSelectionMenu");
		
		for (final var e : referenceProperty.getReferencedEntitySet().getRefEntities()) {
			referencesSelectionMenu.addItem(e.getId(), /*e.getParentEntitySet().getName() +*/ " " + e.getId());
		}
		
		if (referenceProperty.referencesEntity()) {		
			referencesSelectionMenu.select(referenceProperty.getEntity().getId());
		}
		
		return referencesSelectionMenu;
	}
}
