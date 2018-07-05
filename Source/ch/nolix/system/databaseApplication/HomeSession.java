//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.Grid;

//class
public final class HomeSession extends HeaderedSession {

	//constructor
	public HomeSession() {
		super("Home");
	}
	
	//method
	protected List<Button> createLinkButtons() {
		return new List<Button>();
	}
	
	//method
	protected Grid createSubSubContentWidget() {
		
		final var entitySetsGrid = new Grid();
		
		int row = 1;
		int column = 1;
		for (final var es : getRefDatabaseAdapter().getRefEntitySets()) {
			
			entitySetsGrid.setWidget(
				row,
				column,
				new Button(es.getName())
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
		getParentClient().setSession(
			new EntitySetSession(entitySetName)
		);
	}
}
