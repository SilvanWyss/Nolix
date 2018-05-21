//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntitySet;
import ch.nolix.core.databaseAdapter.SingleProperty;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.ButtonRole;
import ch.nolix.element.GUI.Grid;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.TabContainer;
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.primitive.validator2.Validator;

//class
public final class EntitySession extends HeaderedSession {
	
	//attribute
	private final String entitySetName;
	private final int entityId;
	
	//constructor
	public EntitySession(
		final DatabaseApplicationContext databaseApplicationContext,
		final String entitySetName,
		final int entityId
	) {
		super(databaseApplicationContext, entitySetName);
		
		Validator.suppose(
			getRefDatabaseAdapter().containsEntitySet(entitySetName)
		);
		
		this.entitySetName = entitySetName;
		
		Validator.suppose(
			getRefEntitySet().containsEntity(entityId)
		);			
		
		this.entityId = entityId;
	}
	
	//method
	public void Cancel() {
		
		//TODO
		initialize();
	}
	
	//method
	public void OpenEntitySetSession() {
		getRefClient().setSession(
			new EntitySetSession(getRefContext(), entitySetName)
		);
	}
	
	//method
	public void Save() {
		
		final var entity = getRefEntity();
		
		for (final var p : entity.getRefProperties().getRefOfType(SingleProperty.class)) {		
			final TextBox dataTextBox = getRefClient().getRefGUI().getRefWidgetByNameRecursively(p.getHeader());
			p.setGenericValue(dataTextBox.getText());
		}
		
		getRefDatabaseAdapter().saveChanges();
		
		//TODO: Show confirmation message.
	}

	//method
	protected List<Button> createLinkButtons() {
		return new List<Button>(
			new Button()
			.setText(entitySetName)
			.setRole(ButtonRole.LinkButton)
			.setLeftMouseButtonPressCommand("OpenEntitySetSession")
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
	private Grid createDataGrid() {
		
		final var dataGrid = new Grid();
		
		int rowIndex = 1;
		for (final var p : getRefEntity().getRefProperties().getRefOfType(SingleProperty.class)) {
			
			dataGrid
			.setWidget(
				rowIndex,
				1,
				new Label(p.getHeader())
			)
			.setWidget(
				rowIndex,
				2,
				new TextBox(p.getValue().toString())
				.setName(p.getHeader())
			);
			
			rowIndex++;
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
				.setLeftMouseButtonPressCommand("Save"),
				new Button("Cancel")
				.setRole(ButtonRole.CancelButton)
				.setLeftMouseButtonPressCommand("Cancel")
			)
		);
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
