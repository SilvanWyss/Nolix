//package declaration
package ch.nolix.system.gui3d.jmonkeygui;

//JMonkey imports
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.system.gui3d.planarshape.Rectangle;

//class
final class JMonkeyRectangleRenderer implements IJMonkeyShapeRenderer<Rectangle, Geometry> {
	
	//method
	@Override
	public void addSubRenderObject(final Geometry renderObject, final Spatial subRenderObject) {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "addSubRenderObject");
	}
	
	//method
	@Override
	public Geometry createRenderObject() {
		return new Geometry("Quad", new Quad(1.0F, 1.0F));
	}
	
	//method
	@Override
	public void removeSubRenderObject(final Geometry renderObject, final Spatial subRederObject) {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "removeSubRenderObject");
	}
	
	//method
	@Override
	public void render(final Rectangle rectangle, final Geometry geometry) {
		
		geometry.setLocalTranslation(
			rectangle.getXPositionAsFloat(),
			rectangle.getYPositionAsFloat(),
			rectangle.getZPositionAsFloat()
		);
		
		geometry.scale(
			rectangle.getXLengthAsFloat(),
			rectangle.getYLengthAsFloat(),
			1.0F
		);
		
		if (!rectangle.hasDefaultTexture()) {
			
			final var material =
			new Material(JMonkeyHelper.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
			
			material.setBoolean("UseMaterialColors", true);
							
			material.setColor(
				"Diffuse",
				JMonkeyColorHelper.createColorRGBA(rectangle.getDefaultColor())
			);
			
			geometry.setMaterial(material);
		} else {
			
			final var material
			= new Material(JMonkeyHelper.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
			
			final var texture
			= new Texture2D(new AWTLoader().load(rectangle.getDefaultTextureAsBufferedImage(), true));
			
			material.setTexture("ColorMap", texture);
			
			geometry.setMaterial(material);
		}
	}
}
