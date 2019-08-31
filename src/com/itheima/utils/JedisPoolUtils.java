package com.itheima.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {
	
	private static JedisPool pool = null;
	
	static {
		//加载配置文件
		InputStream in = JedisPoolUtils.class.getClassLoader().getResourceAsStream("redis.properties");
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(Integer.parseInt(pro.get("redis.maxIdle").toString()));//最大闲置个数
		poolConfig.setMinIdle(Integer.parseInt(pro.get("redis.minIdle").toString()));//最小闲置个数
		poolConfig.setMaxTotal(Integer.parseInt(pro.get("redis.maxTotal").toString()));//最大连接数
		pool = new JedisPool(poolConfig,pro.getProperty("redis.url"),Integer.parseInt(pro.get("redis.port").toString())); 
		
		
//		//获得池子对象
//		JedisPoolConfig poolConfig = new JedisPoolConfig();
//		poolConfig.setMaxIdle(30);//最大闲置个数
//		poolConfig.setMinIdle(10);//最小闲置个数
//		poolConfig.setMaxTotal(50);//最大连接数
//		//创建一个redis的连接池
//		pool = new JedisPool(poolConfig,"192.168.227.128",6379); 
	}
	
	//获得jedis资源的方法
	public static Jedis getJedis() {
		return pool.getResource();
	}
	
//	public static void main(String[] args) {
//		Jedis jedis = getJedis();
//		System.out.println(jedis.get("xxx"));
//	}
}
