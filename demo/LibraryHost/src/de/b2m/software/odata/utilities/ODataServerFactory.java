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
package de.b2m.software.odata.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.odata4j.core.Throwables;
import org.odata4j.cxf.producer.server.ODataCxfServer;
import org.odata4j.jersey.producer.resources.ODataApplication;
import org.odata4j.jersey.producer.server.ODataJerseyServer;
import org.odata4j.producer.resources.DefaultODataApplication;
import org.odata4j.producer.resources.RootApplication;
import org.odata4j.producer.server.ODataServer;

import de.b2m.software.odata.security.LoggingFilter;

/**
@author sdubey (B2M Software AG)
 *
 */
public class ODataServerFactory {

	final JaxRsImplementation impl;

	/**
	 * Sets the used JaxRs implementation
	 * @param impl
	 */
	public ODataServerFactory(JaxRsImplementation impl) {
		this.impl = impl;
	}

	/**
	 * Starts the hosting, by creating and starting the server for a given base URI
	 * @param baseUri	URI to listen on
	 */
	public void hostODataServer(String baseUri) {
		ODataServer server = null;
		try {
			server = startODataServer(baseUri);
			System.out.println("Started host @" + baseUri);
			System.out.println("Press any key to exit");
			new BufferedReader(new InputStreamReader(System.in)).readLine();

		} catch (IOException e) {
			throw Throwables.propagate(e);
		} finally {
			if (server != null) {
				System.out.println("Stoping server");
				server.stop();
				System.out.println("Server stoped.");
			}

		}
	}

	/**
	 * Starts the server, which listens to the given base URI
	 * @param baseUri	URI to listen on
	 */
	public ODataServer startODataServer(String baseUri) {
		return createODataServer(baseUri).start();
	}

	/**
	 * Creates a OData server instance with the given base URI
	 * @param baseUri	URI to listen on
	 * @return	returns the newly created server instance
	 */
	public ODataServer createODataServer(String baseUri) {
		switch (impl) {
		case JERSEY:
			ODataJerseyServer ser = new ODataJerseyServer(baseUri,
					ODataApplication.class, RootApplication.class);
			ser.addJerseyRequestFilter(LoggingFilter.class);
			ser.addJerseyResponseFilter(LoggingFilter.class);
		case CXF:
			ODataCxfServer sev = new ODataCxfServer(baseUri,
					DefaultODataApplication.class, RootApplication.class);
			return sev;
		}
		return null;
	}
}
