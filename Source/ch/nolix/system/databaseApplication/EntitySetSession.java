//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.common.container.LinkedList;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.containerWidget.Grid;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.LabelRole;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.system.databaseAdapter.EntitySet;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;
import ch.nolix.system.entity.Reference;

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
		return new LinkedList<>(
			new Button("Home")
			.setRole(ButtonRole.LinkButton)
			.setLeftMouseButtonPressAction(() -> openHomeSession())		
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
				.setLeftMouseButtonPressAction(() -> openCreateEntitySession())
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
				if (c.getDataType().getPropertyKind() == PropertyKind.VALUE) {
					
					entitiesGrid.setWidget(
						1,
						columnIndex,
						new Label(c.getHeader())
						.setRole(LabelRole.Level2Header)
					);
					
					columnIndex++;
				}
				
				if (c.getDataType().getPropertyKind() == PropertyKind.REFERENCE) {
					
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
					.setLeftMouseButtonPressAction(
						() -> openEntitySession(entitySetName, e.getId())
					)
				);
				
				columnIndex = 2;
				for (final var p : e.getRefProperties()) {
					
					switch (p.getPropertyKind()) {
						case VALUE:
							
							entitiesGrid.setWidget(rowIndex, columnIndex, new Label(p.toString()));
							
							columnIndex++;
							
							break;
						case REFERENCE:						
							
							@SuppressWarnings("unchecked")
							final var referenceProperty = (Reference<Entity>)p;
							
							entitiesGrid.setWidget(
								rowIndex,
								columnIndex,
								new Button(String.valueOf(referenceProperty.getRefEntity().getId()))
								.setRole(ButtonRole.LinkButton)
								.setLeftMouseButtonPressAction(
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
						.setLeftMouseButtonPressAction(
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
