//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.containerWidget.Grid;
import ch.nolix.element.containerWidget.TabContainer;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.TextBox;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.EntitySet;
import ch.nolix.system.databaseAdapter.Property;
import ch.nolix.system.databaseAdapter.Reference;

//class
public final class EntitySession extends HeaderedSession {
	
	//attribute
	private final String entitySetName;
	private final long entityId;
	
	//constructor
	public EntitySession(
		final String entitySetName,
		final long entityId
	) {
		
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
		getParentClient()
		.pushSession(new EntitySession(entitySetName, entityId));
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
							new Button(String.valueOf(referenceProperty.getEntity().getId()))
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
		getParentClient().pushSession(
			new EntitySetSession(entitySetName)
		);
	}
	
	//method
	private void openEntitySession(final String entitySetName, final long entityId) {
		getParentClient().pushSession(
			new EntitySession(
				entitySetName,
				entityId
			)	
		);
	}
	
	//method
	@SuppressWarnings("unchecked")
	private void openReferencePropertySession(final String referencePropertyHeader) {
		
		final var referenceProperty = 
		(Reference<Entity>)getRefEntity()
		.getRefProperties()
		.getRefFirst(p -> p.hasHeader(referencePropertyHeader));
		
		getParentClient().pushSession(
			new ReferencePropertySession(referenceProperty),
			() -> {
				final Button label = getRefGUI().getRefWidgetByNameRecursively(referencePropertyHeader + "LinkButton");
				label.setText(String.valueOf(referenceProperty.getEntity().getId()));
			}
		);
	}
	
	//method
	private void save() {
		
		final var entity = getRefEntity();
		
		for (final var p : entity.getRefProperties()) {
			
			switch (p.getPropertyKind()) {
				case DATA:
					
					final var property = (Property<?>)p;
			
					final TextBox dataTextBox =
					getRefGUI().getRefWidgetByNameRecursively(p.getHeader());
					
					property.setUntypedValue(dataTextBox.getText());
					
					break;
				default:
					break;
			}
		}
		
		getRefDatabaseAdapter().saveChanges();
		
		getParentClient().pushSession(
			new MessageSession("The changes have been changed.")	
		);
	}
}
