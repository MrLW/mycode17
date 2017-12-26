package com.lw.rmi.test;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class CalcImpl extends UnicastRemoteObject implements Calc {

	public CalcImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	public CalcImpl(int port) throws RemoteException {
		super(port);
		// TODO Auto-generated constructor stub
	}

	public CalcImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
		super(port, csf, ssf);
	}

	@Override
	public int add(int i, int j) throws RemoteException {
		return i + j ;
	}
	public static void main(String[] args) throws MalformedURLException, RemoteException, AlreadyBoundException {
		Calc calc = new CalcImpl() ;
		LocateRegistry.createRegistry(1234);
		Naming.bind("rmi://localhost:1234/calc", calc);
	}
}
