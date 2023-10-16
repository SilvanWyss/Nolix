//package declaration
package ch.nolix.core.commontype.commontypehelper;

//Java imports
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;

//class
public final class GlobalBufferedImageHelper {

  //static method
  public static BufferedImage fromBytes(final byte[] bytes) {
    try {
      return ImageIO.read(new ByteArrayInputStream(bytes));
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  //static method
  public static BufferedImage fromFile(final String filePath) {
    try {
      return ImageIO.read(new File(filePath));
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  //constructor
  private GlobalBufferedImageHelper() {
  }
}
