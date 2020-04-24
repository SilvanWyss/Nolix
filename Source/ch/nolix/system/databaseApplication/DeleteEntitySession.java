//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.system.databaseAdapter.EntitySet;
import ch.nolix.system.entity.Entity;

//class
public final class DeleteEntitySession extends HeaderedSession {

	//attribute
	private final String entitySetName;
	private final long entityId;
	
	//constructor
	public DeleteEntitySession(
		final String entitySetName,
		final long entityId
	) {
		
		super("Delete " + entitySetName);
		
		Validator
		.assertThat(entitySetName)
		.thatIsNamed("entity set name")
		.isNotEmpty();
		
		this.entitySetName = entitySetName;
		this.entityId = entityId;
	}

	//method
	protected LinkedList<Button> createFurtherNavigationButtons() {
		return new LinkedList<>();
	}
	
	//method
	@Override
	protected LinkedList<Button> createLinkButtons() {
		return new LinkedList<>();
	}
	
	//method
	@Override
	protected VerticalStack createSubSubContentWidget() {
		return
		new VerticalStack(
			new Label("Do you want to delete the entity (id: " + entityId + ")?"),
			new HorizontalStack(
				new Button("Yes")
				.setRole(ButtonRole.ActionButton)
				.setLeftMouseButtonPressCommand(() -> deleteEntity()),
				new Button("No")
				.setRole(ButtonRole.ActionButton)
				.setLeftMouseButtonPressCommand(() -> cancel())
			)
		);
	}
	
	//method
	private void cancel() {
		pop();
	}
	
	//method
	private void deleteEntity() {
		
		getRefEntitySet().deleteEntity(entityId);
		getRefDatabaseAdapter().saveChanges();
		
		pop();
	}
	
	//method
	private EntitySet<Entity> getRefEntitySet() {
		return getRefDatabaseAdapter().getRefEntitySet(entitySetName);
	}
}
