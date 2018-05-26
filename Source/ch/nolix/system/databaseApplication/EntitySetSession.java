//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntitySet;
import ch.nolix.core.databaseAdapter.ReferenceProperty;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.ButtonRole;
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.GUI.Grid;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.LabelRole;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.GUI.Widget;
import ch.nolix.primitive.validator2.Validator;

//class
public final class EntitySetSession extends HeaderedSession {
	
	//attribute
	private final String entitySetName;
	
	//constructor
	public EntitySetSession(
		final DatabaseApplicationContext databaseApplicationContext,
		final String entitySetName
	) {
		
		super(databaseApplicationContext, entitySetName);
		
		Validator.suppose(
			getRefDatabaseAdapter().containsEntitySet(entitySetName)
		);
		
		this.entitySetName = entitySetName;
	}
	
	//method
	public void OpenCreateEntity() {
		getRefClient().setSession(
			new CreateEntitySession(getRefContext(), entitySetName)
		);
	}
	
	//method
	public void OpenDeleteEntitySession(final String entityId) {
		getRefClient().setSession(
			new DeleteEntitySession(getRefContext(), entitySetName, Integer.valueOf(entityId))	
		);
	}
	
	//method
	public void OpenEntitySession(final String entitySetName, final String entityId) {
		getRefClient().setSession(
			new EntitySession(
				getRefContext(),
				entitySetName,
				Integer.valueOf(entityId)
			)
		);
	}
	
	//method
	public void OpenHomeSession() {
		getRefClient().setSession(new HomeSession(getRefContext()));
	}
	
	//method
	protected List<Button> createLinkButtons() {
		return new List<Button>(
			new Button("Home")
			.setRole(ButtonRole.LinkButton)
			.setLeftMouseButtonPressCommand("OpenHomeSession")		
		);
	}
	
	//method
	protected VerticalStack createSubSubContentWidget() {
		return
		new VerticalStack(
			new HorizontalStack(
				new Button("Create")
				.setRole(ButtonRole.CreateButton)
				.setLeftMouseButtonPressCommand("OpenCreateEntity")
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
					.setLeftMouseButtonPressCommand("OpenEntitySession(" + entitySetName + "," + e.getId() +")")
				);
				
				columnIndex = 2;
				for (final var p : e.getRefProperties()) {
					
					switch (p.getPropertyKind()) {
						case DATA:
							
							entitiesGrid.setWidget(rowIndex, columnIndex, new Label(p.toString()));
							
							columnIndex++;
							
							break;
						case REFERENCE:						
							
							final var referenceProperty = (ReferenceProperty<Entity>)p;
							
							entitiesGrid.setWidget(
								rowIndex,
								columnIndex,
								new Button(String.valueOf(referenceProperty.getEntity().getId()))
								.setRole(ButtonRole.LinkButton)
								.setLeftMouseButtonPressCommand(
									"OpenEntitySession("
									+ referenceProperty.getReferencedEntitySet().getName()
									+ ","
									+ referenceProperty.getEntity().getId()
									+ ")"
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
						.setLeftMouseButtonPressCommand("OpenDeleteEntitySession(" + e.getId() + ")")
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
}
