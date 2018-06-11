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
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.GUI.Grid;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.TabContainer;
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.GUI.VerticalStack;

//class
public final class EntitySession extends HeaderedSession {
	
	//attribute
	private final String entitySetName;
	private final int entityId;
	
	//constructor
	public EntitySession(
		final String entitySetName,
		final int entityId
	) {
		
		super(entitySetName);
		
		this.entitySetName = entitySetName;			
		this.entityId = entityId;
	}
	
	//method
	public void Cancel() {
		getParentClient()
		.setSession(new EntitySession(entitySetName, entityId));
	}
	
	//method
	public void OpenEntitySetSession() {
		getParentClient().setSession(
			new EntitySetSession(entitySetName)
		);
	}
	
	//method
	public void OpenEntitySession(final String entitySetName, final int entityId) {
		getParentClient().setSession(
			new EntitySession(
				entitySetName,
				entityId
			)	
		);
	}
	
	//method
	@SuppressWarnings("unchecked")
	public void OpenReferencePropertySession(final String referencePropertyHeader) {
		
		final var referenceProperty = 
		(ReferenceProperty<Entity>)getRefEntity()
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
	@SuppressWarnings("incomplete-switch")
	public void Save() {
		
		final var entity = getRefEntity();
		
		for (final var p : entity.getRefProperties()) {	
			
			switch (p.getPropertyKind()) {
				case DATA:
					
					final var property = (Property<?>)p;
			
					final TextBox dataTextBox =
					getParentClient().getRefGUI().getRefWidgetByNameRecursively(p.getHeader());
					
					property.setGenericValue(dataTextBox.getText());
					
					break;
			}
		}
		
		getRefDatabaseAdapter().saveChanges();
		
		getParentClient().pushSession(
			new MessageSession("The changes have been changed.")	
		);
	}

	//method
	protected List<Button> createLinkButtons() {
		return new List<Button>(
			new Button()
			.setText(entitySetName)
			.setRole(ButtonRole.LinkButton)
			.setLeftMouseButtonPressCommand(() -> OpenEntitySetSession())
		);
	}
	
	//method
	protected VerticalStack createSubSubContentWidget() {
		return new VerticalStack(
			createMainDataWidget(),
			createReferenceDataWidget(),
			createBackReferenceDataWidget()
		);
	}
	
	//method
	private TabContainer createBackReferenceDataWidget() {
		
		final var backReferenceDataTabContainer = new TabContainer();
		
		//TODO
		
		return backReferenceDataTabContainer;
	}
	
	//method
	@SuppressWarnings({ "incomplete-switch", "unchecked" })
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
							new Button(String.valueOf(referenceProperty.getEntity().getId()))
							.setRole(ButtonRole.LinkButton)
							.setName(referenceProperty.getHeader() + "LinkButton")
							.setLeftMouseButtonPressCommand(
								() -> OpenEntitySession(referenceProperty.getReferencedEntitySet().getName(), referenceProperty.getEntity().getId())
							),
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
	
	private VerticalStack createMainDataWidget() {
		return
		new VerticalStack(
			createDataGrid(),
			new HorizontalStack(
				new Button("Save")
				.setRole(ButtonRole.SaveButton)
				.setLeftMouseButtonPressCommand(() -> Save()),
				new Button("Reset changes")
				.setRole(ButtonRole.CancelButton)
				.setLeftMouseButtonPressCommand(() -> Cancel())
			)
		)
		.setRole(ContainerRole.MainContainer);
	}
	
	//method
	private TabContainer createReferenceDataWidget() {
		
		final var referenceDataTabContainer = new TabContainer();
		
		//TODO
		
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
}
