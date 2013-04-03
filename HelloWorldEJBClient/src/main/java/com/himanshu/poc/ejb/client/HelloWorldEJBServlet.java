/*
 * Copyright 2013 Himanshu Bhardwaj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.himanshu.poc.ejb.client;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.himanshu.poc.ejb.HelloWorld;
import com.himanshu.poc.ejb.HelloWorldI;

public class HelloWorldEJBServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HelloWorldI ejb;
		try {
			ejb = HelloWorldEJBServlet.getHelloWorldEJB();
			System.out.println(ejb.printHello());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public static HelloWorldI getHelloWorldEJB() throws NamingException {
		InitialContext ctx;
		try {
			Hashtable<String, String> properties = new Hashtable<>();
			/*java.naming.factory.initial=org.jnp.interfaces.NamingContextFactory
					-Djava.naming.provider.url=jnp://localhost:1099
					-Djava.naming.factory.url.pkgs=org.jnp.interfaces

					-Djnp.timeout=0
					-Djnp.sotimeout=0*/

			/*properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
			properties.put("java.naming.provider.url", "jnp://localhost:1099");
			properties.put("java.naming.factory.url.pkgs", "org.jnp.interfaces");
			properties.put("jnp.timeout", "0");
			properties.put("jnp.sotimeout", "0");
			ctx = new InitialContext(properties);*/

			ctx = new InitialContext();
			HelloWorldI hwi = (HelloWorldI)ctx.lookup("java:global/HelloWorldEJB-0.0.1-SNAPSHOT/HelloWorld!com.himanshu.poc.ejb.HelloWorldI");
			return hwi;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
    }

}
