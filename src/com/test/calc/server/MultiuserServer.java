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
 * close bracket. You can use + - * / operators. A neg number defines by "-".
 * Evaluator class is includes mathemathical functions like in java.math lib:
 * pow(a,b), sqrt(a), sin(a), cos(a) and other.
 *
 * You can read more about JEval functions on web-site:
 * http://jeval.sourceforge.net/docs/api/index.html
 *
 *
 */

import java.net.ServerSocket;
import java.lang.Thread;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;


public class MultiuserServer{
	static ServerSocket sSocket;

public static void main(String[] args) {
	int port = 4444;
	
	try {
		if (args.length != 0) {   //Taking params from the shell command line
			port = Integer.parseInt(args[0]);
		}

		sSocket = new ServerSocket(port); //Creating a new server socket
		System.out.println("Server socket is created.");


		while (true) {     //Catching a client and running a new thread
			new Thread(new СlientCatcher(sSocket.accept())).start();
		}
	} catch (Exception e) {
		System.out.println("Port is busy");
	}

}
}

/*
 * Implementing user thread. One client – one thread.
 *
 */
class СlientCatcher extends Thread {
	BufferedReader in;
	PrintWriter out;
	private Socket clientSocket;

	СlientCatcher(Socket soc) {
		this.clientSocket = soc;
	}

	public void run() {
		try {
			in = new BufferedReader(new
					InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(
					clientSocket.getOutputStream(), true);

			Evaluator calculate = new Evaluator();
			String inputExpression;

			while ((inputExpression = in.readLine()) != null) {
				try {
					out.println(calculate.evaluate(inputExpression));
				} catch (EvaluationException ee) {
					out.println(ee.getMessage());
				}

				if (inputExpression.equalsIgnoreCase("exit")) {
					break;
				}
			}

			in.close();
			out.close();
            System.out.println("Client is disconected.");
            clientSocket.close();

		} catch (Exception e) {
			System.out.println("Client is not connected: " + e);
		}
	}
}
