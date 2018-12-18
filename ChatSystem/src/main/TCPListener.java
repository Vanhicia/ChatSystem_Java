package main;

import java.util.Observable;

public class TCPListener extends Observable{
	private Message watchedValue;
	
	public TCPListener(Message value) {
		this.watchedValue=value;
	}
	public void setValue(Message value) {
      if(!watchedValue.equals(value)) {
         System.out.println("Value changed to new value: "+value.getMsg());
         watchedValue = value;
         setChanged();
         notifyObservers(value);
      }
   }
}
