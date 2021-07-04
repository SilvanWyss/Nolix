//package declaration
package ch.nolix.system.databaseapplication.main;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.containerwidget.Grid;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.dialog.YesNoDialogCreator;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.ButtonRole;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.LabelRole;
import ch.nolix.element.gui.widget.Widget;
import ch.nolix.system.database.databaseadapter.EntitySet;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.Reference;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//class
public final class EntitySetSession extends HeaderedSession {
	
	//attribute
	private final String entitySetName;
	
	//constructor
	public EntitySetSession(final String entitySetName) {
		
		super(entitySetName);
		
		this.entitySetName = entitySetName;
	}
	
	//method
	@Override
	protected LinkedList<Button> createLinkButtons() {
		return
		LinkedList.withElements(
			new Button()
			.setRole(ButtonRole.LINK_BUTTON)
			.setText("Home")
			.setLeftMouseButtonPressAction(this::openHomeSession)		
		);
	}
	
	//method
	@Override
	protected VerticalStack createSubSubContentWidget() {
		return
		new VerticalStack()
		.add(
			new HorizontalStack()
			.add(
				new Button()
				.setRole(ButtonRole.CREATE_BUTTON)
				.setText("Create")
				.setLeftMouseButtonPressAction(this::openCreateEntitySession)
			),
			createEntitiesGrid()
		);
	}

	//method
	private Widget<?, ?> createEntitiesGrid() {
		
		final var entitiesGrid = new Grid().setRole(ContainerRole.MAINT_CONTAINER);
		
		//Sets the header of the entities grid.
		int columnIndex = 2;
		for (final var c : getRefEntitySet().getColumns()) {
			if (c.getParametrizedDataType().getPropertyKind() == PropertyType.VALUE) {
				
				entitiesGrid.setWidget(
					1,
					columnIndex,
					new Label()
					.setRole(LabelRole.LEVEL2_HEADER)
					.setText(c.getHeader())
				);
				
				columnIndex++;
			}
			
			if (c.getParametrizedDataType().getPropertyKind() == PropertyType.REFERENCE) {
				
				entitiesGrid.setWidget(
					1,
					columnIndex,
					new Label()
					.setRole(LabelRole.LEVEL2_HEADER)
					.setText(c.getHeader())
				);
				
				columnIndex++;
			}
		}
			
		//Fills up the entities into the entities grid.
		int rowIndex = 2;
		for (final var e : getRefEntitySet().getRefEntities()) {
			
			entitiesGrid.setWidget(
				rowIndex,
				1,
				new Button()
				.setRole(ButtonRole.LINK_BUTTON)
				.setText("Open")
				.setLeftMouseButtonPressAction(
					() -> openEntitySession(entitySetName, e.getId())
				)
			);
			
			columnIndex = 2;
			
			//Inserts the Propertys of the Entitys into the grid in the in the same order as the columns.
			final var properties = e.getRefProperties();
			for (final var p : getRefEntitySet().getColumns().to(c -> properties.getRefFirst(pr -> pr.hasSameHeaderAs(c)))) {	
				switch (p.getPropertyKind()) {
					case VALUE:
						
						entitiesGrid.setWidget(rowIndex, columnIndex, new Label().setText(p.toString()));
						
						columnIndex++;
						
						break;
					case REFERENCE:						
						
						@SuppressWarnings("unchecked")
						final var referenceProperty = (Reference<Entity>)p;
						
						entitiesGrid.setWidget(
							rowIndex,
							columnIndex,
							new Button()
							.setRole(ButtonRole.LINK_BUTTON)
							.setText(String.valueOf(referenceProperty.getRefEntity().getShortDescription()))
							.setLeftMouseButtonPressAction(
								() ->
								openEntitySession(
									referenceProperty.getRefEntitySetOfReferencedEntities().getName(),
									referenceProperty.getRefEntity().getId()
								)
							)
						);
						
						columnIndex++;
						
						break;
					default:
						break;
				}
			}
			
			if (!e.isReferenced()) {
			
				entitiesGrid.setWidget(
					rowIndex,
					columnIndex,
					new Button()
					.setRole(ButtonRole.DELETE_BUTTON)
					.setText("Delete")
					.setLeftMouseButtonPressAction(() -> openDeleteEntityDialogFor(e))
				);
			}
			
			rowIndex++;
		}
		
		return entitiesGrid;
	}
	
	//method
	private EntitySet<Entity> getRefEntitySet() {
		return getRefDatabaseAdapter().getRefEntitySet(entitySetName);
	}
	
	//method
	private void delete(final Entity entity) {
		entity.setDeleted();
		getRefDatabaseAdapter().saveChanges();
	}
	
	//method
	private void openCreateEntitySession() {
		push(new CreateEntitySession(entitySetName));
	}
	
	//method
	private void openDeleteEntityDialogFor(final Entity entity) {
		getRefGUI()
		.addLayerOnTop(
			new YesNoDialogCreator()
			.createWithYesNoQuestionAndConfirmAction(
				"Do you want to delete the entity " + entity.getShortDescriptionInQuotes() + "?",
				() -> delete(entity)
			)
		);
	}
	
	//method
	private void openEntitySession(final String entitySetName, final long entityId) {
		push(new EntitySession(entitySetName,	entityId));
	}
	
	//method
	private void openHomeSession() {
		push(new HomeSession());
	}
}
