//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.common.container.LinkedList;
import ch.nolix.element.containerWidget.Grid;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.ButtonRole;

//class
public final class HomeSession extends HeaderedSession {
	
	//constant
	public static final String TITLE = "Home";
	
	//constructor
	public HomeSession() {
		super(TITLE);
	}
	
	//method
	@Override
	protected LinkedList<Button> createLinkButtons() {
		return new LinkedList<>();
	}
	
	//method
	@Override
	protected Grid createSubSubContentWidget() {
		
		final var entitySetsGrid = new Grid();
		
		var row = 1;
		var column = 1;
		for (final var es : getRefDatabaseAdapter().getRefEntitySets()) {
			
			entitySetsGrid.setWidget(
				row,
				column,
				new Button(es.getName())
				.setRole(ButtonRole.LinkButton)
				.setLeftMouseButtonPressCommand(
					() -> openEntitySetSession(es.getName())
				)
			);
			
			column++;
			
			if (column > 5) {
				column = 1;
				row++;
			}
		}
		
		return entitySetsGrid;
	}
	
	//method
	private void openEntitySetSession(final String entitySetName) {
		push(new EntitySetSession(entitySetName));
	}
}
