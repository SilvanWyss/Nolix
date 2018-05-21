//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntitySet;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.ButtonRole;
import ch.nolix.element.GUI.Grid;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.primitive.validator2.Validator;

//class
public final class CreateEntitySession extends HeaderedSession {

	//attribute
	private final String entitySetName;
	
	//constructor
	public CreateEntitySession(
		final DatabaseApplicationContext databaseApplicationContext,
		final String entitySetName
	) {
		super(databaseApplicationContext, "Create " + entitySetName);
		
		Validator.suppose(
			getRefDatabaseAdapter().containsEntitySet(entitySetName)
		);
			
		this.entitySetName = entitySetName;		
	}
	
	//method
	public void Cancel() {
		openEntitySetSession();
	}
	
	//method
	public void CreateEntity() {
		
		final var entity = getRefEntitySet().createEmptyEntity();
		
		int rowIndex = 1;
		for (final var p : entity.getRefProperties().getRefSelected(p -> p.isDataProperty())) {
			
			final TextBox dataTextBox = getRefClient().getRefGUI().getRefWidgetByNameRecursively("dataTextBox" + rowIndex);
			p.setGenericValue(dataTextBox.getText());
			
			rowIndex++;
		}
		
		getRefEntitySet().addEntity(entity);
		getRefDatabaseAdapter().saveChanges();
		
		openEntitySetSession();
	}

	//method
	protected List<Button> createLinkButtons() {
		return new List<Button>();
	}
	
	//method
	protected VerticalStack createSubSubContentWidget() {
		return
		new VerticalStack(
			createDataEntryGrid(),
			new HorizontalStack(
				new Button("Create")
				.setRole(ButtonRole.CreateButton)
				.setLeftMouseButtonPressCommand("CreateEntity"),
				new Button("Cancel")
				.setRole(ButtonRole.CancelButton)
				.setLeftMouseButtonPressCommand("Cancel")
			)
		);
	}

	//method
	private Grid createDataEntryGrid() {
		
		final var dataEntryGrid = new Grid();
		
		int rowIndex = 1;
		for (final var c : getRefEntitySet().getColumns().getRefSelected(c -> c.isDataColumn())) {
			
			dataEntryGrid
			.setWidget(
				rowIndex,
				1,
				new Label(c.getHeader())
			)
			.setWidget(
				rowIndex,
				2,
				new TextBox()
				.setName("dataTextBox" + rowIndex)
			);
			
			rowIndex++;
		}
		
		return dataEntryGrid;
	}
	
	//method
	private EntitySet<Entity> getRefEntitySet() {
		return getRefDatabaseAdapter().getRefEntitySet(entitySetName);
	}
	
	//method
	private void openEntitySetSession() {
		getRefClient().setSession(
			new EntitySetSession(getRefContext(), entitySetName)
		);
	}
}
