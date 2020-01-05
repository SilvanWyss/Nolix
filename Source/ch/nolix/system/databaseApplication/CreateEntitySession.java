//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.common.containers.LinkedList;
import ch.nolix.element.containerWidgets.Grid;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.ButtonRole;
import ch.nolix.element.widgets.DropdownMenu;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.TextBox;
import ch.nolix.element.widgets.VerticalStack;
import ch.nolix.system.databaseAdapter.EntitySet;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.OptionalReference;
import ch.nolix.system.entity.ValueProperty;
import ch.nolix.system.entity.Reference;

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
	protected LinkedList<Button> createLinkButtons() {
		return new LinkedList<>();
	}
	
	//method
	@Override
	protected VerticalStack createSubSubContentWidget() {
		
		newEntity = getRefEntitySet().createEmptyEntity();
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
		try {
			return createDataEntryGrid_();
		}
		catch (final Exception exception) {
			getParentClient().showErrorMessageOnCounterpart(exception.getMessage());
			return new Grid();
		}
	}

	//method
	private Grid createDataEntryGrid_() {
		
		final var dataGrid = new Grid();
		
		int rowIndex = 1;
		for (final var p : newEntity.getRefProperties()) {
			
			switch (p.getPropertyKind()) {
				case VALUE:
					
					final var property = (ValueProperty<?>)p;
					
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
						.setId(property.getHeader())
					);
					
					rowIndex++;
					
					break;
				case REFERENCE:
					
					@SuppressWarnings("unchecked")
					final var referenceProperty = (Reference<Entity>)p;
					
					if (referenceProperty.getRefEntitySetOfReferencedEntities().isEmpty()) {
						throw new RuntimeException(
							"A '" + entitySetName
							+ "' cannot be created, because it must have a "
							+ referenceProperty.getRefEntitySetOfReferencedEntities().getNameInQuotes()
							+ " and such one does not exist yet"
						);
					}
					
					dataGrid
					.setWidget(
						rowIndex,
						1,
						new Label(p.getHeader())
					);
					
					final var dropdownMenu = new DropdownMenu();
					dropdownMenu.setSelectCommand(
						i -> referenceProperty.set(referenceProperty.getRefEntitySetOfReferencedEntities().getRefEntityById(Long.valueOf(i.getText())))
					);
					for (final var e : referenceProperty.getRefEntitySetOfReferencedEntities().getRefEntities()) {
						dropdownMenu.addItem(e.getIdAsString(), e.getIdAsString());
					}
					
					dataGrid.setWidget(rowIndex, 2, dropdownMenu);
					
					rowIndex++;
					
					break;
				case OPTIONAL_REFERENCE:
					
					@SuppressWarnings("unchecked")
					final var optionalRreferenceProperty = (OptionalReference<Entity>)p;
					
					dataGrid
					.setWidget(
						rowIndex,
						1,
						new Label(optionalRreferenceProperty.getHeader())
					);
					
					if (optionalRreferenceProperty.getRefEntitySetOfReferencedEntities().containsAny()) {
						final var dropdownMenu2 = new DropdownMenu();
						dropdownMenu2.setSelectCommand(
							i -> optionalRreferenceProperty.set(optionalRreferenceProperty.getRefEntitySetOfReferencedEntities().getRefEntityById(Long.valueOf(i.getText())))
						);
						for (final var e : optionalRreferenceProperty.getRefEntitySetOfReferencedEntities().getRefEntities()) {
							dropdownMenu2.addItem(e.getIdAsString(), e.getIdAsString());
						}
						
						dataGrid.setWidget(rowIndex, 2, dropdownMenu2);
					}
					
					rowIndex++;
					
					break;
				default:
			}
		}
		
		return dataGrid;
	}
	
	//method
	private void createEntity() {
		try {
			for (final var p : newEntity.getRefProperties()) {
				
				switch (p.getPropertyKind()) {
					case VALUE:
						
						final var property = (ValueProperty<?>)p;
				
						final TextBox dataTextBox =	getRefGUI().getRefWidgetById(p.getHeader());
						
						property.setValueFromString(dataTextBox.getText());
						
						break;
					default:
						break;
				}
			}
		
			getRefDatabaseAdapter().saveChanges();
			pop();
		}
		catch (final Exception exception) {
			if (exception.getMessage() == null) {
				getParentClient().showErrorMessageOnCounterpart("An error occured.");
			}
			else {
				getParentClient().showErrorMessageOnCounterpart(exception.getMessage());
			}
		}
	}
	
	//method
	private EntitySet<Entity> getRefEntitySet() {
		return getRefDatabaseAdapter().getRefEntitySet(entitySetName);
	}
}
