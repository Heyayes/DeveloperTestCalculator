package com.test.calc.server;

/*
 * Creating by Stanislav Shakun on 25 jul 2015
 *
 * MultiServer class implementing multiuser server, which recieve math statements
 * and send answer to clients. Server recieves the string contains the expression
 * into Evaluator.evaluate() handler and returns the string with the answer
 * or the message from the EvaluationException.
 * Evaluator takes a correct math expressions, which may contain many operators
 * and operands. Count of spaces doesn't matter. The open bracket must have the
 * close bracket. You can use + - * / operators. A negative number defines by "-".
 *
 * You can read more about JEval functions on web-site:
 * http://jeval.sourceforge.net/docs/api/index.html
 *
 *
 */

import java.net.ServerSocket;
import java.lang.Thread;


public class MultiuserServer{
	static ServerSocket sSocket;

public static void main(String[] args) {
	int port = 4444;
	
	try {
        if (args.length != 0)
            port = Integer.parseInt(args[0]);

		//Creating new server socket
		sSocket = new ServerSocket(port);
		System.out.println("Server socket is created.");

		//Catching of new client and run new thread
		while (true) new Thread(new clientCatcher(sSocket.accept())).start();
	} catch (Exception e) {System.out.println("Port is busy");}

}
}

