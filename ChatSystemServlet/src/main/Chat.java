/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import main.gui.LoginWindow;
import java.io.IOException;
/**
 *
 * @author katran
 */
public class Chat {
    public static void main(String[] args) throws IOException{
    	Controller contr = new Controller();
        LoginWindow loginWindow = new LoginWindow(contr);
        loginWindow.displayWindow();
    }
}
