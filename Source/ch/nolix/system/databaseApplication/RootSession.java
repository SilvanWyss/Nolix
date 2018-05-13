//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.Grid;
import ch.nolix.element.GUI.VerticalStack;

//class
public final class RootSession extends DatabaseApplicationSession {

	//constructor
	public RootSession(
		final DatabaseApplicationContext databaseApplicationContext
	) {
		super(databaseApplicationContext);
	}
	
	//method
	public void initialize() {
		
		final var entitySetsGrid = new Grid();
		
		int row = 1;
		int column = 1;
		for (final var es : getRefDatabaseAdapter().getRefEntitySets()) {
			
			entitySetsGrid.setWidget(
				row,
				column,
				new Button(es.getName())
				.setLeftMouseButtonPressCommand(
					"OpenEntitySetSession(" + es.getName() + ")"
				)
			);
			
			column++;
			
			if (column > 5) {
				column = 1;
				row++;
			}
		}
		
		getRefClient()
		.getRefGUI()
		.setRootWidget(
			new VerticalStack(
				createTitleLabel(),
				entitySetsGrid
			)
		);
	}
	
	//method
	public void OpenEntitySetSession(final String entitySetName) {
		//TODO
	}
}
