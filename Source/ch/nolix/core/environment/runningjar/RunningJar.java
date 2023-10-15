//package declaration
package ch.nolix.core.environment.runningjar;

//Java imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;

//class
public final class RunningJar {

  // static method
  public static String getResource(final String path) {
    final var stringBuilder = new StringBuilder();
    final var inputStream = RunningJar.class.getResourceAsStream("/" + path);
    try (
        final var bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
      var line = bufferedReader.readLine();
      while (line != null) {
        stringBuilder.append(line).append("\n");
        line = bufferedReader.readLine();
      }
      return stringBuilder.toString();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  // static method
  public static byte[] getResourceAsBytes(final String path) {
    final var inputStream = RunningJar.class.getResourceAsStream("/" + path);
    try {
      return inputStream.readAllBytes();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  // constructor
  private RunningJar() {
  }
}
