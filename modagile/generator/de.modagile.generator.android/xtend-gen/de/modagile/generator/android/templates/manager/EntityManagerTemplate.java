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
package de.modagile.generator.android.templates.manager;

import com.google.inject.Inject;
import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.java.JavaUtils;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class EntityManagerTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  /**
   * Creates the SuperClass SyncEntity
   */
  public void generateEntityManager(final IFileSystemAccess fsa, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "manager/EntityManager.java");
    CharSequence _generateEntityManagerCode = this.generateEntityManagerCode(packageInfo);
    fsa.generateFile(_plus, ModagileFolderConstants.SRC_GEN, _generateEntityManagerCode);
  }
  
  private CharSequence generateEntityManagerCode(final PackageInfo packageInfo) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      this.getImports(imports, packageInfo);
      StringConcatenation _builder = new StringConcatenation();
      String _packageName = packageInfo.getPackageName();
      String _plus = (_packageName + ".manager");
      CharSequence _generateEntityManagerClass = this.generateEntityManagerClass(_plus, imports, null);
      _builder.append(_generateEntityManagerClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private boolean getImports(final Set<String> imports, final PackageInfo packageInfo) {
    boolean _xblockexpression = false;
    {
      imports.add("java.io.Serializable");
      imports.add("java.util.Collection");
      imports.add("android.net.Uri");
      String _packageName = packageInfo.getPackageName();
      String _plus = (_packageName + ".task.AsyncCallback");
      boolean _add = imports.add(_plus);
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateEntityManagerClass(final String packageName, final Set<String> imports, final String inheritance) {
    StringConcatenation _builder = new StringConcatenation();
    Class<? extends EntityManagerTemplate> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _importStatements = this.javaUtilities.importStatements(imports);
    _builder.append(_importStatements, "");
    _builder.newLineIfNotEmpty();
    CharSequence _interfaceDecl = this.javaUtilities.interfaceDecl("EntityManager<T extends Serializable>", inheritance);
    _builder.append(_interfaceDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateInterfaceMethods = this.generateInterfaceMethods();
    _builder.append(_generateInterfaceMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateInterfaceMethods() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _byId = this.getById();
    _builder.append(_byId, "");
    _builder.newLineIfNotEmpty();
    CharSequence _byId_Asynchronous = this.getById_Asynchronous();
    _builder.append(_byId_Asynchronous, "");
    _builder.newLineIfNotEmpty();
    CharSequence _byUri = this.getByUri();
    _builder.append(_byUri, "");
    _builder.newLineIfNotEmpty();
    CharSequence _byUri_Asynchronous = this.getByUri_Asynchronous();
    _builder.append(_byUri_Asynchronous, "");
    _builder.newLineIfNotEmpty();
    CharSequence _byUuid = this.getByUuid();
    _builder.append(_byUuid, "");
    _builder.newLineIfNotEmpty();
    CharSequence _byUuid_Asynchronous = this.getByUuid_Asynchronous();
    _builder.append(_byUuid_Asynchronous, "");
    _builder.newLineIfNotEmpty();
    CharSequence _all = this.getAll();
    _builder.append(_all, "");
    _builder.newLineIfNotEmpty();
    CharSequence _all_Asynchronous = this.getAll_Asynchronous();
    _builder.append(_all_Asynchronous, "");
    _builder.newLineIfNotEmpty();
    CharSequence _updateEntity = this.updateEntity();
    _builder.append(_updateEntity, "");
    _builder.newLineIfNotEmpty();
    CharSequence _updateEntity_Asynchronous = this.updateEntity_Asynchronous();
    _builder.append(_updateEntity_Asynchronous, "");
    _builder.newLineIfNotEmpty();
    CharSequence _create = this.create();
    _builder.append(_create, "");
    _builder.newLineIfNotEmpty();
    CharSequence _create_Asynchronous = this.create_Asynchronous();
    _builder.append(_create_Asynchronous, "");
    _builder.newLineIfNotEmpty();
    CharSequence _delete = this.delete();
    _builder.append(_delete, "");
    _builder.newLineIfNotEmpty();
    CharSequence _delete_Asynchronous = this.delete_Asynchronous();
    _builder.append(_delete_Asynchronous, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence getById() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Get an entity by its id");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param entityId");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           - id of the entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return the entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract T getById(final Long entityId);");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getById_Asynchronous() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Get an entity by its id asynchron.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param entityId");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - id of the entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param callback");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - callback for result");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract void getById(final Long entityId, final AsyncCallback<T> callback);");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getByUri() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Get an entity by its Uri");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param uri");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           - Uri pointing to the entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return an entity, or null if no entity was found with the given Uri");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract T getByUri(final Uri uri);");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getByUri_Asynchronous() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Get an entity by its Uri asynchron.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param uri");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - Uri pointing to the entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param callback");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - callback for result");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract void getByUri(final Uri uri, final AsyncCallback<T> callback);");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getByUuid() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Get an entity by its uuid");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param uuid");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           - UUID of the entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return an entity or null");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract T getByUuid(final String uuid);");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getByUuid_Asynchronous() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Get an entity by its uuid asynchron.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param uuid");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - UUID of the entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param callback");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - callback for result");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract void getByUuid(final String uuid, final AsyncCallback<T> callback);");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getAll() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Get all entities");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return - a collection of all entities, collection could be empty");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract Collection<T> getAll();");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getAll_Asynchronous() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Get all entities asynchron.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param callback");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - callback for result");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract void getAll(final AsyncCallback<Collection<T>> callback);");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence updateEntity() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Update an entity.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           - entity to update");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract T update(T entity);");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence updateEntity_Asynchronous() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Update an entity asynchron.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - entity to update");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param callback");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - callback for result");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract void update(T entity, final AsyncCallback<T> callback);");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence create() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Create an entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract Uri create(final T entity);");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence create_Asynchronous() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Create an entity asynchron.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - entity to create.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param callback");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - callback for result.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract void create(final T entity, final AsyncCallback<Uri> callback);");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence delete() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Delete an entity from the database");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract boolean delete(final T entity);");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence delete_Asynchronous() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Delete an entity from the database asynchron.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - entity to delete.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param callback");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            - callback for result.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract void delete(final T entity, final AsyncCallback<Boolean> callback);");
    _builder.newLine();
    return _builder;
  }
}
