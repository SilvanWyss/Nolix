//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.common.containers.List;
import ch.nolix.element.containerWidgets.Grid;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.ButtonRole;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.TextBox;
import ch.nolix.element.widgets.VerticalStack;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.EntitySet;
import ch.nolix.system.databaseAdapter.Property;
import ch.nolix.system.databaseAdapter.Reference;

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
	@Override
	protected List<Button> createLinkButtons() {
		return new List<>();
	}
	
	//method
	@Override
	protected VerticalStack createSubSubContentWidget() {
		
		newEntity = getRefEntitySet().createDefaultEntity();
		getRefEntitySet().addEntity(newEntity);
		
		return
		new VerticalStack(
			createDataEntryGrid(),
			new HorizontalStack(
				new Button("Create")
				.setRole(ButtonRole.CreateButton)
				.setLeftMouseButtonPressCommand(() -> createEntity()),
				new Button("Cancel")
				.setRole(ButtonRole.CancelButton)
				.setLeftMouseButtonPressCommand(() -> cancel())
			)
		);
	}
	
	//method
	private void cancel() {
		pop();
	}

	//method
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
					
					final var referenceProperty = (Reference<Entity>)p;
					
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
							 () -> openReferencePropertySession(referenceProperty.getHeader())
							)
						)
					);
					
					rowIndex++;
					
					break;
				default:
			}
		}
		
		return dataGrid;
	}
	
	//method
	private void createEntity() {
		
		for (final var p : newEntity.getRefProperties()) {
			
			switch (p.getPropertyKind()) {
				case DATA:
					
					final var property = (Property<?>)p;
			
					final TextBox dataTextBox =
					getRefGUI().getRefWidgetByName(p.getHeader());
					
					property.setValueFromString(dataTextBox.getText());
					
					break;
				default:
					break;
			}
		}
		
		try {
			getRefDatabaseAdapter().saveChanges();
			pop();
		}
		catch (final Exception exception) {
			getParentClient().showErrorMessageOnCounterpart(exception.getMessage());
		}
	}
	
	//method
	private EntitySet<Entity> getRefEntitySet() {
		return getRefDatabaseAdapter().getRefEntitySet(entitySetName);
	}
	
	//method
	private void openReferencePropertySession(final String referencePropertyHeader) {
		
		final var referenceProperty = 
		(Reference<Entity>)newEntity
		.getRefProperties()
		.getRefFirst(p -> p.hasHeader(referencePropertyHeader));
		
		final Button label = getRefGUI().getRefWidgetByName(referencePropertyHeader + "LinkButton");
		final var referencedId = pushAndGetResult(new ReferencePropertySession(referenceProperty), Integer.class);
		label.setText(String.valueOf(referencedId));
	}
}
