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
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.GUI.VerticalStack;

//class
public final class CreateEntitySession extends HeaderedSession {

	//attribute
	private final String entitySetName;
	private Entity newEntity;
	
	//constructor
	public CreateEntitySession(final String entitySetName) {
		
		super("Create " + entitySetName);
			
		this.entitySetName = entitySetName;
	}
	
	//method
	public void Cancel() {
		getParentClient().popCurrentSession();
	}
	
	//method
	@SuppressWarnings({ "incomplete-switch" })
	public void CreateEntity() {
		
		for (final var p : newEntity.getRefProperties()) {	
			
			switch (p.getPropertyKind()) {
				case DATA:
					
					final var property = (Property<?>)p;
			
					final TextBox dataTextBox =
					getRefGUI().getRefWidgetByNameRecursively(p.getHeader());
					
					property.setGenericValue(dataTextBox.getText());
					
					break;
					
				case REFERENCE:
					
					break;
			}
		}
		
		getRefDatabaseAdapter().saveChanges();
		
		getParentClient().popCurrentSession();
	}
	
	//method
	@SuppressWarnings("unchecked")
	public void OpenReferencePropertySession(final String referencePropertyHeader) {
		
		final var referenceProperty = 
		(ReferenceProperty<Entity>)newEntity
		.getRefProperties()
		.getRefFirst(p -> p.hasHeader(referencePropertyHeader));
		
		getParentClient().pushSession(
			new ReferencePropertySession(referenceProperty),
			() -> {
				final Button label = getRefGUI().getRefWidgetByNameRecursively(referencePropertyHeader + "LinkButton");
				
				if (referenceProperty.referencesEntity()) {
					label.setText(String.valueOf(referenceProperty.getEntity().getId()));
				}
			}
		);
	}

	//method
	protected List<Button> createLinkButtons() {
		return new List<Button>();
	}
	
	//method
	protected VerticalStack createSubSubContentWidget() {
		
		newEntity = getRefEntitySet().createDefaultEntity();
		getRefEntitySet().addEntity(newEntity);
		
		return
		new VerticalStack(
			createDataEntryGrid(),
			new HorizontalStack(
				new Button("Create")
				.setRole(ButtonRole.CreateButton)
				.setLeftMouseButtonPressCommand(() -> CreateEntity()),
				new Button("Cancel")
				.setRole(ButtonRole.CancelButton)
				.setLeftMouseButtonPressCommand(() -> Cancel())
			)
		);
	}

	//method
	@SuppressWarnings({ "incomplete-switch", "unchecked" })
	private Grid createDataEntryGrid() {
		
		final var dataGrid = new Grid();
		
		int rowIndex = 1;
		for (final var p : newEntity.getRefProperties()) {
			
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
						.setName(referenceProperty.getHeader())
					);
					
					dataGrid.setWidget(
						rowIndex,
						2,
						new HorizontalStack(
							new Button()
							.setRole(ButtonRole.LinkButton)
							.setName(referenceProperty.getHeader() + "LinkButton"),
							new Button("Select")
							.setLeftMouseButtonPressCommand(
							   () -> OpenReferencePropertySession(referenceProperty.getHeader())
							)
						)
					);
					
					rowIndex++;
					
					break;
			}
		}
		
		return dataGrid;
	}
	
	//method
	private EntitySet<Entity> getRefEntitySet() {
		return getRefDatabaseAdapter().getRefEntitySet(entitySetName);
	}
}
