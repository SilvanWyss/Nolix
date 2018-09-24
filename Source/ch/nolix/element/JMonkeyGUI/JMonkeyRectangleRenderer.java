//package declaration
package ch.nolix.element.JMonkeyGUI;

//JMonkey imports
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;

//own import
import ch.nolix.element._3DGUI.Rectangle;

//package-visible class
final class JMonkeyRectangleRenderer
implements IJMonkeyShapeRenderer<Rectangle, Geometry> {

	//method
	public Geometry createRenderObject() {		
		return new Geometry("Quad", new Quad(1.0f, 1.0f));
	}

	//method
	public void render(final Rectangle rectangle, final Geometry geometry) {
		
		geometry.setLocalTranslation(
			rectangle.getXPositionAsFloat(),
			rectangle.getYPositionAsFloat(),
			rectangle.getZPositionAsFloat()
		);
		
		geometry.scale(
			rectangle.getXLengthAsFloat(),
			rectangle.getYLengthAsFloat(),
			1.0f
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
		}
		else {
			
			final var material
			= new Material(JMonkeyHelper.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
			
			final var texture
			= new Texture2D(new AWTLoader().load(rectangle.getDefaultTextureAsBufferedImage(), true));
			
			material.setTexture("ColorMap", texture);	
			
			geometry.setMaterial(material);
		}
	}
}
