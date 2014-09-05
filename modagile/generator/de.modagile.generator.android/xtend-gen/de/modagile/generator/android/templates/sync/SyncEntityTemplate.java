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
package de.modagile.generator.android.templates.sync;

import com.google.inject.Inject;
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.MobileApp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class SyncEntityTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  /**
   * Creates the SuperClass SyncEntity
   */
  public void generateSuperClassSyncEntitiy(final String packagePrefix, final MobileApp m, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "sync/SyncEntity.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "sync");
    CharSequence _generateSuperClassSyncEntityCode = this.generateSuperClassSyncEntityCode(_plus_1, m);
    fsa.generateFile(_plus, outputConfiguration, _generateSuperClassSyncEntityCode);
  }
  
  /**
   * Creates a SyncSubclass for a specific entity
   * For SyncDomainEntities methods see section two in this file
   */
  public void generateSyncDomainEntitiy(final String packagePrefix, final EClass domainEntity, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "sync/Sync");
    String _name = domainEntity.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + ".java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_3 = (_replaceAll + "sync");
    CharSequence _generateSyncDomainEntityCode = this.generateSyncDomainEntityCode(_plus_3, domainEntity);
    fsa.generateFile(_plus_2, outputConfiguration, _generateSyncDomainEntityCode);
  }
  
  public CharSequence generateSuperClassSyncEntityCode(final String packageName, final MobileApp m) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      this.getImports(imports, packageName);
      StringConcatenation _builder = new StringConcatenation();
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateSuperClassSyncEntityClass = this.generateSuperClassSyncEntityClass("SyncEntityTemplate", packageName, imports, m, null, _arrayList);
      _builder.append(_generateSuperClassSyncEntityClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public boolean getImports(final Set<String> imports, final String packageName) {
    boolean _xblockexpression = false;
    {
      imports.add("java.util.ArrayList");
      imports.add("java.util.Collection");
      imports.add("java.util.List");
      imports.add("android.accounts.NetworkErrorException");
      imports.add("android.content.Context");
      imports.add("android.util.Log");
      String _replace = packageName.replace("sync", "manager");
      String _plus = (_replace + ".EntityManager");
      imports.add(_plus);
      String _replace_1 = packageName.replace("sync", "model");
      String _plus_1 = (_replace_1 + ".Entity");
      imports.add(_plus_1);
      String _replace_2 = packageName.replace("sync", "rest");
      String _plus_2 = (_replace_2 + ".BaseRestClient");
      boolean _add = imports.add(_plus_2);
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateSuperClassSyncEntityClass(final String templateName, final String packageName, final Set<String> imports, final MobileApp m, final String inheritance, final List<String> interfaces) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(templateName);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _importStatements = this.javaUtilities.importStatements(imports);
    _builder.append(_importStatements, "");
    _builder.newLineIfNotEmpty();
    CharSequence _classDecl = this.javaUtilities.classDecl("SyncEntity<ENTITY extends Entity>", inheritance, interfaces, true);
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateSyncEntityMethods = this.generateSyncEntityMethods(m);
    _builder.append(_generateSyncEntityMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateSyncEntityMethods(final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateSyncEntityAttributes = this.generateSyncEntityAttributes();
    _builder.append(_generateSyncEntityAttributes, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateSyncEntityConstructor = this.generateSyncEntityConstructor();
    _builder.append(_generateSyncEntityConstructor, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateAbstractMethods = this.generateAbstractMethods();
    _builder.append(_generateAbstractMethods, "");
    _builder.newLineIfNotEmpty();
    CharSequence _sync = this.sync();
    _builder.append(_sync, "");
    _builder.newLineIfNotEmpty();
    CharSequence _removeDeletedEntriesFromServer = this.removeDeletedEntriesFromServer();
    _builder.append(_removeDeletedEntriesFromServer, "");
    _builder.newLineIfNotEmpty();
    CharSequence _createOrUpdateEntries = this.createOrUpdateEntries();
    _builder.append(_createOrUpdateEntries, "");
    _builder.newLineIfNotEmpty();
    CharSequence _deleteOutdatesLocalData = this.deleteOutdatesLocalData();
    _builder.append(_deleteOutdatesLocalData, "");
    _builder.newLineIfNotEmpty();
    CharSequence _createLocally = this.createLocally();
    _builder.append(_createLocally, "");
    _builder.newLineIfNotEmpty();
    CharSequence _updateLocalToRemote = this.updateLocalToRemote();
    _builder.append(_updateLocalToRemote, "");
    _builder.newLineIfNotEmpty();
    CharSequence _updateRemoteToLocal = this.updateRemoteToLocal();
    _builder.append(_updateRemoteToLocal, "");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    CharSequence _pushNewLocalDataToServer = this.pushNewLocalDataToServer();
    _builder.append(_pushNewLocalDataToServer, " ");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateSyncEntityAttributes() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("protected final Context mCtx;");
    _builder.newLine();
    _builder.append("private final String TAG;");
    _builder.newLine();
    _builder.append("protected final EntityManager<ENTITY> entityManager;");
    _builder.newLine();
    _builder.append("protected final BaseRestClient<ENTITY> restClient;");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateSyncEntityConstructor() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Constructor to set up the object instance");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param ctx");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           The Android Context");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public SyncEntity(Context ctx, BaseRestClient<ENTITY> restClient, EntityManager<ENTITY> entityManager) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.TAG = getClass().getSimpleName();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.mCtx = ctx;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.entityManager = entityManager;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (restClient == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.restClient = createRestClient(mCtx);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.restClient = restClient;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence sync() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Synchronizes local and remote data");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @throws NetworkErrorException");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            If there is a communication problem with the server");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public void sync() throws NetworkErrorException {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Log.i(TAG, \"Starting syncing \"+ getClass());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("removeDeletedEntriesFromServer();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("createOrUpdateEntries();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("pushNewLocalDataToServer();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("deleteOutdatedLocalData();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence removeDeletedEntriesFromServer() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("protected void removeDeletedEntriesFromServer() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Collection<ENTITY> l = entityManager.getDeleted();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("List<Long> idsToRemove = new ArrayList<Long>();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// collect IDs of entries that should be deleted");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("for (ENTITY entity : l) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (entity.getDeleted() == 1) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("idsToRemove.add(entity.getId());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// remove the entries 1 by 1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("for (Long id : idsToRemove) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// get a clean instance");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ENTITY entity = entityManager.getById(id);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// remove from server");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("boolean successful = restClient.delete(entity.getUuid(), entity.getRevision());");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (successful) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("// remove locally for good if server side deletion was successful");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("entityManager.delete(entity);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateAbstractMethods() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("protected abstract BaseRestClient<ENTITY> createRestClient(Context ctx);");
    _builder.newLine();
    _builder.append("protected abstract String getShortDescription(ENTITY entity);");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence createOrUpdateEntries() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("protected void createOrUpdateEntries() throws NetworkErrorException {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("List<ENTITY> list = null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// get all entries from the remote server");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list = restClient.getAll();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (Exception e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new NetworkErrorException(\"Network error\" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (list != null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for (ENTITY aRemote : list) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("ENTITY aLocal = entityManager.getByUuid(aRemote.getUuid());");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (aLocal == null) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("// there is no such entry in the local DB, create it");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("createLocally(aRemote);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("Log.d(TAG, \"Considering an update for: \" + getShortDescription(aLocal));");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("long localLastUpdate = aLocal.getLastUpdate();");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("long remoteLastUpdate = aRemote.getLastUpdate();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("// there is an entry in the local DB");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("if (remoteLastUpdate > localLastUpdate) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("updateRemoteToLocal(aRemote, aLocal);");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("} else if (remoteLastUpdate < localLastUpdate) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("updateLocalToRemote(aLocal);");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("} else if (remoteLastUpdate == localLastUpdate) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("Log.d(TAG, \" Already in sync, no update needed\");");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("Log.d(TAG, \"Update finished for this address\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence deleteOutdatesLocalData() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* See if we have entries in the local DB that have a revision (meaning is known to the server), but are not on the");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* server any more (meaning it has been deleted by an other client)");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("protected void deleteOutdatedLocalData() {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Collection<ENTITY> entityListLocal = entityManager.getAll();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("List<ENTITY> entityListRemote = restClient.getAll();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (entityListRemote != null) {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ArrayList<ENTITY> listToDelete = new ArrayList<ENTITY>();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for (ENTITY eLocal : entityListLocal) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (eLocal.getRevision() != null) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("String uuid = eLocal.getUuid();");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("boolean found = false;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("for (ENTITY eRemote : entityListRemote) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("if (eRemote.getUuid().equals(uuid)) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("found = true;");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("if (!found) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("// this is a local address with revision that is not on the server -> delete locally");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("listToDelete.add(eLocal);");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for (ENTITY entity : listToDelete) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("entityManager.delete(entity);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence createLocally() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Create a local address with remote data");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param eRemote");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           The remote data that will be written into the local DB");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("private void createLocally(ENTITY eRemote) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Log.d(TAG, \"Creating new entry in local db: \" + eRemote.getUuid());");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// reset some data for creation");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("eRemote.setId(null);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// execute creating the address");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("entityManager.create(eRemote);");
    _builder.newLine();
    _builder.append("}");
    return _builder;
  }
  
  public CharSequence updateLocalToRemote() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Update remote address with local data");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param eLocal");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           The local address on the client side that will be send to the server");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("private void updateLocalToRemote(ENTITY eLocal) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Log.d(TAG, \" Updating local -> server\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// local data is the newer than the remote data -> push data to server");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int count = 0;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int maxCount = 2;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("while (count < maxCount) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// execute the update and hold on to the new revision we retrieve from the server");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String rev = restClient.update(eLocal);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// handle error when the update goes wrong. It might have happened because the remote data has been updated.");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (rev == null) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("count++;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("//");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("ENTITY eRemote = restClient.getByUuid(eLocal.getUuid());");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (eRemote != null) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("eLocal.setRevision(eRemote.getRevision());");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("continue;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// save the new revision in the local DB");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("eLocal.setRevision(rev);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("entityManager.update(eLocal, true);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("break;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence updateRemoteToLocal() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Update a local address entry with remote data");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param eRemote");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           Remote data that will be written to the local DB");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param eLocal");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           Local address that will be overwritten by the remote data");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("private void updateRemoteToLocal(ENTITY eRemote, ENTITY eLocal) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Log.d(TAG, \" Updating remote -> local\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// server\'s data is newer than ours, write new data to the local DB");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// the id is needed for saving an object, set the ID from local, take the rest from remote");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("eRemote.setId(eLocal.getId());");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// execute the update");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("entityManager.update(eRemote);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence pushNewLocalDataToServer() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* inspect all local entries and push new entries to the server");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("protected void pushNewLocalDataToServer() {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// get all the local entries");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Collection<ENTITY> addressListLocal = entityManager.getAll();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("for (ENTITY eLocal : addressListLocal) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"Considering for server side creation: \" + getShortDescription(eLocal));");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (eLocal.getRevision() == null) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("// revision is null (meaning they have not been pushed to the server so far)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Log.d(TAG, \"Creating address on server. UUID: \" + eLocal.getUuid());");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("String revCreatedByServer = restClient.create(eLocal);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (revCreatedByServer != null) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("eLocal.setRevision(revCreatedByServer);");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("entityManager.update(eLocal, true);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * SyncSubClass methods
   */
  public CharSequence generateSyncDomainEntityCode(final String packageName, final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      String _name = domainEntity.getName();
      String _plus = ("SyncEntity<" + _name);
      String inheritance = (_plus + ">");
      String _name_1 = domainEntity.getName();
      this.getSyncDomainEntityImports(imports, packageName, _name_1);
      StringConcatenation _builder = new StringConcatenation();
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateSyncDomainEntityClass = this.generateSyncDomainEntityClass("SyncEntityTemplate", packageName, imports, domainEntity, inheritance, _arrayList);
      _builder.append(_generateSyncDomainEntityClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public boolean getSyncDomainEntityImports(final Set<String> imports, final String packageName, final String domainEntityName) {
    boolean _xblockexpression = false;
    {
      imports.add("android.content.Context");
      String _replace = packageName.replace("sync", "manager.");
      String _plus = (_replace + domainEntityName);
      String _plus_1 = (_plus + "Manager");
      imports.add(_plus_1);
      String _replace_1 = packageName.replace("sync", "manager.impl.");
      String _plus_2 = (_replace_1 + domainEntityName);
      String _plus_3 = (_plus_2 + "ManagerImpl");
      imports.add(_plus_3);
      String _replace_2 = packageName.replace("sync", "model.");
      String _plus_4 = (_replace_2 + domainEntityName);
      imports.add(_plus_4);
      String _replace_3 = packageName.replace("sync", "rest.");
      String _plus_5 = (_replace_3 + domainEntityName);
      String _plus_6 = (_plus_5 + "RestClient");
      imports.add(_plus_6);
      String _replace_4 = packageName.replace("sync", "rest.");
      String _plus_7 = (_replace_4 + "BaseRestClient");
      imports.add(_plus_7);
      String _replace_5 = packageName.replace("sync", "rest.");
      String _plus_8 = (_replace_5 + "HttpClientFactoryImpl");
      boolean _add = imports.add(_plus_8);
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateSyncDomainEntityClass(final String templateName, final String packageName, final Set<String> imports, final EClass domainEntity, final String inheritance, final List<String> interfaces) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(templateName);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _importStatements = this.javaUtilities.importStatements(imports);
    _builder.append(_importStatements, "");
    _builder.newLineIfNotEmpty();
    String _name = domainEntity.getName();
    String _plus = ("Sync" + _name);
    CharSequence _classDecl = this.javaUtilities.classDecl(_plus, inheritance, interfaces, false);
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    String _name_1 = domainEntity.getName();
    CharSequence _generateSyncDomainEntityMethods = this.generateSyncDomainEntityMethods(_name_1);
    _builder.append(_generateSyncDomainEntityMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateSyncDomainEntityMethods(final String domainEntityName) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateSyncDomainEntityDefaultConstructor = this.generateSyncDomainEntityDefaultConstructor(domainEntityName);
    _builder.append(_generateSyncDomainEntityDefaultConstructor, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateSyncDomainEntityConstructor = this.generateSyncDomainEntityConstructor(domainEntityName);
    _builder.append(_generateSyncDomainEntityConstructor, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateCreateRestClient = this.generateCreateRestClient(domainEntityName);
    _builder.append(_generateCreateRestClient, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateGetShortsDescription = this.generateGetShortsDescription(domainEntityName);
    _builder.append(_generateGetShortsDescription, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateSyncDomainEntityDefaultConstructor(final String domainEntityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Constructor to set up the object instance");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param ctx");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           The Android Context");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public Sync");
    _builder.append(domainEntityName, "");
    _builder.append("(Context ctx, BaseRestClient<");
    _builder.append(domainEntityName, "");
    _builder.append("> restClient, ");
    _builder.append(domainEntityName, "");
    _builder.append("Manager ");
    String _lowerCase = domainEntityName.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("Manager) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("super(ctx, restClient, ");
    String _lowerCase_1 = domainEntityName.toLowerCase();
    _builder.append(_lowerCase_1, "	");
    _builder.append("Manager);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateSyncDomainEntityConstructor(final String domainEntityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Constructor to set up the object instance");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param ctx");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           The Android Context");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public Sync");
    _builder.append(domainEntityName, "");
    _builder.append("(Context ctx, BaseRestClient<");
    _builder.append(domainEntityName, "");
    _builder.append("> restClient) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("this(ctx, restClient, new ");
    _builder.append(domainEntityName, "	");
    _builder.append("ManagerImpl(ctx));");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateCreateRestClient(final String domainEntityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected BaseRestClient<");
    _builder.append(domainEntityName, "");
    _builder.append("> createRestClient(Context ctx) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return new ");
    _builder.append(domainEntityName, "	");
    _builder.append("RestClient(ctx, new HttpClientFactoryImpl());");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateGetShortsDescription(final String domainEntityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected String getShortDescription(");
    _builder.append(domainEntityName, "");
    _builder.append(" entity) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return entity.getUuid();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
