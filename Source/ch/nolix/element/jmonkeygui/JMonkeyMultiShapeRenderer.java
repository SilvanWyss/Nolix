//package declaration
package ch.nolix.element.jmonkeygui;

//JMonkey import
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import ch.nolix.element.gui3d.shape.MultiShape;

//class
/**
 * A Jmonkey multi shape renderer is a multi shape renderer for JMonkey.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 10
 */
public final class JMonkeyMultiShapeRenderer
implements IJMonkeyShapeRenderer<MultiShape, Node> {
	
	//method
	/**
	 * Lets this JMonkey multi shape renderer
	 * add the given sub render object to the given render object.
	 * 
	 * @param renderObject
	 * @param subRenderObject
	 */
	@Override
	public void addSubRenderObject(
		final Node renderObject,
		final Spatial subRenderObject
	) {
		renderObject.attachChild(subRenderObject);
	}

	//method
	/**
	 * @return a new render object for the multi shape of this JMonkey multi shape renderer.
	 */
	@Override
	public Node createRenderObject() {
		return new Node();
	}

	//method
	/**
	 * 
	 * 
	 * @param multiShape
	 */
	@Override
	public void render(final MultiShape multiShape, final Node node) {
		node.setLocalTranslation(
			multiShape.getXPositionAsFloat(),
			multiShape.getYPositionAsFloat(),
			multiShape.getZPositionAsFloat()
		);
	}
}
