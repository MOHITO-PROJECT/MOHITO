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
package de.b2m.software.main;

import org.odata4j.producer.resources.DefaultODataProducerProvider;

import de.b2m.software.odata.utilities.JaxRsImplementation;
import de.b2m.software.odata.utilities.LibraryJPACreator;
import de.b2m.software.odata.utilities.ODataServerFactory;
import de.b2m.software.utilites.Constants;

/**
@author sdubey (B2M Software AG)
 *
 */
public class Program {

	/**
	 * This main function can be used to run this OData instance in an self-hosting environment 
	 * @param args
	 */
	public static void main(String[] args) {

		DefaultODataProducerProvider.setInstance(LibraryJPACreator
				.createProducer());
		System.out.println("Setting up Odata services..");
		new ODataServerFactory(JaxRsImplementation.JERSEY)
				.hostODataServer(Constants.HOST_SERVER_ENDPOINT);
	}

}
