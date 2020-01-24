package ch.nolix.commonTutorial.runningJarTutorial;

import ch.nolix.common.runningJar.RunningJar;

public final class RunningJarTutorial {
	
	public static void main(String[] args) {
		System.out.print(
			RunningJar.getResource("ch/nolix/commonTutorial/RunningJarTutorial/Willkommen_und_Abschied.txt")
		);
	}
	
	private RunningJarTutorial() {}
}
