//abstract class
package ch.nolix.element._3DGUI;

import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 10
 * @param <S> The type of the shape of a shape renderer.
 * @param <RO> The type of the render object of a shape renderer.
 */
final class ShapeRenderManager<S extends Shape<S>, RO, SRO> {

	//attribute
	private final S shape;
	private final IShapeRenderer<S, RO, SRO> shapeRenderer;
	private final RO renderObject;
	
	public ShapeRenderManager(final S shape, final IShapeRenderer<S, RO, SRO> shapeRenderer) {
		
		Validator.suppose(shape).isInstanceOf(Shape.class);
		Validator.suppose(shapeRenderer).isInstanceOf(IShapeRenderer.class);
		
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
