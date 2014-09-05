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
package de.modagile.generator.android.templates.configs;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;

/**
 * Produces the necessary classpath file for Java.
 */
@SuppressWarnings("all")
public class ProjectPropertiesTemplate {
  public void generateProjectProperties(final IFileSystemAccess fsa) {
    CharSequence _projectProjectPropertiesCode = this.projectProjectPropertiesCode();
    fsa.generateFile("project.properties", IFileSystemAccess.DEFAULT_OUTPUT, _projectProjectPropertiesCode);
  }
  
  public void generateProjectPropertiesWithSherlock(final IFileSystemAccess fsa) {
    CharSequence _projectProjectPropertiesCodeWithSherlock = this.projectProjectPropertiesCodeWithSherlock();
    fsa.generateFile("project.properties", IFileSystemAccess.DEFAULT_OUTPUT, _projectProjectPropertiesCodeWithSherlock);
  }
  
  private CharSequence projectProjectPropertiesCode() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("target=Google Inc.:Google APIs:14");
    _builder.newLine();
    _builder.append("android.library=false");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence projectProjectPropertiesCodeWithSherlock() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("target=Google Inc.:Google APIs:14");
    _builder.newLine();
    _builder.append("android.library=false");
    _builder.newLine();
    _builder.append("android.library.reference.1=../com.actionbarsherlock");
    _builder.newLine();
    return _builder;
  }
}
