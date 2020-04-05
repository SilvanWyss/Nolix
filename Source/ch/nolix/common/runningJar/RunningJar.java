//package declaration
package ch.nolix.common.runningJar;

//Java imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ch.nolix.common.wrapperException.WrapperException;

//class
public final class RunningJar {
	
	//static method
	public static String getResource(final String pPackage) {
		final var stringBuilder = new StringBuilder();
		final var inputStream = RunningJar.class.getResourceAsStream("/" + pPackage);
		final var bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			var line = bufferedReader.readLine();
			while (line != null) {
				stringBuilder.append(line).append("\n");
				line = bufferedReader.readLine();
			}
		}
		catch (final IOException IOException) {
			throw new WrapperException(IOException);
		}
		return stringBuilder.toString();
	}
	
	//access-reducing constructor
	private RunningJar() {}
}
