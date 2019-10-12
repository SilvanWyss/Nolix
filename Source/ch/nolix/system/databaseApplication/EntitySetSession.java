//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.common.containers.List;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.containerWidgets.ContainerRole;
import ch.nolix.element.containerWidgets.Grid;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.ButtonRole;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.LabelRole;
import ch.nolix.element.widgets.VerticalStack;
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
		return new List<>(
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
								new Button(String.valueOf(referenceProperty.getRefEntity().getId()))
								.setRole(ButtonRole.LinkButton)
								.setLeftMouseButtonPressCommand(
									() -> openEntitySession(referenceProperty.getRefEntitySetOfReferencedEntities().getName(), referenceProperty.getRefEntity().getId())
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
		push(new CreateEntitySession(entitySetName));
	}
	
	//method
	private void openDeleteEntitySession(final long entityId) {
		push(new DeleteEntitySession(entitySetName, entityId));
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
