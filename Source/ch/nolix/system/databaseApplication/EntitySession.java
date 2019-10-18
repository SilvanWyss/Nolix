//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.common.constants.StringCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.element.containerWidgets.ContainerRole;
import ch.nolix.element.containerWidgets.Grid;
import ch.nolix.element.containerWidgets.TabContainer;
import ch.nolix.element.containerWidgets.TabContainerTab;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.ButtonRole;
import ch.nolix.element.widgets.DropdownMenu;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.TextBox;
import ch.nolix.element.widgets.VerticalStack;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.EntitySet;
import ch.nolix.system.databaseAdapter.OptionalProperty;
import ch.nolix.system.databaseAdapter.Property;
import ch.nolix.system.databaseAdapter.Reference;

//class
public final class EntitySession extends HeaderedSession {
	
	//attributes
	private final String entitySetName;
	private final long entityId;
	
	//constructor
	public EntitySession(final String entitySetName, final long entityId) {
		
		super(entitySetName);
		
		this.entitySetName = entitySetName;
		this.entityId = entityId;
	}
	
	//method
	@Override
	protected List<Button> createLinkButtons() {
		return new List<>(
			new Button()
			.setText(entitySetName)
			.setRole(ButtonRole.LinkButton)
			.setLeftMouseButtonPressCommand(() -> openEntitySetSession())
		);
	}
	
	//method
	@Override
	protected VerticalStack createSubSubContentWidget() {
		return new VerticalStack(
			createMainDataWidget(),
			createReferenceDataWidget(),
			createBackReferenceDataWidget()
		);
	}
	
	//method
	private void cancel() {
		push(new EntitySession(entitySetName, entityId));
	}
	
	//method
	private TabContainer createBackReferenceDataWidget() {
		
		final var backReferenceDataTabContainer = new TabContainer();
		
		//TODO: Fill up back reference data tab container.
		
		return backReferenceDataTabContainer;
	}
	
	//method
	private Grid createDataGrid() {
		
		final var dataGrid = new Grid();
		
		int rowIndex = 1;
		for (final var p : getRefEntity().getRefProperties()) {
			
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
						new TextBox(property.getValue().toString())
						.setName(property.getHeader())
					);
					
					rowIndex++;
					
					break;
				case OPTIONAL_DATA:
					
					final var optionalProperty = (OptionalProperty<?>)p;
					
					dataGrid.setWidget(rowIndex, 1,	new Label(p.getHeader()));
					
					final var value =
					optionalProperty.isEmpty() ? StringCatalogue.EMPTY_STRING : optionalProperty.getValue().toString();
					dataGrid.setWidget(
						rowIndex,
						2,
						new TextBox(value)
						.setName(optionalProperty.getHeader())
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
					);
					
					final var dropdownMenu = new DropdownMenu();
					
					dropdownMenu.setSelectCommand(
						i -> referenceProperty.set(referenceProperty.getRefEntitySetOfReferencedEntities().getRefEntityById(Long.valueOf(i.getText())))
					);
					
					for (final var e : referenceProperty.getRefEntitySetOfReferencedEntities().getRefEntities()) {
						dropdownMenu.addItem(e.getIdAsString(), e.getIdAsString());
					}
					dropdownMenu.selectItemById(String.valueOf(referenceProperty.getReferencedEntityId()));
					
					dataGrid.setWidget(rowIndex, 2, dropdownMenu);
					
					rowIndex++;
					
					break;
				default:
					break;
			}
		}
		
		return dataGrid;
	}
	
	private VerticalStack createMainDataWidget() {
		return
		new VerticalStack(
			createDataGrid(),
			new HorizontalStack(
				new Button("Save")
				.setRole(ButtonRole.SaveButton)
				.setLeftMouseButtonPressCommand(() -> save()),
				new Button("Reset changes")
				.setRole(ButtonRole.CancelButton)
				.setLeftMouseButtonPressCommand(() -> cancel())
			)
		)
		.setRole(ContainerRole.MainContainer);
	}
	
	//method
	private TabContainer createReferenceDataWidget() {
		
		final var referenceDataTabContainer = new TabContainer();
		
		for (final var mr : getRefEntity().getRefMultiReferences()) {
			
			final var multiReferenceHorizontalStack = new HorizontalStack();
			for (final var e : mr.getRefEntities()) {
				final var entitySetName = e.getParentEntitySet().getName();
				final var entityId = e.getId();
				multiReferenceHorizontalStack.addWidget(
					new Button(e.getIdAsString())
					.setLeftMouseButtonPressCommand(() -> push(new EntitySession(entitySetName, entityId)))
				);
			}
			final var tab = new TabContainerTab(mr.getHeader(), multiReferenceHorizontalStack);
			
			referenceDataTabContainer.addTab(tab);
		}
		
		return referenceDataTabContainer;
	}

	//method
	private Entity getRefEntity() {
		return getRefEntitySet().getRefEntityById(entityId);
	}
	
	//method
	private EntitySet<Entity> getRefEntitySet() {
		return getRefDatabaseAdapter().getRefEntitySet(entitySetName);
	}
	
	//method
	private void openEntitySetSession() {
		push(new EntitySetSession(entitySetName));
	}
	
	//method
	private void save() {
		
		final var entity = getRefEntity();
		
		for (final var p : entity.getRefProperties()) {
			switch (p.getPropertyKind()) {
				case DATA:
					
					final var property = (Property<?>)p;
					
					final TextBox dataTextBox =	getRefGUI().getRefWidgetByName(p.getHeader());
					
					property.setValueFromString(dataTextBox.getText());
					
					break;
				case OPTIONAL_DATA:
					
					final var optionalProperty = (OptionalProperty<?>)p;
					
					final TextBox optionalDataTextBox =	getRefGUI().getRefWidgetByName(p.getHeader());
					
					if (optionalDataTextBox.getText().isBlank()) {
						optionalProperty.clear();
					}
					else {
						optionalProperty.setValueFromString(optionalDataTextBox.getText());
					}
					
					break;
				default:
					break;
			}
		}
		
		try {
			getRefDatabaseAdapter().saveChanges();
			setNext(new MessageSession("The changes have been saved."));
		}
		catch (final Exception exception) {
			getParentClient().showErrorMessageOnCounterpart(exception.getMessage());
		}
	}
}
