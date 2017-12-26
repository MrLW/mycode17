package com.lw.redis.subpub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub {

	@Override
	public void onMessage(String channel, String message) {
		System.out.println("onMessage: channel[" + channel + "], message[" + message + "]");
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {
		System.out.println("onPMessage: channel[" + channel + "], message[" + message + "]");
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("onSubscribe: channel[" + channel + "]," + "subscribedChannels[" + subscribedChannels + "]");
	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println(
				"onUnsubscribe: channel[" + channel + "], " + "subscribedChannels[" + subscribedChannels + "]");
	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		System.out.println(
				"onPUnsubscribe: pattern[" + pattern + "]," + "subscribedChannels[" + subscribedChannels + "]");
	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		System.out
				.println("onPSubscribe: pattern[" + pattern + "], " + "subscribedChannels[" + subscribedChannels + "]");
	}

	public static void main(String[] args) {
		Jedis jr = null;
		try {
			jr = new Jedis("192.168.157.128", 6379, 0);// redis�����ַ�Ͷ˿ں�
			Subscriber sp = new Subscriber();
			sp.proceed(jr.getClient(), "news.share", "news.blog");
			// sp.proceedWithPatterns(jr.getClient(), "news.*");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jr != null) {
				jr.disconnect();
			}
		}
	}

}
