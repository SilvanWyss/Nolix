package ch.nolix.corearchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class CoreArchitectureTest {

  @Test
  void testCase_chnolixcore_package() {

    final var rule = //
    ArchRuleDefinition
      .classes()
      .that()
      .resideInAPackage("ch.nolix.core..")
      .should()
      .onlyDependOnClassesThat()
      .resideInAnyPackage("ch.nolix.core..", "ch.nolix.coreapi..", "io.netty..", "java..", "javax..");

    final var testUnit = new ClassFileImporter().importPackages("ch.nolix..");

    rule.check(testUnit);
  }
}
