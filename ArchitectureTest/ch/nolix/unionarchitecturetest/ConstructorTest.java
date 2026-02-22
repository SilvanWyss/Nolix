/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.unionarchitecturetest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;

import ch.nolix.base.testing.archunit.ArchUnitRuleCatalog;

/**
 * @author Silvan Wyss
 */
final class ConstructorTest {
  @Disabled
  @Test
  void testCase_publicConstructorsDoNotContainParameters() {
    //setup
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix...");

    //execution & verification
    ArchUnitRuleCatalog.PUBLIC_CONSTRUCTORS_DO_NOT_CONTAIN_PARAMETERS.check(testUnit);
  }
}
