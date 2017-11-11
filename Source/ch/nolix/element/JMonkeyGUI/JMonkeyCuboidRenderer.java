//package declaration
package ch.nolix.element.JMonkeyGUI;

//JMonkey imports
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

//own import
import ch.nolix.element._3DGUI.Cuboid;

//class
/**
 * A JMonkey cuboid renderer is a cuboid renderer for JMonkey.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 70
 */
public final class JMonkeyCuboidRenderer
implements IJMonkeyShapeRenderer<Cuboid, Geometry> {

	//method
	/**
	 * @return a new render object for the cuboid of this JMonkey cuboid renderer.
	 */
	public Geometry createRenderObject() {
		
		//Creates box.
		final Box box = new Box(1, 1, 1);
        
		//Creates material.		
			final Material material =
			new Material(JMonkeyHelper.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
			
			material.setBoolean("UseMaterialColors", true);
		
		//Creates geometry.
        final Geometry geometry = new Geometry("Box", box);
        geometry.setMaterial(material);
		
		return geometry;
	}

	//method
	/**
	 * Lets this JMonkey cuboid renderer
	 * render the given cuboid on the given geometry.
	 * 
	 * @param cuboid
	 * @param geometry
	 */
	public void render(final Cuboid cuboid, final Geometry geometry) {
		
		//Sets the position of the given render object.
		geometry.setLocalTranslation(
			cuboid.getXPositionAsFloat(),
			cuboid.getYPositionAsFloat(),
			cuboid.getZPositionAsFloat()
		);
		
		//Sets the size of the given render object.
		geometry.scale(
			cuboid.getXLengthAsFloat(),
			cuboid.getYLengthAsFloat(),
			cuboid.getZLengthAsFloat()
		);
		
		//Sets the color of the given render object.
		geometry
		.getMaterial()
		.setColor("Diffuse", JMonkeyColorHelper.createColorRGBA(cuboid.getColor()));
	}
}
