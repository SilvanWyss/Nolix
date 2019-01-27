//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.container.List;
import ch.nolix.core.specificationAPI.Configurable;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.bases.ConfigurableElement;
import ch.nolix.element.geometry._2DPoint;
import ch.nolix.element.geometry._3DPoint;

//abstract class
/**
 * The position of a shape
 * is its smallest (!) position in a left-hand (!) coordination system.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 350
 * @param <S> The type of a shape.
 */
public abstract class Shape<S extends Shape<S>> extends ConfigurableElement<S> {
	
	//default value
	public static final _3DPoint DEFAULT_POSITION = new _3DPoint(0.0, 0.0, 0.0);
	
	//attribute
	private _3DPoint position = DEFAULT_POSITION;
	
	//optional attributes
	private _3DGUI<?> GUI;
	private ShapeRenderManager<S, ?, ?> shapeRenderManager;
	
	//method
	/**
	 * @return true if this shape belongs to a GUI.
	 */
	public final boolean belongsToAGUI() {
		return (GUI != null);
	}
	
	//method
	/**
	 * @return the attributes of this shape.
	 */
	@Override
	public List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		final List<DocumentNode> attributes = super.getAttributes();
		
		attributes.addAtEnd(position.getSpecification());
		
		return attributes;
	}
	
	//method
	/**
	 * @return the position of this shape.
	 */
	public final _3DPoint getPosition() {
		return position;
	}
	
	//method
	/**
	 * @return the configurable elements of this shape.
	 */
	@Override
	public final ReadContainer<Configurable<?>> getRefConfigurables() {
		return new ReadContainer<Configurable<?>>(getRefShapes());
	}
	
	//method
	/**
	 * @return the render object of this shape.
	 * @throws UnexistingAttributeException if this shape does not have a render object.
	 */
	@SuppressWarnings("unchecked")
	public final <RO> RO getRefRenderObject() {
		return (RO)getRefShapeRenderManager().getRefRenderObject();
	}
	
	//abstract method
	/**
	 * @return the shapes of this shapes.
	 */
	public abstract ReadContainer<Shape<?>> getRefShapes();
	
	//method
	/**
	 * @return the x-position of this shape.
	 */
	public final double getXPosition() {
		return position.getX();
	}
	
	//method
	/**
	 * @return the x-position of this shape as float.
	 */
	public final float getXPositionAsFloat() {
		return position.getXAsFloat();
	}
	
	//method
	/**
	 * @return the y-position of this shape.
	 */
	public final double getYPosition() {
		return position.getY();
	}
	
	//method
	/**
	 * @return the xy-position of this shape as float.
	 */
	public final float getYPositionAsFloat() {
		return position.getYAsFloat();
	}
	
	//method
	/**
	 * @return the z-position of this shape.
	 */
	public final double getZPosition() {
		return position.getZ();
	}
	
	//method
	/**
	 * @return the z-position of this shape as float.
	 */
	public final float getZPositionAsFloat() {
		return position.getZAsFloat();
	}
	
	//method
	/**
	 * @return true if this shape has the given role.
	 */
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * Renders this shape.	
	 */
	public final void render() {
		getRefShapeRenderManager().render();
	}
	
	//method
	/**
	 * Renders this shape recursively.
	 */
	public final void renderRecursively() {
		getRefShapes().forEach(s -> s.renderRecursively());
		render();
	}
	
	//method
	/**
	 * Resets this shape.
	 * 
	 * @return this shape.
	 */
	@Override
	public S reset() {
		
		setPosition(DEFAULT_POSITION);
		
		//Calls method of the base class.
		return super.reset();
	}
	
	//method
	/**
	 * Sets the GUI this shape will belong to.
	 * 
	 * @param GUI
	 * @return this shape.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final S setGUI(_3DGUI<?> GUI) {
		
		//Checks if the given GUI is not null.
		Validator.suppose(GUI).thatIsNamed("GUI").isNotNull();
		
		//Checks if this shape does not belong already to a GUI.
		if (belongsToAGUI()) {
			throw new InvalidArgumentException(this, "belongs already to a GUI");
		}
		
		this.shapeRenderManager = new ShapeRenderManager(this, GUI.getShapeRendererFor(this));
		
		//Sets the GUI of this shape.
		this.GUI = GUI;
		
		getRefShapes().forEach(s -> s.setGUI(GUI));
		getRefShapes().forEach(s -> getRefShapeRenderManager().addSubRenderObject(s));
		
		return asConcreteType();
	}
	
	/**
	 * Sets the position of the current {@link Shape} with z-position=0.0.
	 * 
	 * @param position
	 * @return the current {@link Shape}.
	 */
	public final S setPosition(final _2DPoint position) {
		
		//Calls other method.
		return setPosition(position.to3DPoint());
	}
	
	//method
	/**
	 * Sets the position of this frame.
	 * 
	 * @param position
	 * @return this frame.
	 * @throws NullArgumentException if the given position is null.
	 */
	public final S setPosition(final _3DPoint position) {
		
		//Checks if the given position is not null.
		Validator.suppose(position).thatIsNamed("position").isNotNull();
		
		//Sets the position of this frame.
		this.position = position;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the position of the current {@link Shape} with z-position=0.0.
	 * 
	 * @param xPosition
	 * @param xPosition
	 * @return the current {@link Shape}.
	 */
	public final S setPosition(
		final double xPosition,
		final double yPosition
	) {
		
		//Calls other method.
		return setPosition(xPosition, yPosition, 0.0);
	}
	
	//method
	/**
	 * Sets the position of this shape.
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @param zPosition
	 * @return this shape.
	 */
	public final S setPosition(
		final double xPosition,
		final double yPosition,
		final double zPosition
	) {
		return setPosition(new _3DPoint(xPosition, yPosition, zPosition));
	}
	
	//method
	/**
	 * Translates this shape.
	 * 
	 * @param xDelta
	 * @param yDelta
	 * @param zDelta
	 * @return this shape.
	 */
	public final S translate(
		final double xDelta,
		final double yDelta,
		final double zDelta
	) {
		return setPosition(
			getXPosition() + xDelta,
			getYPosition() + yDelta,
			getZPosition() + zDelta
		);
	}
	
	//method
	/**
	 * @return the GUI this shape belongs to.
	 * @throws InvalidArgumentException if this shape does not belong to a GUI.
	 */
	protected final _3DGUI<?> getRefGUI() {
		
		supposeBelongsToAGUI();
		
		return GUI;
	}

	//method
	/**
	 * @return the shape render manager of this shape.
	 * @throws UnexistingAttributeException if this shape does not have a shape render manager.
	 */
	protected final ShapeRenderManager<S, ?, ?> getRefShapeRenderManager() {
		
		//Checks if this shape has a shape render manager.
		supposeHasShapeRenderManager();
		
		return shapeRenderManager;
	}
	
	//method
	/**
	 * @return true if this shape has a shape render manager.
	 */
	protected final boolean hasShapeRenderManager() {
		return (shapeRenderManager != null);
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if this shape belongs to a GUI. 
	 */
	protected final void supposeBelongsNotToAGUI() {
		if (belongsToAGUI()) {
			throw new InvalidArgumentException(this, "belongs to a GUI");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if this shape belongs not to a GUI. 
	 */
	protected final void supposeBelongsToAGUI() {
		if (!belongsToAGUI()) {
			throw new InvalidArgumentException(this, "belongs not to a GUI");
		}
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException if this shape does not have a shape render manager.
	 */
	private void supposeHasShapeRenderManager() {
		if (!hasShapeRenderManager()) {
			throw new UnexistingAttributeException(this, ShapeRenderManager.class);
		}
	}
}
