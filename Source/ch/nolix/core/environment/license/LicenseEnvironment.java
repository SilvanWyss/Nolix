/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.environment.license;

import ch.nolix.coreapi.environment.nolixenvironment.NolixDirectoryAndFileCatalog;

/**
 * The {@link LicenseEnvironment} provides information about the Nolix license
 * environment on the local computer. Of the {@link LicenseEnvironment} an
 * instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class LicenseEnvironment {
  public static final String LICENCSE_FILE_EXTENSION = "license";

  /**
   * Prevents that an instance of the {@link LicenseEnvironment} can be created.
   */
  private LicenseEnvironment() {
  }

  /**
   * @return the path of the license folder on the local computer.
   */
  public static String getLocalLicenseFolderPath() {
    return NolixDirectoryAndFileCatalog.NOLIX_LICENSES_DIRECTORY_PATH;
  }
}
