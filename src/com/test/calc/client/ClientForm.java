package com.test.calc.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.test.calc.client.Client.calculate;


/**
 * Created by stshakun on 22.07.15.
 *
 * Class implementing user interface for inputing expressions and recieving
 * answers. For connecting server and sending expressions using Client class.
 *
 */
public class ClientForm extends JFrame implements KeyListener, WindowListener{
    JTextField writeField;
    JTextArea resultField;
    String expression = "";

    public static void main(String[] args) {
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public ClientForm(String nameOfWindow) {
        super(nameOfWindow);  //inputing name of window
    }

    private void addComponents(){
        writeField = new JTextField();   //creating input field
        writeField.addKeyListener(this);
        writeField.setColumns(30);

        resultField = new JTextArea(12, 30); //creating result field

        getContentPane().add(writeField, BorderLayout.PAGE_START);
        getContentPane().add(resultField, BorderLayout.CENTER);
        getContentPane().setPreferredSize(new Dimension(300, 500));

        addWindowListener(this);
    }

    private static void createAndShowGUI(){
        ClientForm clientForm = new ClientForm("I'am calculator");
        clientForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientForm.addComponents();
        clientForm.pack();
        clientForm.setVisible(true);
    }


    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            expression = writeField.getText();
            try {
                resultField.setText(
                        expression + "="
                                + calculate(expression)//Insert result
                                //from server.
                                + "\n"                        //Go to next string.
                                + resultField.getText());     //Insert old data.
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            if (writeField.getText().equalsIgnoreCase("exit")) System.exit(0);
            writeField.setText("");
        }

        if (e.getKeyCode() == 38) {
            writeField.setText(expression);
        }

        if (e.getKeyCode() == 40) {
            writeField.setText("");
        }

    }


    public void windowOpened(WindowEvent e) {
        Client.connectToServer();
    }

    public void windowClosing(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}

