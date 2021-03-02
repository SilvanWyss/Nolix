//class
package ch.nolix.element.gui3d;

import ch.nolix.common.errorcontrol.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 50
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
