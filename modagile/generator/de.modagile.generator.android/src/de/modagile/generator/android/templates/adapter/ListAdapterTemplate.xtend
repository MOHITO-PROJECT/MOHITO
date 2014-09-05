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
import info.multiplatform.generator.java.helper.Pair
import info.multiplatform.generator.java.templates.PackageInfo
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess

class ListAdapterTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities

	def public generateListAdapter(IFileSystemAccess fsa, ListBinding listBinding, PackageInfo packageInfo, MobileApp m){
 		fsa.generateFile(packageInfo.packageDir + "/adapter/" + listBinding.listElement.name+"ListAdapter.java", ModagileFolderConstants::SRC_GEN, generateListAdapterCode(listBinding, packageInfo, m));
 	}

	def private generateListAdapterCode(ListBinding listBinding, PackageInfo packageInfo, MobileApp m) {
		val String className = listBinding.listElement.name+"ListAdapter";
		var Set<String> imports  = new HashSet<String>();
		var List<String> interfaces  = new ArrayList<String>();
		interfaces.add("LoaderCallbacks<Cursor>")
		var List<Pair<String, String>> entityAttributes = new ArrayList<Pair<String, String>>();
		
		getListAdapterImports(imports, packageInfo, listBinding);
		'''
		«javaUtilities.statementGenerated("AppConstantsTemplate")»
		«javaUtilities.packageStatement(packageInfo.packageName+".adapter")»
        «javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl(className,"CursorAdapter", interfaces, false)»
		private static final String TAG = «className».class.getName();
        private final LayoutInflater mInflater;
        private final Context mContext;
        «javaUtilities.generateAdditionalAttributes(entityAttributes)»
		«generateAdapterConstructor(className)»
		«generateCursorAdapterMethods(listBinding, className, m)»
		«generateLoadManagerMethods(listBinding)»
		«generateAbstractClassViewHolder(className, listBinding, m)»
		}
		'''
	}
	
	def private generateCursorAdapterMethods(ListBinding listBinding, String className, MobileApp m){
	    '''
	    «generateMethodNewView(listBinding, className, m)»
	    «generateMethodBindView(className, listBinding, m)»
	    '''
	}
	
	def private generateAdapterConstructor(String className){
        '''
        public «className»(Context context, boolean autoRequery) {
           super(context, null, autoRequery);
           mContext = context;
           mInflater = LayoutInflater.from(context);
        }
        '''
    }
	
	def private generateMethodNewView(ListBinding listBinding, String className, MobileApp m){
	    var EClass domainReferenceType = listBinding.domainReference.EReferenceType;
	    '''
	    @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View row = mInflater.inflate(R.layout.«listBinding.listElement.name.toLowerCase»row, null);
            «className»ViewHolder holder = new «className»ViewHolderImpl();
            «FOR EAttribute attribute: domainReferenceType.EAttributes»
                «val DisplayElement displayElementInBindingForAttribute = javaUtilities.getRelatedStringBoundDisplayElementForListAttribute(m.bindingRepository, attribute, listBinding)»
                «IF displayElementInBindingForAttribute != null»
                    holder.«attribute.name» = («javaUtilities.getDisplayElementType(displayElementInBindingForAttribute)») row.findViewById(R.id.«javaUtilities.getDisplayElementPrefix(displayElementInBindingForAttribute)+displayElementInBindingForAttribute.name.toFirstUpper»);
                «ENDIF»
            «ENDFOR»
            row.setTag(holder);
            Log.d(TAG, "Row has been cached");
            return row;
        }
	    '''
	}
	
	def private generateMethodBindView(String className, ListBinding listBinding, MobileApp m){
	    '''
	    @Override
	    public void bindView(View view, Context context, Cursor cursor) {
	        
        «className»ViewHolder holder = («className»ViewHolder) view.getTag();

        «listBinding.domainReference.EReferenceType.name» «listBinding.domainReference.EReferenceType.name.toLowerCase» = «listBinding.domainReference.EReferenceType.name»Converter.cursorTo«listBinding.domainReference.EReferenceType.name»(cursor);

        «FOR EAttribute attributeInEntityReference: listBinding.domainReference.EReferenceType.EAttributes»
         «val DisplayElement displayElementInBindingForAttribute = javaUtilities.getRelatedStringBoundDisplayElementForListAttribute(m.bindingRepository, attributeInEntityReference, listBinding)»
            «IF displayElementInBindingForAttribute != null»
                holder.«attributeInEntityReference.name».«getViewHolderAttributeMethodOppositeForDisplayElement(attributeInEntityReference, displayElementInBindingForAttribute)»(holder.«getViewHolderAttributeMethodForDisplayElement(attributeInEntityReference, displayElementInBindingForAttribute)»(«listBinding.domainReference.EReferenceType.name.toLowerCase», context));
             «ENDIF»
        «ENDFOR»
	    }
	    '''
	}
	
	def private generateLoadManagerMethods(ListBinding listBinding){
	    '''
	    «generateMethodOnCreateLoader(listBinding)»
	    «generateMethodOnLoaderReset()»
	    «generateMethodOnLoadFinished()»
	    '''
	}
	
	def private generateMethodOnCreateLoader(ListBinding listBinding){
	    '''
	    @Override
	    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
	       return new CursorLoader(mContext, DBConstants.CONTENT_URI_«listBinding.domainReference.EReferenceType.name.toUpperCase», null, null, null, null);
	    }
	    '''
	}
	def private generateMethodOnLoaderReset(){
	    '''
	    @Override
	    public void onLoaderReset(final Loader<Cursor> loader) {
	       swapCursor(null);
	    }
	    '''
	}
	
	def private generateMethodOnLoadFinished(){
	    '''
	    @Override
	    public void onLoadFinished(final Loader<Cursor> loader, final Cursor cursor) {
	       swapCursor(cursor);
	       notifyDataSetChanged();
	    }
	    '''
	}
	
	def private generateAbstractClassViewHolder(String className, ListBinding listBinding, MobileApp m){
	    '''
	    «javaUtilities.innerClassDecl("public", className+"ViewHolder",null,null,true,true)»
	    «FOR EAttribute attribute: listBinding.domainReference.EReferenceType.EAttributes»
            «val DisplayElement displayElementInBindingForAttribute = javaUtilities.getRelatedStringBoundDisplayElementForListAttribute(m.bindingRepository, attribute, listBinding)»
            «IF displayElementInBindingForAttribute != null»
                protected «javaUtilities.getDisplayElementType(displayElementInBindingForAttribute)» «attribute.name»;
«««         We need a method to implement the behavoir of the DisplayElement manually or should for each DisplayElement a Method be generated?    
                public abstract «getViewHolderRequiredMethodReturnType(displayElementInBindingForAttribute)» «getViewHolderAttributeMethodForDisplayElement(attribute, displayElementInBindingForAttribute)»(«listBinding.domainReference.EReferenceType.name» «listBinding.domainReference.EReferenceType.name.toLowerCase», Context context);
            «ENDIF»
        «ENDFOR»
	    }     
	    '''
	}
	
	def public getListAdapterImports(Set<String> imports, PackageInfo packageInfo, ListBinding listBinding){
	    imports.add("android.app.Activity");
        imports.add("android.content.Context");
        imports.add("android.view.LayoutInflater");
        imports.add("android.view.View");
        imports.add("android.view.ViewGroup");
        imports.add("android.widget.CursorAdapter");
        imports.add("android.widget.TextView");
        imports.add("com.actionbarsherlock.app.SherlockFragmentActivity");
        imports.add("android.app.LoaderManager.LoaderCallbacks");
        imports.add("android.content.Loader");
        imports.add("android.database.Cursor");
        imports.add("android.content.CursorLoader");
        imports.add("android.os.Bundle");
        imports.add("android.util.Log");
        imports.add(packageInfo.packageName+".R");
        imports.add(packageInfo.packageName+".constants.DBConstants");
        imports.add(packageInfo.packageName+".model."+listBinding.domainReference.EType.name);
        imports.add(packageInfo.packageName+".model."+listBinding.domainReference.EType.name+"Converter");
	}
	
	def public getViewHolderAttributeMethodForDisplayElement(EAttribute attribute, DisplayElement displayElement){
	   switch(getViewHolderRequiredMethodReturnType(displayElement)){
          case "String": "getText" + attribute.name.toFirstUpper
          case "boolean": "isChecked"+attribute.name.toFirstUpper
          case "Adress": "setAdress" +attribute.name.toFirstUpper
       }
   
    }
    
    def public getViewHolderAttributeMethodOppositeForDisplayElement(EAttribute attribute, DisplayElement displayElement){
       switch(getViewHolderRequiredMethodReturnType(displayElement)){
          case "String": "setText"
          case "boolean": "setChecked"
          case "Adress": "setAdress"
       }
    }
    
    def public getViewHolderRequiredMethodReturnType(DisplayElement displayElement){
       switch(displayElement.eClass.name){
          case "Button": "String"
          case "Image": "String"
          case "Input": "String"
          case "Label": "String"
          case "CheckBox": "boolean"
          case "DynamicList": ""
          case "LocationPicker": "Adress"
          case "Datepicker": ""
          case "ImageButton": "String"
       }
    }
}