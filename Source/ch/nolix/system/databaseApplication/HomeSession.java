//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.Grid;

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
	protected List<Button> createLinkButtons() {
		return new List<Button>();
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
		getParentClient().pushSession(
			new EntitySetSession(entitySetName)
		);
	}
}
