//package declaration
package ch.nolix.element.gui3d;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.configuration.ConfigurableElement;
import ch.nolix.element.elementapi.IConfigurableElement;
import ch.nolix.element.geometry.Point2D;
import ch.nolix.element.geometry.Point3D;

//class
/**
 * The position of a {@link Shape} is a position in a left-hand (!) coordination system.
 * The position of a {@link Shape} is the position that is nearest to the origin.
 * 
 * @author Silvan Wyss
 * @month 2017-11-11
 * @lines 360
 * @param <S> The type of a {@link Shape}.
 */
public abstract class Shape<S extends Shape<S>> extends ConfigurableElement<S> {
	
	//constant
	public static final Point3D DEFAULT_POSITION = new Point3D(0.0, 0.0, 0.0);
	
	//attribute
	private Point3D position = DEFAULT_POSITION;
	
	//optional attributes
	private GUI3D<?> mGUI;
	private ShapeRenderManager<S, ?, ?> shapeRenderManager;
	
	//method
	/**
	 * @return true if the current {@link Shape} belongs to a GUI.
	 */
	public final boolean belongsToAGUI() {
		return (mGUI != null);
	}
	
	//method
	/**
	 * @return the attributes of the current {@link Shape}.
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final LinkedList<Node> attributes = super.getAttributes();
		
		attributes.addAtEnd(position.getSpecification());
		
		return attributes;
	}
	
	//method
	/**
	 * @return the position of the current {@link Shape}.
	 */
	public final Point3D getPosition() {
		return position;
	}
	
	//method
	/**
	 * @return the configurable elements of the current {@link Shape}.
	 */
	@Override
	public final ReadContainer<IConfigurableElement<?>> getSubConfigurables() {
		return ReadContainer.forIterable(getRefShapes().asContainerWithElementsOfEvaluatedType());
	}
	
	//method
	/**
	 * @param <RO> is the type of the returned render object.
	 * @return the render object of the current {@link Shape}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Shape} does not have a render object.
	 */
	@SuppressWarnings("unchecked")
	public final <RO> RO getRefRenderObject() {
		return (RO)getRefShapeRenderManager().getRefRenderObject();
	}
	
	//method declaration
	/**
	 * @return the shapes of the current {@link Shape}s.
	 */
	public abstract ReadContainer<Shape<?>> getRefShapes();
	
	//method
	/**
	 * @return the x-position of the current {@link Shape}.
	 */
	public final double getXPosition() {
		return position.getX();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link Shape} as float.
	 */
	public final float getXPositionAsFloat() {
		return position.getXAsFloat();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link Shape}.
	 */
	public final double getYPosition() {
		return position.getY();
	}
	
	//method
	/**
	 * @return the xy-position of the current {@link Shape} as float.
	 */
	public final float getYPositionAsFloat() {
		return position.getYAsFloat();
	}
	
	//method
	/**
	 * @return the z-position of the current {@link Shape}.
	 */
	public final double getZPosition() {
		return position.getZ();
	}
	
	//method
	/**
	 * @return the z-position of the current {@link Shape} as float.
	 */
	public final float getZPositionAsFloat() {
		return position.getZAsFloat();
	}
	
	//method
	/**
	 * @return true if the current {@link Shape} has the given role.
	 */
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * Renders the current {@link Shape}.	
	 */
	public final void render() {
		getRefShapeRenderManager().render();
	}
	
	//method
	/**
	 * Renders the current {@link Shape} recursively.
	 */
	public final void renderRecursively() {
		getRefShapes().forEach(Shape::renderRecursively);
		render();
	}
	
	//method
	/**
	 * Sets the GUI the current {@link Shape} will belong to.
	 * 
	 * @param pGUI
	 * @return the current {@link Shape}.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final S setGUI(GUI3D<?> pGUI) {
		
		//Asserts that the given GUI is not null.
		Validator.assertThat(pGUI).thatIsNamed("GUI").isNotNull();
		
		//Asserts that the current Shape does not belong already to a GUI.
		if (belongsToAGUI()) {
			throw new InvalidArgumentException(this, "belongs already to a GUI");
		}
		
		this.shapeRenderManager = new ShapeRenderManager(this, pGUI.getShapeRendererFor(this));
		
		//Sets the GUI of the current Shape.
		mGUI = pGUI;
		
		getRefShapes().forEach(s -> s.setGUI(pGUI));
		getRefShapes().forEach(s -> getRefShapeRenderManager().addSubRenderObject(s));
		
		return asConcrete();
	}
	
	/**
	 * Sets the position of the current {@link Shape} with z-position=0.0.
	 * 
	 * @param position
	 * @return the current {@link Shape}.
	 */
	public final S setPosition(final Point2D position) {
		
		//Calls other method.
		return setPosition(position.to3DPoint());
	}
	
	//method
	/**
	 * Sets the position of this frame.
	 * 
	 * @param position
	 * @return this frame.
	 * @throws ArgumentIsNullException if the given position is null.
	 */
	public final S setPosition(final Point3D position) {
		
		//Asserts that the given position is not null.
		Validator.assertThat(position).thatIsNamed("position").isNotNull();
		
		//Sets the position of this frame.
		this.position = position;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the position of the current {@link Shape} with z-position=0.0.
	 * 
	 * @param xPosition
	 * @param yPosition
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
	 * Sets the position of the current {@link Shape}.
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @param zPosition
	 * @return the current {@link Shape}.
	 */
	public final S setPosition(
		final double xPosition,
		final double yPosition,
		final double zPosition
	) {
		return setPosition(new Point3D(xPosition, yPosition, zPosition));
	}
	
	//method
	/**
	 * Translates the current {@link Shape}.
	 * 
	 * @param xDelta
	 * @param yDelta
	 * @param zDelta
	 * @return the current {@link Shape}.
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
	 * @throws InvalidArgumentException if the current {@link Shape} belongs to a GUI. 
	 */
	protected final void assertBelongsNotToAGUI() {
		if (belongsToAGUI()) {
			throw new InvalidArgumentException(this, "belongs to a GUI");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Shape} belongs not to a GUI. 
	 */
	protected final void assertBelongsToAGUI() {
		if (!belongsToAGUI()) {
			throw new InvalidArgumentException(this, "belongs not to a GUI");
		}
	}
	
	//method
	/**
	 * @return the GUI the current {@link Shape} belongs to.
	 * @throws InvalidArgumentException if the current {@link Shape} does not belong to a GUI.
	 */
	protected final GUI3D<?> getRefGUI() {
		
		assertBelongsToAGUI();
		
		return mGUI;
	}

	//method
	/**
	 * @return the shape render manager of the current {@link Shape}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Shape} does not have a shape render manager.
	 */
	protected final ShapeRenderManager<S, ?, ?> getRefShapeRenderManager() {
		
		//Asserts that the current Shape has a shape render manager.
		assertHasShapeRenderManager();
		
		return shapeRenderManager;
	}
	
	//method
	/**
	 * @return true if the current {@link Shape} has a shape render manager.
	 */
	protected final boolean hasShapeRenderManager() {
		return (shapeRenderManager != null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetStage2() {
		
		setPosition(DEFAULT_POSITION);
		
		resetStage3();
	}
	
	//method declaration
	/**
	 * Resets the current {@link Shape}.
	 */
	protected abstract void resetStage3();
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Shape} does not have a shape render manager.
	 */
	private void assertHasShapeRenderManager() {
		if (!hasShapeRenderManager()) {
			throw new ArgumentDoesNotHaveAttributeException(this, ShapeRenderManager.class);
		}
	}
}
