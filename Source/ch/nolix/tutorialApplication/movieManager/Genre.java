package ch.nolix.tutorialApplication.movieManager;

import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Property;

public class Genre extends Entity {

	public final Property<String> Name = new Property<String>();
}
