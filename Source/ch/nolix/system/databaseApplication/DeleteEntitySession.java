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

//class
public final class DeleteEntitySession extends HeaderedSession {

	//attribute
	private final String entitySetName;
	private final int entityId;
	
	//constructor
	public DeleteEntitySession(
		final String entitySetName,
		final int entityId
	) {
		
		super("Delete " + entitySetName + " " + entityId);
		
		this.entitySetName = entitySetName;		
		this.entityId = entityId;
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
				.setLeftMouseButtonPressCommand(() -> deleteEntity()),
				new Button("No")
				.setLeftMouseButtonPressCommand(() -> cancel())
			)
		);
	}
	
	//method
	private void cancel() {
		openEntitySetSession();
	}
	
	//method
	private void deleteEntity() {
		
		getRefEntitySet().deleteEntity(entityId);
		getRefDatabaseAdapter().saveChanges();
		
		openEntitySetSession();
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
}
