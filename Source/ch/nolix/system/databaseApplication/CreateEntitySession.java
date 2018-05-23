//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntitySet;
import ch.nolix.core.databaseAdapter.Property;
import ch.nolix.core.databaseAdapter.ReferenceProperty;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.ButtonRole;
import ch.nolix.element.GUI.Grid;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.SelectionMenu;
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.primitive.validator2.Validator;

//class
public final class CreateEntitySession extends HeaderedSession {

	//attribute
	private final String entitySetName;
	
	//constructor
	public CreateEntitySession(
		final DatabaseApplicationContext databaseApplicationContext,
		final String entitySetName
	) {
		super(databaseApplicationContext, "Create " + entitySetName);
		
		Validator.suppose(
			getRefDatabaseAdapter().containsEntitySet(entitySetName)
		);
			
		this.entitySetName = entitySetName;		
	}
	
	//method
	public void Cancel() {
		openEntitySetSession();
	}
	
	//method
	public void CreateEntity() {
		
		final var entity = getRefEntitySet().createEmptyEntity();
		getRefEntitySet().addEntity(entity);
		
		for (final var p : entity.getRefProperties()) {	
			
			switch (p.getPropertyKind()) {
				case DATA:
					
					final var property = (Property<?>)p;
			
					final TextBox dataTextBox =
					getRefClient().getRefGUI().getRefWidgetByNameRecursively(p.getHeader());
					
					property.setGenericValue(dataTextBox.getText());
					
					break;
					
				case REFERENCE:
					
					final var referenceProperty = (ReferenceProperty<Entity>)p;
					
					final SelectionMenu referenceSelectionMenu =
					getRefClient().getRefGUI().getRefWidgetByNameRecursively(referenceProperty.getHeader());
					
					referenceProperty.set(
						referenceProperty
						.getReferencedEntitySet()
						.getRefEntityById(referenceSelectionMenu.getSelectedItemId())
					);
					
					break;
			}
		}
		
		
		getRefDatabaseAdapter().saveChanges();
		
		openEntitySetSession();
	}

	//method
	protected List<Button> createLinkButtons() {
		return new List<Button>();
	}
	
	//method
	protected VerticalStack createSubSubContentWidget() {
		return
		new VerticalStack(
			createDataEntryGrid(),
			new HorizontalStack(
				new Button("Create")
				.setRole(ButtonRole.CreateButton)
				.setLeftMouseButtonPressCommand("CreateEntity"),
				new Button("Cancel")
				.setRole(ButtonRole.CancelButton)
				.setLeftMouseButtonPressCommand("Cancel")
			)
		);
	}

	//method
	private Grid createDataEntryGrid() {
		
		final var dataGrid = new Grid();
		
		var emptyEntity = getRefEntitySet().createEmptyEntity();
		getRefEntitySet().addEntity(emptyEntity);
		
		int rowIndex = 1;
		for (final var p : emptyEntity.getRefProperties()) {
			
			switch (p.getPropertyKind()) {
				case DATA:
					
					final var property = (Property<?>)p;
					
					dataGrid
					.setWidget(
						rowIndex,
						1,
						new Label(p.getHeader())
					)
					.setWidget(
						rowIndex,
						2,
						new TextBox()
						.setName(property.getHeader())
					);
					
					rowIndex++;
					
					break;
				case REFERENCE:
					
					final var referenceProperty = (ReferenceProperty<Entity>)p;
					
					dataGrid
					.setWidget(
						rowIndex,
						1,
						new Label(p.getHeader())
					);
					
					final var referencesSelectionMenu = new SelectionMenu().setName(referenceProperty.getHeader());
					
					for (final var e : referenceProperty.getReferencedEntitySet().getRefEntities()) {
						referencesSelectionMenu.addItem(e.getId(), e.getParentEntitySet().getName() + e.getId());
					}
					
					dataGrid.setWidget(
						rowIndex,
						2,
						referencesSelectionMenu
					);
					
					rowIndex++;
					
					break;
			}
		}
		
		getRefDatabaseAdapter().reset();
		
		return dataGrid;
	}
	
	//method
	private EntitySet<Entity> getRefEntitySet() {
		return getRefDatabaseAdapter().getRefEntitySet(entitySetName);
	}
	
	//method
	private void openEntitySetSession() {
		getRefClient().setSession(
			new EntitySetSession(getRefContext(), entitySetName)
		);
	}
}
