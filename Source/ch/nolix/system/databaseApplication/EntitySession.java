//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.common.containers.List;
import ch.nolix.element.containerWidgets.ContainerRole;
import ch.nolix.element.containerWidgets.Grid;
import ch.nolix.element.containerWidgets.TabContainer;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.ButtonRole;
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
					
					if (optionalProperty.hasValue()) {
						dataGrid.setWidget(
							rowIndex,
							2,
							new TextBox(optionalProperty.getValue().toString())
							.setName(optionalProperty.getHeader())
						);
					}
					
					rowIndex++;
					
					break;
					
				case REFERENCE:
					
					@SuppressWarnings("unchecked")
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
							new Button(referenceProperty.getEntity().getIdAsString())
							.setRole(ButtonRole.LinkButton)
							.setName(referenceProperty.getHeader() + "LinkButton")
							.setLeftMouseButtonPressCommand(
								() -> openEntitySession(referenceProperty.getReferencedEntitySet().getName(), referenceProperty.getEntity().getId())
							),
							new Button("Select")
							.setLeftMouseButtonPressCommand(
								() -> openReferencePropertySession(referenceProperty.getHeader())
							)
						)
					);
					
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
		
		//TODO: Fill up reference data tab container.
		
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
	private void openEntitySession(final String entitySetName, final long entityId) {
		push(new EntitySession(entitySetName, entityId));
	}
	
	//method
	private void openReferencePropertySession(final String referencePropertyHeader) {
		
		//TODO
//		final var referenceProperty = 
//		(Reference<Entity>)getRefEntity()
//		.getRefProperties()
//		.getRefFirst(p -> p.hasHeader(referencePropertyHeader));
		
		
//		push(
//			new ReferencePropertySession(referenceProperty),
//			() -> {
//				final Button label = internal_getRefGUI().getRefWidgetByName(referencePropertyHeader + "LinkButton");
//				label.setText(String.valueOf(referenceProperty.getEntity().getId()));
//			}
//		);
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
		
		getRefDatabaseAdapter().saveChanges();
		
		push(new MessageSession("The changes have been changed."));
	}
}
