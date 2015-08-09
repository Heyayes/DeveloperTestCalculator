package com.test.calc.client;

/**
 * Created by stshakun on 25.07.15.
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
            createInOut();

        } catch (IOException e) {
            System.out.println("Not Connection");
            System.exit(-1);}
    }


//Creating socket for connecting to server with defined adress and defined port
    static void connectToServer(String serverAddress, String sPort) throws IOException{

        int port=Integer.parseInt(sPort);
        soc = new Socket(serverAddress, port);
        System.out.println("Connection established");
        createInOut();


    }


    static String calculate(String s) throws Exception{
        out.println(s);

        return in.readLine();

    }

    static void createInOut() throws IOException{

            in = new BufferedReader(new
                    InputStreamReader(soc.getInputStream()));
            out = new PrintWriter(soc.getOutputStream(), true);
            reader = new BufferedReader(new
                InputStreamReader(System.in));

    }
}
