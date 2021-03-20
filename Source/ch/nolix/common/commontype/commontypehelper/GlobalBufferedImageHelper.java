//package declaration
package ch.nolix.common.commontype.commontypehelper;

//Java imports
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//own import
import ch.nolix.common.errorcontrol.exception.WrapperException;

//class
public final class GlobalBufferedImageHelper {
	
	//static method
	public static BufferedImage fromFile(final String filePath) {
		try {
			return ImageIO.read(new File(filePath));
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//visibility-reduced constructor
	private GlobalBufferedImageHelper() {}
}
