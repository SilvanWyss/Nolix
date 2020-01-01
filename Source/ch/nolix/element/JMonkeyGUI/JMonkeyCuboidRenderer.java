//package declaration
package ch.nolix.element.JMonkeyGUI;

//JMonkey imports
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;

//own import
import ch.nolix.element.shapes.BaseCube;

//class
/**
 * A JMonkey cuboid renderer is a cuboid renderer for JMonkey.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 90
 */
public final class JMonkeyCuboidRenderer implements IJMonkeyShapeRenderer<BaseCube, Geometry> {
	
	//method
	/**
	 * @return a new render object for the cuboid of this JMonkey cuboid renderer.
	 */
	@Override
	public Geometry createRenderObject() {
		
		//Creates box.
		final Box box = new Box(1, 1, 1);
		
		//Creates geometry.
		final Geometry geometry = new Geometry("Box", box);
		
		return geometry;
	}
	
	//method
	/**
	 * Lets this JMonkey cuboid renderer
	 * render the given cuboid on the given geometry.
	 * 
	 * @param baseCube
	 * @param geometry
	 */
	@Override
	public void render(final BaseCube baseCube, final Geometry geometry) {
		
		//Sets the position to the given geometry.
		geometry.setLocalTranslation(
			baseCube.getXPositionAsFloat(),
			baseCube.getYPositionAsFloat(),
			baseCube.getZPositionAsFloat()
		);
		
		//Sets the size to the given geometry.
		geometry.scale(
			baseCube.getXLengthAsFloat(),
			baseCube.getYLengthAsFloat(),
			baseCube.getZLengthAsFloat()
		);
		
		//Handles the case that the given cuboid does not have a default texture.
		if (!baseCube.hasDefaultTexture()) {
			
			//Sets the color to the given geometry.
				final var material =
				new Material(JMonkeyHelper.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
				
				material.setBoolean("UseMaterialColors", true);
								
				material.setColor(
					"Diffuse",
					JMonkeyColorHelper.createColorRGBA(baseCube.getDefaultColor())
				);
				
				geometry.setMaterial(material);
		}
		
		//Handles the case that the given cuboid has a default texture.
		else {
									
			//Sets the texture to the given geometry.
				final var material
				= new Material(JMonkeyHelper.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
				
				final var texture
				= new Texture2D(new AWTLoader().load(baseCube.getRefDefaultTexture().toBufferedImage(),true));
				
				material.setTexture("ColorMap", texture);
				
				geometry.setMaterial(material);
		}
	}
}
