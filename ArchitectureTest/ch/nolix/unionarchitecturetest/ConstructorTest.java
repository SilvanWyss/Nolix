/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.unionarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import ch.nolix.base.testing.archunit.ArchUnitRuleCatalog;

/**
 * @author Silvan Wyss
 */
final class ConstructorTest {
  private static final JavaClasses TEST_UNIT = new ClassFileImporter().importPackages("ch.nolix..");

  @Test
  void testCase_publicAndPackageVisibleConstructorsDoNotContainParameters() {
    // execute & verify
    ArchUnitRuleCatalog.PUBLIC_AND_PACKAGE_VISIBLE_CONSTRUCTORS_DO_NOT_CONTAIN_PARAMETERS.check(TEST_UNIT);
  }
}
