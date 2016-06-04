package com.test.calc.client;

/**
 * Created by stshakun on 25.07.15.
 *
 * This class have a method ConconnectToServer() for connect to server
 * method calculate() for send an expression to calculating
 */
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;


class Client {
    static Socket soc;
    static PrintWriter out;
    static BufferedReader in, reader;

    static void connectToServer(){
        try {
            soc = new Socket("localhost", 4444);
            System.out.println("Connection established");
            createInputAndOutput();
        } catch (IOException e) {
            System.out.println("Not Connection");
            System.exit(-1);
        }
    }


//Creating socket to connect server with defined adress and port
// Опшсать здесь функцшю подключеншя клиента

    static void connectToServer(String serverAddress, String sPort)
            throws IOException {
        int port=Integer.parseInt(sPort);
        soc = new Socket(serverAddress, port);
        System.out.println("Connection established");
        createInputAndOutput();
    }


    public static String calculate(String s) throws Exception {
        out.println(s);
        return in.readLine();

    }

    static void createInputAndOutput() throws IOException {
        in = new BufferedReader(new
                InputStreamReader(soc.getInputStream()));
        out = new PrintWriter(soc.getOutputStream(), true);
        reader = new BufferedReader(new
                InputStreamReader(System.in));

    }
}
