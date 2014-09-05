/**
 * Copyright (c) 2012-2014 ModAgile Mobile (http://www.modagile-mobile.de/) and others.
 * 
 * Licensed under Eclipse Public License - v 1.0
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package info.multiplatform.generator.java.templates;

/**
 * Contains information about the root package and its dir
 */
@SuppressWarnings("all")
public class PackageInfo {
  private final String packageName;
  
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
}
