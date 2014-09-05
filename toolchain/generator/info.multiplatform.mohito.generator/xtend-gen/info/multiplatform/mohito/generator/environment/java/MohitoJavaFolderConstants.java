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

import info.multiplatform.generator.java.templates.JavaFolderConstants;

@SuppressWarnings("all")
public class MohitoJavaFolderConstants extends JavaFolderConstants {
  /**
   * Directory used to store binary artifacts.
   */
  public final static String BIN = "bin";
  
  /**
   * Directory used to store all domain-model related generic artifacts.
   */
  public final static String MODEL = "model";
  
  /**
   * Directory used to store all domain-model related artifacts required additionally for the local storage.
   */
  public final static String MODEL_LOCAL = "local";
  
  /**
   * Directory used to store all domain-model related artifacts required additionally for the remote storage.
   */
  public final static String MODEL_REMOTE = "remote";
  
  /**
   * Directory used to store the utility artifacts for the domain-model.
   */
  public final static String MODEL_UTIL = "util";
}
