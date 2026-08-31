/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.unionarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import ch.nolix.base.testing.archunit.ArchUnitRuleCatalog;

/**
 * @author Silvan Wyss
 */
final class NonCaptorClassesTest {
  private static final JavaClasses TEST_UNIT = //
  new ClassFileImporter()
    .importPackages("ch.nolix..")
    .that(
      new DescribedPredicate<JavaClass>("non-captor classes") {
        @Override
        public boolean test(final JavaClass javaClass) {
          return !javaClass.getName().matches(".*Captor");
        }
      });

  @Test
  void testCase_nonAnonymousCaptorsAreAbstractOrFinal() {
    // execute & verify
    ArchUnitRuleCatalog.NON_ANONYMOUS_CLASSES_ARE_ABSTRACT_OR_FINAL.check(TEST_UNIT);
  }
}
