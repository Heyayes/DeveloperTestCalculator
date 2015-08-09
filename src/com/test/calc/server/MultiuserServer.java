package com.test.calc.server;

/*
 *
 * Creating by Stanislav Shakun on 25 jul 2015
 *
 * MultiServer class implementing multiuser server that may
 * recieve math statements and send answer to clients.
 *
 * For calculating use net.sourceforge.jeval lib.
 * Main class for calculating is Evaluator.
 * Evaluator has method evaluate(String) that recieve string
 * with mathemathical or boolean statement and return answer or
 * EvaluatorException. Evaluator has many
 *
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.lang.Thread;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.EvaluationException;
import junit.framework.TestCase;

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


/*
* Defined class for string-parsing math calculations from net.sourceforge.jeval
*
* Main method of Evaluator class is .evalueta()
* Them take String with statement as param.
*
* Return answer or exception(EvaluateException)
*
*/

class clientCatcher extends Thread{
    private BufferedReader in;
	private PrintWriter out;
	private Socket clientSocket;

    clientCatcher(Socket soc){
	    this.clientSocket = soc;
    }


    public void run(){
        try{
			in = new BufferedReader(new
			     InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(
				  clientSocket.getOutputStream(), true);

	        Evaluator calculate = new Evaluator(); //new Evaluator
	        String Expression;


//Recieving statement from client side, evaluating and sending result
            while ((Expression=in.readLine())!=null){
		        try{
		            out.println(calculate.evaluate(Expression));
	            } catch (EvaluationException ee){
	        	    out.println(ee.getMessage());
		        }

		        if (Expression.equalsIgnoreCase("exit")){
			        break;
		        }
	        }

	        in.close();
	        out.close();
	        System.out.println("Client disconected.");
	        clientSocket.close();

        } catch (Exception e) {
			System.out.println("Nihrena ne vishlo — "+ e);
		}
}
}