//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.ContainerRole;
import ch.nolix.element.widget.Grid;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.LabelRole;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.element.widget.Widget;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.EntitySet;
import ch.nolix.system.databaseAdapter.Reference;

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
	protected List<Button> createLinkButtons() {
		return new List<Button>(
			new Button("Home")
			.setRole(ButtonRole.LinkButton)
			.setLeftMouseButtonPressCommand(() -> openHomeSession())		
		);
	}
	
	//method
	@Override
	protected VerticalStack createSubSubContentWidget() {
		return
		new VerticalStack(
			new HorizontalStack(
				new Button("Create")
				.setRole(ButtonRole.CreateButton)
				.setLeftMouseButtonPressCommand(() -> openCreateEntitySession())
			),
			createEntitiesGrid()
		);
	}

	//method
	@SuppressWarnings({ "incomplete-switch", "unchecked" })
	private Widget<?, ?> createEntitiesGrid() {
		
		final var entitiesGrid = new Grid().setRole(ContainerRole.MainContainer);
		
		//Sets the header of the entities grid.
			int columnIndex = 2;
			for (final var c : getRefEntitySet().getColumns()) {
				if (c.isDataColumn()) {
					
					entitiesGrid.setWidget(
						1,
						columnIndex,
						new Label(c.getHeader())
						.setRole(LabelRole.Level2Header)
					);
					
					columnIndex++;
				}
				
				if (c.isReferenceColumn()) {
					
					entitiesGrid.setWidget(
						1,
						columnIndex,
						new Label(c.getHeader())
						.setRole(LabelRole.Level2Header)
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
					new Button("Open")
					.setRole(ButtonRole.LinkButton)
					.setLeftMouseButtonPressCommand(
						() -> openEntitySession(entitySetName, e.getId())
					)
				);
				
				columnIndex = 2;
				for (final var p : e.getRefProperties()) {
					
					switch (p.getPropertyKind()) {
						case DATA:
							
							entitiesGrid.setWidget(rowIndex, columnIndex, new Label(p.toString()));
							
							columnIndex++;
							
							break;
						case REFERENCE:						
							
							final var referenceProperty = (Reference<Entity>)p;
							
							entitiesGrid.setWidget(
								rowIndex,
								columnIndex,
								new Button(String.valueOf(referenceProperty.getEntity().getId()))
								.setRole(ButtonRole.LinkButton)
								.setLeftMouseButtonPressCommand(
									() -> openEntitySession(referenceProperty.getReferencedEntitySet().getName(), referenceProperty.getEntity().getId())
								)
							);
							
							columnIndex++;
							
							break;
					}
				}
				
				if (!e.isReferenced()) {
				
					entitiesGrid.setWidget(
						rowIndex,
						columnIndex,
						new Button("Delete")
						.setRole(ButtonRole.DeleteButton)
						.setLeftMouseButtonPressCommand(
							() -> openDeleteEntitySession(e.getId())
						)
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
	private void openCreateEntitySession() {
		getParentClient().pushSession(
			new CreateEntitySession(entitySetName),
			() -> initialize()
		);
	}
	
	//method
	private void openDeleteEntitySession(final long entityId) {
		getParentClient().pushSession(
			new DeleteEntitySession(entitySetName, entityId)	
		);
	}
	
	//method
	private void openEntitySession(final String entitySetName, final long entityId) {
		getParentClient().pushSession(
			new EntitySession(
				entitySetName,
				entityId
			)
		);
	}
	
	//method
	private void openHomeSession() {
		getParentClient().pushSession(new HomeSession());
	}
}
