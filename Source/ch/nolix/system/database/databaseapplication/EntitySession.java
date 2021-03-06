//package declaration
package ch.nolix.system.database.databaseapplication;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.containerwidget.Grid;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.containerwidget.TabContainer;
import ch.nolix.element.gui.containerwidget.TabContainerTab;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.dialog.ErrorDialogCreator;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.ButtonRole;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.system.database.databaseadapter.EntitySet;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.MultiReference;
import ch.nolix.system.database.propertybinder.CentralPropertyBinder;

//class
public final class EntitySession extends HeaderedSession {
	
	//attributes
	private final String entitySetName;
	private final long entityId;
	
	//constructor
	public EntitySession(final String entitySetName, final long entityId) {
		
		super(entitySetName);
		
		this.entitySetName = entitySetName;
		this.entityId = entityId;
	}
	
	//method
	@Override
	protected LinkedList<Button> createLinkButtons() {
		return
		LinkedList.withElements(
			new Button()
			.setText("<--")
			.setRole(ButtonRole.LINK_BUTTON)
			.setLeftMouseButtonReleaseAction(b -> pop()),
			new Button()
			.setText(entitySetName)
			.setRole(ButtonRole.LINK_BUTTON)
			.setLeftMouseButtonPressAction(this::openEntitySetSession)
		);
	}
	
	//method
	@Override
	protected VerticalStack createSubSubContentWidget() {
		return
		new VerticalStack()
		.addWidget(
			createMainDataWidget(),
			createReferenceDataWidget(),
			createBackReferenceDataWidget()
		);
	}
	
	//method
	private void cancel() {
		push(new EntitySession(entitySetName, entityId));
	}
	
	//method
	private TabContainer createBackReferenceDataWidget() {
		
		//TODO: Fill up TabCotainer.
		return new TabContainer();
	}
	
	//method
	private Grid createDataGrid() {
		
		final var dataGrid = new Grid();
		
		var rowIndex = 1;
		for (final var p : getRefEntity().getRefProperties()) {
			if (p.isAtMostOne()) {
				
				dataGrid
				.setWidget(rowIndex, 1, new Label().setText(p.getHeader()))
				.setWidget(rowIndex, 2, CentralPropertyBinder.createWidgetAndBindItWith(p).getRefWidget());
				
				rowIndex++;
			}
		}
		
		return dataGrid;
	}
	
	private VerticalStack createMainDataWidget() {
		return
		new VerticalStack()
		.addWidget(
			createDataGrid(),
			new HorizontalStack()
			.addWidget(
				new Button()
				.setRole(ButtonRole.SAVE_BUTTON)
				.setText("Save")
				.setLeftMouseButtonPressAction(this::save),
				new Button()
				.setRole(ButtonRole.CANCEL_BUTTON)
				.setText("Reset changes")
				.setLeftMouseButtonPressAction(this::cancel)
			)
		)
		.setRole(ContainerRole.MAINT_CONTAINER);
	}
	
	//method
	@SuppressWarnings("unchecked")
	private TabContainer createReferenceDataWidget() {
		
		final var referenceDataTabContainer = new TabContainer();
		
		for (final MultiReference<Entity> mr : getRefEntity().getRefMultiReferences()) {
			
			final var multiReferenceHorizontalStack = new HorizontalStack();
			for (final var e : mr.getRefEntities()) {
				final var lEntitySetName = e.getParentEntitySet().getName();
				final var lEntityId = e.getId();
				multiReferenceHorizontalStack.addWidget(
					new Button()
					.setText(e.getIdAsString())
					.setLeftMouseButtonPressAction(() -> push(new EntitySession(lEntitySetName, lEntityId)))
				);
			}
			final var tab = new TabContainerTab(mr.getHeader(), multiReferenceHorizontalStack);
			
			referenceDataTabContainer.addTab(tab);
		}
		
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
	
	//method
	private void openEntitySetSession() {
		push(new EntitySetSession(entitySetName));
	}
	
	//method
	private void save() {
		try {
			getRefDatabaseAdapter().saveChanges();
			pop();
		} catch (final Exception exception) {
			getRefGUI().addLayerOnTop(new ErrorDialogCreator().createWithException(exception));
		}
	}
}
