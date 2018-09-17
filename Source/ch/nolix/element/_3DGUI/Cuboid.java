//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity.MutableOptionalProperty;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.element.FPNData.Length;
import ch.nolix.element.FPNData.Width;
import ch.nolix.element.core.PositiveFloatingPointNumber;
import ch.nolix.element.image.Image;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 230
 */
public final class Cuboid extends BaseShape<Cuboid> {

	//default values	
	public static final double DEFAULT_X_LENGTH = 1.0;
	public static final double DEFAULT_Y_LENGTH = 1.0;
	public static final double DEFAULT_Z_LENGTH = 1.0;
	
	//constants
	private static final String X_LENGTH_HEADER = "XLength";
	private static final String Y_LENGTH_HEADER = "YLength";
	private static final String Z_LENGTH_HEADER = "ZLength";
	
	//attribute
	private final MutableProperty<PositiveFloatingPointNumber> xLength =
	new MutableProperty<PositiveFloatingPointNumber>(
		X_LENGTH_HEADER,
		xl -> setXLength(xl.getValue()),
		s -> PositiveFloatingPointNumber.createFromSpecification(s),
		xl -> xl.getSpecification()
	);
	
	//attribute
	private final MutableProperty<PositiveFloatingPointNumber> yLength =
	new MutableProperty<PositiveFloatingPointNumber>(
		Y_LENGTH_HEADER,
		yl -> setYLength(yl.getValue()),
		s -> PositiveFloatingPointNumber.createFromSpecification(s),
		yl -> yl.getSpecification()
	);
	
	//attribute
	private final MutableProperty<PositiveFloatingPointNumber> zLength =
	new MutableProperty<PositiveFloatingPointNumber>(
		Z_LENGTH_HEADER,
		zl -> setZLength(zl.getValue()),
		s -> PositiveFloatingPointNumber.createFromSpecification(s),
		zl -> zl.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<Image> texture =
	new MutableOptionalProperty<Image>(
		PascalCaseNameCatalogue.TEXTURE,
		t -> setTexture(t),
		s -> Image.createFromSpecification(s),
		t -> t.getSpecification()
	);
	
	//constructor
	/**
	 * Creates a new cuboid.
	 */
	public Cuboid() {
		resetConfiguration();
	}
	
	//method
	/**
	 * @return the texture of this cuboid.
	 * @throws UnexistingAttributeException if this cuboid has no texture.
	 */
	public Image getRefTexture() {
		return texture.getValue();
	}
	
	//method
	/**
	 * @return the x-length of this cuboid.
	 */
	public double getXLength() {
		return xLength.getValue().getValue();
	}
	
	//method
	/**
	 * @return the x-length of this cuboid as float.
	 */
	public float getXLengthAsFloat() {
		return (float)getXLength();
	}
	
	//method
	/**
	 * @return the y-length of this cuboid.
	 */
	public double getYLength() {
		return yLength.getValue().getValue();
	}
	
	//method
	/**
	 * @return the y-length of thus cuboid as float.
	 */
	public float getYLengthAsFloat() {
		return (float)getYLength();
	}
	
	//method
	/**
	 * @return the z-length of this cuboid.
	 */
	public double getZLength() {
		return zLength.getValue().getValue();
	}
	
	//method
	/**
	 * @return the z-length of this cuboid as float.
	 */
	public float getZLengthAsFloat() {
		return (float)getZLength();
	}
	
	//method
	/**
	 * @return true if this cuboid has a texture.
	 */
	public boolean hasTexture() {
		return texture.containsAny();
	}
	
	//method
	/**
	 * Removes the texture of this cuboid.
	 * 
	 * @return this cuboid.
	 */
	public Cuboid removeTexture() {
		
		texture.clear();
		
		return this;
	}
	
	//method
	/**
	 * Resets this cuboid.
	 * 
	 * @return this cuboid.
	 */
	public Cuboid reset() {
		
		setXLength(DEFAULT_X_LENGTH);
		setZLength(DEFAULT_Y_LENGTH);
		setYLength(DEFAULT_Z_LENGTH);
		
		removeTexture();
		
		//Calls method of the base class.
		return super.reset();
	}
	
	//method
	/**
	 * @param texture
	 * @return this cuboid.
	 * @throws NullArgumentException if the given texture is not an instance.
	 */
	public Cuboid setTexture(final Image texture) {
		
		this.texture.setValue(texture);
		
		return this;
	}
	
	//method
	/**
	 * Sets the x-length of this cuboid.
	 * 
	 * @param xLength
	 * @return this cuboid.
	 * @throws NonPositiveArgumentException
	 * if the given x-length is not positive.
	 */
	public Cuboid setXLength(final double xLength) {
		
		this.xLength.setValue(new Length(xLength));
		
		return this;
	}
	
	//method
	/**
	 * Sets the y-length of this cuboid.
	 * 
	 * @param yLength
	 * @return this cuboid.
	 * @throws NonPositiveArgumentException
	 * if the given y-length is not positive.
	 */
	public Cuboid setYLength(final double yLength) {
		
		this.yLength.setValue(new Width(yLength));
		
		return this;
	}
	
	//method
	/**
	 * Sets the z-length of this cuboid.
	 * 
	 * @param zLength
	 * @return this cuboid.
	 * @throws NonPositiveArgumentException
	 * if the given z-length is not positive.
	 */
	public Cuboid setZLength(final double zLength) {
		
		this.zLength.setValue(new PositiveFloatingPointNumber(zLength));
		
		return this;
	}
}
