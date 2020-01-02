//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.common.constants.StringCatalogue;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.node.Node;
import ch.nolix.element.containerWidgets.ContainerRole;
import ch.nolix.element.containerWidgets.Grid;
import ch.nolix.element.containerWidgets.TabContainer;
import ch.nolix.element.containerWidgets.TabContainerTab;
import ch.nolix.element.image.Image;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.ButtonRole;
import ch.nolix.element.widgets.DropdownMenu;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.ImageWidget;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.TextBox;
import ch.nolix.element.widgets.Uploader;
import ch.nolix.element.widgets.VerticalStack;
import ch.nolix.system.databaseAdapter.EntitySet;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.MultiReference;
import ch.nolix.system.entity.OptionalValueProperty;
import ch.nolix.system.entity.OptionalReference;
import ch.nolix.system.entity.ValueProperty;
import ch.nolix.system.entity.Reference;

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
	protected LinkedList<Button> createLinkButtons() {
		return new LinkedList<>(
			new Button()
			.setText("<--")
			.setRole(ButtonRole.LinkButton)
			.setLeftMouseButtonReleaseCommand(() -> pop()),
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
		
		//TODO: Fill up backReferenceDataTabContainer.
		
		return backReferenceDataTabContainer;
	}
	
	//TODO: Refactor this method.
	//method
	@SuppressWarnings("unchecked")
	private Grid createDataGrid() {
		
		final var dataGrid = new Grid();
		
		int rowIndex = 1;
		for (final var p : getRefEntity().getRefProperties()) {
			
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
						new TextBox(property.getValue().toString())
						.setName(property.getHeader())
					);
					
					rowIndex++;
					
					break;
				case OPTIONAL_VALUE:
					
					final var optionalProperty = (OptionalValueProperty<?>)p;
					
					dataGrid.setWidget(rowIndex, 1,	new Label(p.getHeader()));
					
					if (optionalProperty.getValueClass() == Image.class) {				
						
						if (optionalProperty.hasValue()) {
							dataGrid.setWidget(rowIndex, 2, new ImageWidget((Image)optionalProperty.getValue()));
						}
						
						dataGrid.setWidget(
							rowIndex,
							3,
							new Uploader().setLeftMouseButtonPressCommand(
								() -> {
									final var image = Image.fromBytes(getRefGUI().onFrontEnd().readFile());
									((OptionalValueProperty<Image>)optionalProperty).setValue(image);
								}
							)
						);
						
						rowIndex++;
						break;
					}
					
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
				case OPTIONAL_REFERENCE:
					
					final var optionalReferenceProperty = (OptionalReference<Entity>)p;
					
					dataGrid
					.setWidget(
						rowIndex,
						1,
						new Label(p.getHeader())
					);
					
					if (optionalReferenceProperty.getRefEntitySetOfReferencedEntities().containsAny()) {
					
						final var dropdownMenu2 = new DropdownMenu();
						
						dropdownMenu2.setSelectCommand(
							i -> optionalReferenceProperty.set(optionalReferenceProperty.getRefEntitySetOfReferencedEntities().getRefEntityById(Long.valueOf(i.getText())))
						);
						
						for (final var e : optionalReferenceProperty.getRefEntitySetOfReferencedEntities().getRefEntities()) {
							dropdownMenu2.addItem(e.getIdAsString(), e.getIdAsString());
						}
						if (optionalReferenceProperty.containsAny()) {
							dropdownMenu2.selectItemById(String.valueOf(optionalReferenceProperty.getReferencedEntityId()));
						}
						
						dataGrid.setWidget(rowIndex, 2, dropdownMenu2);
					}
					
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
	@SuppressWarnings("unchecked")
	private TabContainer createReferenceDataWidget() {
		
		final var referenceDataTabContainer = new TabContainer();
		
		for (final MultiReference<Entity> mr : getRefEntity().getRefMultiReferences()) {
			
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
	
	//TODO: Refactor this method.
	//method
	private void save() {
		
		final var entity = getRefEntity();
		
		for (final var p : entity.getRefProperties()) {
			switch (p.getPropertyKind()) {
				case VALUE:
					
					final var property = (ValueProperty<?>)p;
					
					final TextBox dataTextBox =	getRefGUI().getRefWidgetByName(p.getHeader());
					
					property.setValueFromString(dataTextBox.getText());
					
					break;
				case OPTIONAL_VALUE:
					
					final var optionalProperty = (OptionalValueProperty<?>)p;
					
					if (optionalProperty.getValueClass() == Image.class) {
						break;
					}
					
					final TextBox optionalDataTextBox =	getRefGUI().getRefWidgetByName(p.getHeader());
					
					if (optionalDataTextBox.getText().isBlank()) {
						optionalProperty.clear();
					}
					else {
						optionalProperty.setValueFromSpecification(Node.fromString(optionalDataTextBox.getText()));
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
			if (exception.getMessage() == null) {
				getParentClient().showErrorMessageOnCounterpart("An error occured.");
			}
			else {
				getParentClient().showErrorMessageOnCounterpart(exception.getMessage());
			}
		}
	}
}
