//package declaration
package ch.nolix.element.JMonkeyGUI;

//JMonkey imports
import com.jme3.material.Material;
import com.jme3.scene.Geometry;

//own import
import ch.nolix.element._3DGUI.Sphere;

//class
/**
 * A JMonkey sphere renderer is a sphere renderer for JMonkey.
 * A JMonkey sphere renderer is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 70
 */
public final class JMonkeySphereRenderer
implements IJMonkeyShapeRenderer<Sphere, Geometry> {

	//method
	/**
	 * @return a new render object for a sphere from this JMonkey sphere renderer.
	 */
	@Override
	public Geometry createRenderObject() {
					
		//Creates sphere.
		final com.jme3.scene.shape.Sphere sphere
		= new com.jme3.scene.shape.Sphere(100, 100, 1.0f);
		
		//Creates material.
			final Material material =
			new Material(JMonkeyHelper.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
			
			material.setBoolean("UseMaterialColors", true);
		
		//Creates geometry.
        final Geometry geometry = new Geometry("Box", sphere);
        geometry.setMaterial(material);
        
		return geometry;
	}

	//method
	/**
	 * Lets this JMonkey sphere renderer render the given sphere on the given geometry.
	 * 
	 * @param sphere
	 * @param renderObject
	 */
	@Override
	public void render(
		final Sphere sphere,
		final Geometry geometry
	) {
		//Sets the position of the given geometry.
		geometry.setLocalTranslation(
			sphere.getXPositionAsFloat(),
			sphere.getYPositionAsFloat(),
			sphere.getZPositionAsFloat()
		);
		
		//Sets the size of the given geometry.
		geometry.scale(sphere.getDiameterAsFloat());
		
		//Sets the color of the given geometry.
		geometry
		.getMaterial()
		.setColor("Diffuse", JMonkeyColorHelper.createColorRGBA(sphere.getDefaultColor()));
	}
}
