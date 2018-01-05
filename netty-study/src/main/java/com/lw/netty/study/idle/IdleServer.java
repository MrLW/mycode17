package com.lw.netty.study.idle;

import io.netty.bootstrap.ServerBootstrap;

public class IdleServer {

	public static void main(String[] args) {
		int port = 8080;
		startServer(port);
	}

	private static void startServer(int port) {
		ServerBootstrap bootstrap = new ServerBootstrap();
		
	}
}
