//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntitySet;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.primitive.validator2.Validator;

//class
public final class DeleteEntitySession extends HeaderedSession {

	//attribute
	private final String entitySetName;
	private final int entityId;
	
	//constructor
	public DeleteEntitySession(
		final DatabaseApplicationContext databaseApplicationContext,
		final String entitySetName,
		final int entityId
	) {
		super(databaseApplicationContext, "Delete " + entitySetName + " " + entityId);
		
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
		openEntitySetSession();
	}
	
	public void DeleteEntity() {
		
		getRefEntitySet().deleteEntity(entityId);
		getRefDatabaseAdapter().saveChanges();
		
		openEntitySetSession();
	}

	//method
	protected List<Button> createFurtherNavigationButtons() {
		return new List<Button>();
	}
	
	//method
	protected List<Button> createLinkButtons() {
		return new List<Button>();
	}
	
	//method
	protected VerticalStack createSubSubContentWidget() {
		return
		new VerticalStack(
			new Label("Do you want to delete the entity " + entityId + "?"),
			new HorizontalStack(
				new Button("Yes")
				.setLeftMouseButtonPressCommand("DeleteEntity"),
				new Button("No")
				.setLeftMouseButtonPressCommand("Cancel")
			)
		);
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
