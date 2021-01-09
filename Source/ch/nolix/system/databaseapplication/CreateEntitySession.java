//package declaration
package ch.nolix.system.databaseapplication;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.exception.GeneralException;
import ch.nolix.element.containerwidget.Grid;
import ch.nolix.element.containerwidget.HorizontalStack;
import ch.nolix.element.containerwidget.VerticalStack;
import ch.nolix.element.dialog.ErrorDialog;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.DropdownMenu;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.TextBox;
import ch.nolix.system.databaseadapter.EntitySet;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.OptionalReference;
import ch.nolix.system.entity.Value;
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
		new VerticalStack()
		.addWidget(
			createDataEntryGrid(),
			new HorizontalStack()
			.addWidget(
				new Button()
				.setRole(ButtonRole.CREATE_BUTTON)
				.setText("Create")
				.setLeftMouseButtonPressAction(this::createEntity),
				new Button()
				.setRole(ButtonRole.CANCEL_BUTTON)
				.setText("Cancel")
				.setLeftMouseButtonPressAction(this::cancel)
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
		} catch (final Exception exception) {
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
					
					final var property = (Value<?>)p;
					
					dataGrid
					.setWidget(
						rowIndex,
						1,
						new Label().setText(p.getHeader())
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
						throw
						new GeneralException(
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
						new Label().setText(p.getHeader())
					);
					
					final var dropdownMenu = new DropdownMenu();
					dropdownMenu.setSelectAction(
						i ->
						referenceProperty.set(
							referenceProperty
							.getRefEntitySetOfReferencedEntities()
							.getRefEntityById(Long.valueOf(i.getId()))
						)
					);
					for (final var e : referenceProperty.getRefEntitySetOfReferencedEntities().getRefEntities()) {
						dropdownMenu.addItem(e.getIdAsString(), e.getShortDescription());
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
						new Label().setText(optionalRreferenceProperty.getHeader())
					);
					
					if (optionalRreferenceProperty.getRefEntitySetOfReferencedEntities().containsAny()) {
						final var dropdownMenu2 = new DropdownMenu();
						dropdownMenu2.setSelectAction(
							i ->
							optionalRreferenceProperty.set(
								optionalRreferenceProperty
								.getRefEntitySetOfReferencedEntities()
								.getRefEntityById(Long.valueOf(i.getText()))
							)
						);
						for (final var e : optionalRreferenceProperty.getRefEntitySetOfReferencedEntities().getRefEntities()) {
							dropdownMenu2.addItem(e.getIdAsString(), e.getShortDescription());
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
						
						final var property = (Value<?>)p;
				
						final TextBox dataTextBox =	getRefGUI().getRefWidgetById(p.getHeader());
						
						property.setValueFromString(dataTextBox.getText());
						
						break;
					default:
						break;
				}
			}
		
			getRefDatabaseAdapter().saveChanges();
			pop();
		} catch (final Exception exception) {
			if (exception.getMessage() == null) {
				getRefGUI().addLayerOnTop(new ErrorDialog("An error occured."));
			} else {
				getRefGUI().addLayerOnTop(new ErrorDialog(exception.getMessage()));
			}
		}
	}
	
	//method
	private EntitySet<Entity> getRefEntitySet() {
		return getRefDatabaseAdapter().getRefEntitySet(entitySetName);
	}
}
