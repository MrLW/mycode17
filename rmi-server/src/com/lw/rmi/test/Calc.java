package com.lw.rmi.test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calc extends Remote {

	public int add(int i, int j) throws RemoteException;
}
