//class
package ch.nolix.system.gui3d.base;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-11
 * @param <S> is the type of the shape of a shape renderer.
 * @param <RO> is the type of the render object of a shape renderer.
 * @param <SRO> is the type of the sub render objects of a shape renderer.
 */
public final class ShapeRenderManager<S extends Shape<S>, RO, SRO> {

	//attribute
	private final S shape;
	private final IShapeRenderer<S, RO, SRO> shapeRenderer;
	private final RO renderObject;
	
	public ShapeRenderManager(final S shape, final IShapeRenderer<S, RO, SRO> shapeRenderer) {
		
		Validator.assertThat(shape).isOfType(Shape.class);
		Validator.assertThat(shapeRenderer).isOfType(IShapeRenderer.class);
		
		this.shape = shape;
		this.shapeRenderer = shapeRenderer;
		
		renderObject = shapeRenderer.createRenderObject();
	}
	
	@SuppressWarnings("unchecked")
	public void addSubRenderObject(Shape<?> shape) {
		shapeRenderer.addSubRenderObject(renderObject, (SRO)shape.getRefShapeRenderManager().renderObject);
	}
	
	@SuppressWarnings("unchecked")
	public void removeSubRenderObject(Shape<?> shape) {
		shapeRenderer.removeSubRenderObject(renderObject, (SRO) shape.getRefShapeRenderManager().renderObject);
	}

	//method
	/**
	 * Renders the shape of this shape render manager.
	 */
	public void render() {
		
		shapeRenderer.render(shape, renderObject);
	}

	public RO getRefRenderObject() {
		return renderObject;
	}
}
