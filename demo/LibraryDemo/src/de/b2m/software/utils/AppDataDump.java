/** 
 * Copyright (c) 2012-2014 B2M Software AG
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
package de.b2m.software.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

/** 
 * @author egailus, theuser (B2M Software AG)
 *
 */
public class AppDataDump {

	static final String PACKAGE_NAME = "de.b2m.software";
	static final String BACKUP_FOLDER = "//dump//";
	
	
	public AppDataDump() {		
	}

	
	public static void dumpDatabasesToExternalMemory(Context context) {
		FileInputStream	in = null;
		FileOutputStream out = null;
		FileChannel src = null;
		FileChannel dst = null;
		
		try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
            	String[] dbList = context.databaseList();
            	for (String dbName : dbList) {
                    String currentDBPath = "//data//"+ PACKAGE_NAME +"//databases//";
                    String backupDBPath = BACKUP_FOLDER;
                    
                    File backUpFolder = new File(sd, backupDBPath);
                    if(!backUpFolder.exists()) {
                    	backUpFolder.mkdir();
                    }
                    
                    File currentDB = new File(data, currentDBPath + dbName);
                    File backupDB = new File(sd, backupDBPath + dbName);

                    in = new FileInputStream(currentDB);
                    src = in.getChannel();
                    
                    if(backupDB.exists()) {
                    	backupDB.delete();
                    }
                    backupDB.createNewFile();
                    
                    out = new FileOutputStream(backupDB);
                    dst = out.getChannel();
                    
                    dst.transferFrom(src, 0, src.size());
                                        
                    Toast.makeText(context, backupDB.toString(), Toast.LENGTH_SHORT).show();	
				}
            }
        } 
		catch (Exception e) {
			e.printStackTrace();
        }
		finally {
            try {
            	if(src != null)
            		src.close();
            	
            	if(in != null)
            		in.close();
				
            	if(dst != null)
            		dst.close();
            	
            	if(out != null)
            		out.close();	
			} 
            catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void importDatabase(Context context) {
		FileInputStream	in = null;
		FileOutputStream out = null;
		FileChannel src = null;
		FileChannel dst = null;
		
		try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canRead()) {
                String targetDBPath = "//data//"+ PACKAGE_NAME +"//databases//";
                String importDBPath = BACKUP_FOLDER;
                String dBName = "hereIAm.db";
               
                File targetDB = new File(data, targetDBPath + dBName);
                File importDB = new File(sd, importDBPath + dBName);

                if(!importDB.exists()) {
                	Toast.makeText(context, "Not able to import database: " + importDB.toString(), Toast.LENGTH_SHORT).show();
                	return;
                }
                
                in = new FileInputStream(importDB);
                src = in.getChannel();
                
                if(targetDB.exists()) {
                	targetDB.delete();
                }
                targetDB.createNewFile();
                
                out = new FileOutputStream(targetDB);
                dst = out.getChannel();
                
                dst.transferFrom(src, 0, src.size());     
                Toast.makeText(context, "Imported database: " + importDB.toString(), Toast.LENGTH_SHORT).show();
            }
        } 
		catch (Exception e) {
			e.printStackTrace();
        }
		finally {
            try {
            	if(src != null)
            		src.close();
            	
            	if(in != null)
            		in.close();
				
            	if(dst != null)
            		dst.close();
            	
            	if(out != null)
            		out.close();	
			} 
            catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
