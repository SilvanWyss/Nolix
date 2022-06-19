//package declaration
package ch.nolix.system.gui3d.jmonkeygui;

//JMonkey imports
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.system.gui3d.shape.Sphere;

//class
/**
 * A JMonkey sphere renderer is a sphere renderer for JMonkey.
 * A JMonkey sphere renderer is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-11-11
 */
public final class JMonkeySphereRenderer implements IJMonkeyShapeRenderer<Sphere, Geometry> {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addSubRenderObject(final Geometry renderObject, final Spatial subRenderObject) {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "addSubRenderObject");
	}
	
	//method
	/**
	 * @return a new render object for a sphere from this JMonkey sphere renderer.
	 */
	@Override
	public Geometry createRenderObject() {
					
		//Creates sphere.
		final com.jme3.scene.shape.Sphere sphere
		= new com.jme3.scene.shape.Sphere(100, 100, 1.0F);
		
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
	 * {@inheritDoc}
	 */
	@Override
	public void removeSubRenderObject(final Geometry renderObject, final Spatial subRederObject) {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "removeSubRenderObject");
	}

	//method
	/**
	 * Lets this JMonkey sphere renderer render the given sphere on the given geometry.
	 * 
	 * @param sphere
	 * @param geometry
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
