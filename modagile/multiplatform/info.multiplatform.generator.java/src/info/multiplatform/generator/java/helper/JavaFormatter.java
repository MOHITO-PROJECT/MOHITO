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
package info.multiplatform.generator.java.helper;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.WorkingCopyOwner;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.internal.core.DefaultWorkingCopyOwner;
import org.eclipse.text.edits.TextEdit;

/**
 * Applied to IJavaProjects for formatting according to JDT formatting rules. 
 */
@SuppressWarnings("restriction")
public class JavaFormatter {

	/**
	 * Formats all compilation units (.java files) in a project. 
	 * 
	 * @param javaProject 
	 * @throws JavaModelException 
	 */
	public void formatAll(IJavaProject javaProject) throws JavaModelException {
		IPackageFragment[] packages = null;
		packages = javaProject.getPackageFragments();

		for (IPackageFragment mypackage : packages) {
			if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
				formatPackage(mypackage);
			}
		}
	}

	private void formatPackage(IPackageFragment fragment) throws JavaModelException {
		for (ICompilationUnit unit : fragment.getCompilationUnits()) {
			formatCompilationUnit(unit);
		}
	}

	
	private void formatCompilationUnit(ICompilationUnit unit) throws JavaModelException {
		WorkingCopyOwner owner = DefaultWorkingCopyOwner.PRIMARY;
		ICompilationUnit workingCopy = unit.getWorkingCopy(owner, null);
		
		CodeFormatter formatter = ToolFactory.createCodeFormatter(null);
		ISourceRange range = workingCopy.getSourceRange();
		TextEdit indent_edit = formatter.format(CodeFormatter.K_COMPILATION_UNIT, 
				workingCopy.getSource(), range.getOffset(), range.getLength(), 0, null);
		if (indent_edit != null)
			workingCopy.applyTextEdit(indent_edit, null);
		workingCopy.reconcile(ICompilationUnit.NO_AST, false, null, null);
	    
	    // Commit changes
	    workingCopy.commitWorkingCopy(false, null);
	    
	    // Destroy working copy
	    workingCopy.discardWorkingCopy();
	}
}
