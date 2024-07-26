//package declaration
package ch.nolix.system.graphic.image;

//Java imports
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

//Javax imports
import javax.imageio.ImageIO;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;

//class
public final class BufferedImageTool {

  //method
  public BufferedImage fromBytes(final byte[] bytes) {
    try {
      return ImageIO.read(new ByteArrayInputStream(bytes));
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  //method
  public BufferedImage fromFile(final String filePath) {
    try {
      return ImageIO.read(new File(filePath));
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }
}
