//package declaration
package ch.nolix.common.runningJar;

//Java imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//own import
import ch.nolix.common.wrapperException.WrapperException;

//class
public final class RunningJar {
	
	//static method
	public static String getResource(final String pPackage) {
		final var stringBuilder = new StringBuilder();
		final var inputStream = RunningJar.class.getResourceAsStream("/" + pPackage);
		try (final var bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
			var line = bufferedReader.readLine();
			while (line != null) {
				stringBuilder.append(line).append("\n");
				line = bufferedReader.readLine();
			}
			return stringBuilder.toString();
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//access-reducing constructor
	private RunningJar() {}
}
