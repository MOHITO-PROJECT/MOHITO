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
package info.multiplatform.mohito.framework.util;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThrowableUtils {
	
	/** Logger for this class. */
	private static final Logger logger = Logger.getLogger(ThrowableUtils.class.getCanonicalName());
	
    /**
     * directly re-throw if argument is {@link java.io.IOException} or {@link Error}, otherwise throw IOException wrapping
     * the given argument
     * @param throwable throwable to be wrapped, if needed
     * @throws java.io.IOException
     */
    public static void throwAsIOExceptionIfPossible(Throwable throwable) throws IOException {
        throwAs(IOException.class, throwable);
    }

    /**
     * directly re-throw if wrappedThrowable is of type wrapperClass or {@link Error}, otherwise throw wrapped in wrapperClass
     *
     * @param wrapperClass class that extends {@link java.lang.Exception} to be used as wrapper
     * @param throwable throwable to be wrapped, if needed
     * @param String message the internal error message
     * @param <T> wrapper type
     * @throws T
     */
    public static <T extends Exception> void throwAs(Class<T> wrapperClass, String message, Throwable throwable) throws T {
        if (throwable instanceof Error) {
            throw (Error) throwable;
        } else {
            T wrapped = null;
            Constructor<T> constructor;
            try {
            	if(message == null) {
            		constructor = wrapperClass.getConstructor(Throwable.class);
            		wrapped = constructor.newInstance(throwable);
            	}
            	else {
            		constructor = wrapperClass.getConstructor(String.class, Throwable.class);
            		wrapped = constructor.newInstance(message, throwable);
            	}
            	
            } catch (NoSuchMethodException e) {
            	logger.log(Level.SEVERE, "FATAL - failed to wrap exception!", e);
            } catch (InvocationTargetException e) {
            	logger.log(Level.SEVERE, "FATAL - failed to wrap exception!", e);
            } catch (InstantiationException e) {
            	logger.log(Level.SEVERE, "FATAL - failed to wrap exception!", e);
            } catch (IllegalAccessException e) {
            	logger.log(Level.SEVERE, "FATAL - failed to wrap exception!", e);
            }

            if (wrapped == null) {
                throw new Error("FATAL - failed to wrap exception!");
            }

            throw wrapped;
        }
    }
    
    /**
     * directly re-throw if wrappedThrowable is of type wrapperClass or {@link Error}, otherwise throw wrapped in wrapperClass
     *
     * @param wrapperClass class that extends {@link java.lang.Exception} to be used as wrapper
     * @param throwable throwable to be wrapped, if needed
     * @param <T> wrapper type
     * @throws T
     */
    public static <T extends Exception> void throwAs(Class<T> wrapperClass, Throwable throwable) throws T {
    	throwAs(wrapperClass, null, throwable);
    }
    
}
