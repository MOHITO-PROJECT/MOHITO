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
package info.multiplatform.mohito.generator.environment.java

/**
* Contains information about a package and its directory.
*/
class PackageInfo {
    /** Separator for different sections of a package. */
    public static final val String SEPARATOR_PACKAGE = "."
    /** Separator for different sections of a directory. */
    public static final val String SEPARATOR_DIRECTORY = "/"

    /** Name of the package in Java notation. */
    val String packageName

    /** Directory of the package. */
    val String packageDir

    new(String packageName, String packageDir) {
        this.packageName = packageName
        this.packageDir = packageDir
    }

    def getPackageDir() {
        packageDir
    }

    def getPackageName() {
        packageName
    }

    /**Returns a new package info after appending the given name to the package information.
    * Does not modify the current package info.
    * @param Name to append.
    * @return New package info.
    */
    def PackageInfo append(String name) {
    	var String newPackageName;
    	if (packageName.equals("")) {
    		newPackageName = name;
    	} else {
    		newPackageName = this.packageName + SEPARATOR_PACKAGE + name 
    	}
    	var String newPackageDir;
    	if (packageDir.equals("")) {
    		newPackageDir = name;
    	} else {
    		newPackageDir = this.packageDir + SEPARATOR_DIRECTORY + name + SEPARATOR_DIRECTORY
    	}
        new PackageInfo(newPackageName, newPackageDir);
    }

    /**Returns a new package info after stripping the last section from the path and package.
    * Does not modify the current package info.
    * @return New package info.
    */
    def PackageInfo strip() {
        var String newPackageName = ""
        var String newPackageDir = ""
        if (packageName.lastIndexOf(SEPARATOR_PACKAGE) > 0) {
            newPackageName = packageName.substring(0, packageName.lastIndexOf(SEPARATOR_PACKAGE))
            newPackageDir = packageDir.substring(0, packageDir.lastIndexOf(SEPARATOR_DIRECTORY))
        }
        new PackageInfo(newPackageName, newPackageDir);
    }
}