package com.test.calc.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Created by stshakun on 22.07.15.
 */
public class ClientForm extends JFrame implements KeyListener, WindowListener{
    JTextField writeField;
    JTextArea resultField;
    String Expression="";

    public static void main(String[] args) {
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public ClientForm(String nameOfWindow) {
        super(nameOfWindow);
    }

    private void addComponents(){

        writeField = new JTextField();
        writeField.addKeyListener(this);
        writeField.setColumns(30);

        resultField = new JTextArea(12,30);

        getContentPane().add(writeField, BorderLayout.PAGE_START);
        getContentPane().add(resultField, BorderLayout.CENTER);
        getContentPane().setPreferredSize(new Dimension(200, 299));
        addWindowListener(this);

    }

    private static void createAndShowGUI(){
        ClientForm paco = new ClientForm("I'am calculator");
        paco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        paco.addComponents();
        paco.pack();
        paco.setVisible(true);
    }


    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {

            Expression = writeField.getText();
            try {
                resultField.setText(
                        Expression + "="
                                + Client.calculate(Expression)
                                + "\n"
                                + resultField.getText());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            if (writeField.getText().equalsIgnoreCase("exit")) System.exit(0);
            writeField.setText("");
        }
        if (e.getKeyCode() == 38) {
            writeField.setText(Expression);
        }
        if (e.getKeyCode() == 40) {
            writeField.setText("");
        }

    }


    public void windowOpened(WindowEvent e) {Client.connectToServer();}
    public void windowClosing(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}

