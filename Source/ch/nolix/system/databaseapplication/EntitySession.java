//package declaration
package ch.nolix.system.databaseapplication;

//own imports
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.Node;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.containerwidget.Grid;
import ch.nolix.element.containerwidget.TabContainer;
import ch.nolix.element.containerwidget.TabContainerTab;
import ch.nolix.element.dialog.ErrorDialog;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.DropdownMenu;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.TextBox;
import ch.nolix.element.widget.Uploader;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.system.databaseadapter.EntitySet;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.MultiReference;
import ch.nolix.system.entity.OptionalValue;
import ch.nolix.system.entity.OptionalReference;
import ch.nolix.system.entity.Value;
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
		return
		LinkedList.withElements(
			new Button()
			.setText("<--")
			.setRole(ButtonRole.LINK_BUTTON)
			.setLeftMouseButtonReleaseAction(b -> pop()),
			new Button()
			.setText(entitySetName)
			.setRole(ButtonRole.LINK_BUTTON)
			.setLeftMouseButtonPressAction(this::openEntitySetSession)
		);
	}
	
	//method
	@Override
	protected VerticalStack createSubSubContentWidget() {
		return
		new VerticalStack()
		.addWidget(
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
		
		//TODO: Fill up TabCotainer..
		return new TabContainer();
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
						.setText(property.getValue().toString())
						.setId(property.getHeader())
					);
					
					rowIndex++;
					
					break;
				case OPTIONAL_VALUE:
					
					final var optionalProperty = (OptionalValue<?>)p;
					
					dataGrid.setWidget(rowIndex, 1,	new Label().setText(p.getHeader()));
					
					if (optionalProperty.getValueClass() == Image.class) {				
						
						if (optionalProperty.hasValue()) {
							dataGrid.setWidget(rowIndex, 2, new ImageWidget().setImage((Image)optionalProperty.getValue()));
						}
						
						dataGrid.setWidget(
							rowIndex,
							3,
							new Uploader().setLeftMouseButtonPressAction(
								() -> {
									
									final var image =
									Image.fromBytes(getRefGUI().fromFrontEnd().readFileToBytes().getRefElement());
									
									((OptionalValue<Image>)optionalProperty).setValue(image);
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
						new TextBox()
						.setText(value)
						.setId(optionalProperty.getHeader())
					);
					
					rowIndex++;
					
					break;
					
				case REFERENCE:
					
					final var referenceProperty = (Reference<Entity>)p;
					
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
						new Label().setText(p.getHeader())
					);
					
					if (optionalReferenceProperty.getRefEntitySetOfReferencedEntities().containsAny()) {
					
						final var dropdownMenu2 = new DropdownMenu();
						
						dropdownMenu2.setSelectAction(
							i ->
							optionalReferenceProperty.set(
								optionalReferenceProperty
								.getRefEntitySetOfReferencedEntities()
								.getRefEntityById(Long.valueOf(i.getText()))
							)
						);
						
						for (final var e : optionalReferenceProperty.getRefEntitySetOfReferencedEntities().getRefEntities()) {
							dropdownMenu2.addItem(e.getIdAsString(), e.getShortDescription());
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
		new VerticalStack()
		.addWidget(
			createDataGrid(),
			new HorizontalStack()
			.addWidget(
				new Button()
				.setRole(ButtonRole.SAVE_BUTTON)
				.setText("Save")
				.setLeftMouseButtonPressAction(this::save),
				new Button()
				.setRole(ButtonRole.CANCEL_BUTTON)
				.setText("Reset changes")
				.setLeftMouseButtonPressAction(this::cancel)
			)
		)
		.setRole(ContainerRole.MAINT_CONTAINER);
	}
	
	//method
	@SuppressWarnings("unchecked")
	private TabContainer createReferenceDataWidget() {
		
		final var referenceDataTabContainer = new TabContainer();
		
		for (final MultiReference<Entity> mr : getRefEntity().getRefMultiReferences()) {
			
			final var multiReferenceHorizontalStack = new HorizontalStack();
			for (final var e : mr.getRefEntities()) {
				final var lEntitySetName = e.getParentEntitySet().getName();
				final var lEntityId = e.getId();
				multiReferenceHorizontalStack.addWidget(
					new Button()
					.setText(e.getIdAsString())
					.setLeftMouseButtonPressAction(() -> push(new EntitySession(lEntitySetName, lEntityId)))
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
		try {
			
			final var entity = getRefEntity();
			
			for (final var p : entity.getRefProperties()) {
				switch (p.getPropertyKind()) {
					case VALUE:
						
						final var property = (Value<?>)p;
						
						final TextBox dataTextBox =	getRefGUI().getRefWidgetById(p.getHeader());
						
						property.setValueFromString(dataTextBox.getText());
						
						break;
					case OPTIONAL_VALUE:
						
						final var optionalProperty = (OptionalValue<?>)p;
						
						if (optionalProperty.getValueClass() == Image.class) {
							break;
						}
						
						final TextBox optionalDataTextBox =	getRefGUI().getRefWidgetById(p.getHeader());
						
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
			
			getRefDatabaseAdapter().saveChanges();
			pop();
		}
		catch (final Exception exception) {
			if (exception.getMessage() == null) {
				getRefGUI().addLayerOnTop(new ErrorDialog("An error occured."));
			}
			else {
				getRefGUI().addLayerOnTop(new ErrorDialog(exception.getMessage()));
			}
		}
	}
}
