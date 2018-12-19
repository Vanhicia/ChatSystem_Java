package main;

import java.util.Observable;

public class TCPListener extends Observable{
	private String watchedValue;
	
	public TCPListener(String value) {
		this.watchedValue=value;
	}
	public void setValue(String value) {
      if(!watchedValue.equals(value)) {
         System.out.println("Value changed to new value: "+value);
         watchedValue = value;
         setChanged();
         notifyObservers(value);
      }
   }
}
