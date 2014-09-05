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
package de.modagile.generator.android.templates.adapter

import com.google.inject.Inject
import de.modagile.generator.android.templates.ModagileFolderConstants
import de.modagile.metamodel.app.MobileApp
import de.modagile.metamodel.app.domain.ListBinding
import de.modagile.metamodel.app.ui.DisplayElement
import info.multiplatform.generator.java.templates.PackageInfo
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.xtext.generator.IFileSystemAccess

class ListAdapterViewHolderImplTemplate {
    
    @Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
    @Inject extension ListAdapterTemplate listAdapter

    def public generateListAdapterViewHolderImpl(IFileSystemAccess fsa, ListBinding listBinding, PackageInfo packageInfo, MobileApp m){
        fsa.generateFile(packageInfo.packageDir + "/adapter/" + listBinding.listElement.name+"ListAdapterViewHolderImpl.java", ModagileFolderConstants::SRC_MAN, generateListAdapterViewHolderImplCode(listBinding, packageInfo, m));
    }
    
    def private generateListAdapterViewHolderImplCode(ListBinding listBinding, PackageInfo packageInfo, MobileApp m){
        val String inheritedClass = listBinding.listElement.name + "ListAdapterViewHolder";
        var String className = inheritedClass+"Impl";
        var Set<String> imports  = new HashSet<String>();
        listAdapter.getListAdapterImports(imports, packageInfo, listBinding);
        imports.add(packageInfo.packageName+".adapter."+listBinding.listElement.name+"ListAdapter."+inheritedClass);
        '''
        «javaUtilities.statementGenerated("AppConstantsTemplate")»
        «javaUtilities.packageStatement(packageInfo.packageName+".adapter")»
        «javaUtilities.importStatements(imports)»
        «javaUtilities.classDecl(className, inheritedClass, null, false)»
        «FOR EAttribute attribute: listBinding.domainReference.EReferenceType.EAttributes»
            «val DisplayElement displayElementInBindingForAttribute = javaUtilities.getRelatedStringBoundDisplayElementForListAttribute(m.bindingRepository, attribute, listBinding)»
            «IF displayElementInBindingForAttribute != null»
                «generateRequiredAttributeHookMethod(listBinding, attribute, displayElementInBindingForAttribute)»
           «ENDIF»
        «ENDFOR»
        }     
        '''
    }
    
    def private generateRequiredAttributeHookMethod(ListBinding listBinding, EAttribute attribute, DisplayElement displayElement){
        '''
         public «getViewHolderRequiredMethodReturnType(displayElement)» «listAdapter.getViewHolderAttributeMethodForDisplayElement(attribute, displayElement)»(«listBinding.domainReference.EReferenceType.name» «listBinding.domainReference.EReferenceType.name.toLowerCase», Context context){
             //TODO add app specific behavoir manually and remove the default return value.
             return  «listBinding.domainReference.EReferenceType.name.toLowerCase».get«attribute.name.toFirstUpper»();
         }
            
        '''
    }
    
    
}