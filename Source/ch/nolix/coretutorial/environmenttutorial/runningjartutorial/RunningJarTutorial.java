package ch.nolix.coretutorial.environmenttutorial.runningjartutorial;

import ch.nolix.core.environment.runningjar.RunningJar;

public final class RunningJarTutorial {
	
	public static void main(String[] args) {
		
		final var resourcePath =
		"ch/nolix/coretutorial/environmenttutorial/runningjartutorialresource/willkommen_und_abschied.txt";
		
		System.out.print(RunningJar.getResource(resourcePath));
	}
	
	private RunningJarTutorial() {}
}
