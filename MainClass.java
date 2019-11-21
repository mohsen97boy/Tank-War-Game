/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author mohsen
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class MainClass implements ActionListener {

    private int value = 0;
    Timer timer = new Timer(1000, this);

    @Override
    public void actionPerformed(ActionEvent e) {
        value++;
        System.out.println(value);
    }

    public void start() {
        timer.start();
    }



}
class Launcher {

    public static void main(String[] args) {
        MainClass ui = new MainClass();
        ui.start();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
