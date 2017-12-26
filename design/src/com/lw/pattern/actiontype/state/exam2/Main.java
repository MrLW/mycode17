package com.lw.pattern.actiontype.state.exam2;

public class Main {

	public static void main(String[] args) {
		Work work = new Work();
		
		work.setHour(9); // 开始上班
		work.writeProgram();// 开始工作
		
		work.setHour(11);
		work.writeProgram();
		
		work.setHour(12);
		work.writeProgram();
		
		work.setHour(13);
		work.writeProgram();
		
		work.setHour(14);
		work.writeProgram();
		
		work.setHour(17);
		// 如果没有工作完继续
		work.setFinished(true);

		work.writeProgram();
		work.setHour(21);
		work.writeProgram();
		work.setHour(22);
		work.setFinished(true);
		work.writeProgram();
	}
}
