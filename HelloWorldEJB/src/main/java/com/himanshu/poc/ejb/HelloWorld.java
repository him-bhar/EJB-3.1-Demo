package com.himanshu.poc.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class HelloWorld
 */
@Stateless
@Remote
public class HelloWorld implements HelloWorldI {

    /**
     * Default constructor.
     */
    public HelloWorld() {
    	System.out.println("Inside constructor");
    }

    @Override
    public String printHello() {
    	return "Hello World from the ejb";
    }

}
