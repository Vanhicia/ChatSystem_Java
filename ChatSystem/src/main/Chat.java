package main;

import java.io.IOException;
import main.gui.LoginWindow;

public class Chat {
    public static void main(String[] args) throws IOException{
    	Controller contr = new Controller();
        LoginWindow loginWindow = new LoginWindow(contr);
        loginWindow.displayWindow();
    }
}
