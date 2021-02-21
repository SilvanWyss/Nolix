//package declaration
package ch.nolix.system.database.databaseapplication;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.containerwidget.Grid;
import ch.nolix.element.containerwidget.HorizontalStack;
import ch.nolix.element.containerwidget.VerticalStack;
import ch.nolix.element.dialog.ErrorDialogCreator;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.Label;
import ch.nolix.system.database.databaseadapter.EntitySet;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.propertybinder.CentralPropertyBinder;

//class
public final class CreateEntitySession extends HeaderedSession {
	
	//attributes
	private final String entitySetName;
	private Entity newEntity;
	
	//constructor
	public CreateEntitySession(final String entitySetName) {
		
		super("Create " + entitySetName);
			
		this.entitySetName = entitySetName;
	}

	//method
	@Override
	protected LinkedList<Button> createLinkButtons() {
		return new LinkedList<>();
	}
	
	//method
	@Override
	protected VerticalStack createSubSubContentWidget() {
		
		newEntity = getRefEntitySet().createEmptyEntity();
		getRefEntitySet().addEntity(newEntity);
		
		return
		new VerticalStack()
		.addWidget(
			createDataEntryGrid(),
			new HorizontalStack()
			.addWidget(
				new Button()
				.setRole(ButtonRole.CREATE_BUTTON)
				.setText("Create")
				.setLeftMouseButtonPressAction(this::createEntity),
				new Button()
				.setRole(ButtonRole.CANCEL_BUTTON)
				.setText("Cancel")
				.setLeftMouseButtonPressAction(this::cancel)
			)
		);
	}
	
	//method
	private void cancel() {
		pop();
	}
	
	//method
	private Grid createDataEntryGrid() {
		try {
			return createDataEntryGrid_();
		} catch (final Exception exception) {
			getParentClient().showErrorMessageOnCounterpart(exception.getMessage());
			return new Grid();
		}
	}
	
	//method
	private Grid createDataEntryGrid_() {
		
		final var dataGrid = new Grid();
		
		var rowIndex = 1;
		for (final var p : newEntity.getRefProperties()) {
			if (p.isAtMostOne()) {
				
				dataGrid
				.setWidget(rowIndex, 1, new Label().setText(p.getHeader()))
				.setWidget(rowIndex, 2, CentralPropertyBinder.createWidgetAndBindItWith(p).getRefWidget());
				
				rowIndex++;
			}
		}
		
		return dataGrid;
	}
	
	//method
	private void createEntity() {
		try {
			getRefDatabaseAdapter().saveChanges();
			pop();
		} catch (final Exception exception) {
			if (exception.getMessage() == null) {
				getRefGUI().addLayerOnTop(new ErrorDialogCreator().createWithErrorMessage("An error occured."));
			} else {
				getRefGUI().addLayerOnTop(new ErrorDialogCreator().createWithException(exception));
			}
		}
	}
	
	//method
	private EntitySet<Entity> getRefEntitySet() {
		return getRefDatabaseAdapter().getRefEntitySet(entitySetName);
	}
}
