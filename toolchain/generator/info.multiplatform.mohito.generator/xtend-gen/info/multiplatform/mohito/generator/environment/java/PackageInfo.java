/**
 * Copyright (c) 2012-2014 MOHITO Project
 * 
 * Licensed under the EUPL V.1.1
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package info.multiplatform.mohito.generator.environment.java;

/**
 * Contains information about a package and its directory.
 */
@SuppressWarnings("all")
public class PackageInfo {
  /**
   * Separator for different sections of a package.
   */
  public final static String SEPARATOR_PACKAGE = ".";
  
  /**
   * Separator for different sections of a directory.
   */
  public final static String SEPARATOR_DIRECTORY = "/";
  
  /**
   * Name of the package in Java notation.
   */
  private final String packageName;
  
  /**
   * Directory of the package.
   */
  private final String packageDir;
  
  public PackageInfo(final String packageName, final String packageDir) {
    this.packageName = packageName;
    this.packageDir = packageDir;
  }
  
  public String getPackageDir() {
    return this.packageDir;
  }
  
  public String getPackageName() {
    return this.packageName;
  }
  
  /**
   * Returns a new package info after appending the given name to the package information.
   * Does not modify the current package info.
   * @param Name to append.
   * @return New package info.
   */
  public PackageInfo append(final String name) {
    PackageInfo _xblockexpression = null;
    {
      String newPackageName = null;
      boolean _equals = this.packageName.equals("");
      if (_equals) {
        newPackageName = name;
      } else {
        String _plus = (this.packageName + PackageInfo.SEPARATOR_PACKAGE);
        String _plus_1 = (_plus + name);
        newPackageName = _plus_1;
      }
      String newPackageDir = null;
      boolean _equals_1 = this.packageDir.equals("");
      if (_equals_1) {
        newPackageDir = name;
      } else {
        String _plus_2 = (this.packageDir + PackageInfo.SEPARATOR_DIRECTORY);
        String _plus_3 = (_plus_2 + name);
        String _plus_4 = (_plus_3 + PackageInfo.SEPARATOR_DIRECTORY);
        newPackageDir = _plus_4;
      }
      PackageInfo _packageInfo = new PackageInfo(newPackageName, newPackageDir);
      _xblockexpression = (_packageInfo);
    }
    return _xblockexpression;
  }
  
  /**
   * Returns a new package info after stripping the last section from the path and package.
   * Does not modify the current package info.
   * @return New package info.
   */
  public PackageInfo strip() {
    PackageInfo _xblockexpression = null;
    {
      String newPackageName = "";
      String newPackageDir = "";
      int _lastIndexOf = this.packageName.lastIndexOf(PackageInfo.SEPARATOR_PACKAGE);
      boolean _greaterThan = (_lastIndexOf > 0);
      if (_greaterThan) {
        int _lastIndexOf_1 = this.packageName.lastIndexOf(PackageInfo.SEPARATOR_PACKAGE);
        String _substring = this.packageName.substring(0, _lastIndexOf_1);
        newPackageName = _substring;
        int _lastIndexOf_2 = this.packageDir.lastIndexOf(PackageInfo.SEPARATOR_DIRECTORY);
        String _substring_1 = this.packageDir.substring(0, _lastIndexOf_2);
        newPackageDir = _substring_1;
      }
      PackageInfo _packageInfo = new PackageInfo(newPackageName, newPackageDir);
      _xblockexpression = (_packageInfo);
    }
    return _xblockexpression;
  }
}
