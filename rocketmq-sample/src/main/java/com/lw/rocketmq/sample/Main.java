package com.lw.rocketmq.sample;

import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.common.message.MessageExt;

public class Main {

	public static void main(String[] args) {
		TransactionCheckListener listener = new TransactionCheckListener() {
			
			public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		
	}
}
