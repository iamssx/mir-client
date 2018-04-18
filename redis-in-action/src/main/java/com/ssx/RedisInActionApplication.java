package com.ssx;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisInActionApplication implements CommandLineRunner {

//	@Autowired
//	private StringRedisTemplate stringRedisTemplate;
//	@Autowired
//	private RedisTemplate redisTemplate;


	public static void main(String[] args) {
		SpringApplication.run(RedisInActionApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println();
//		String s = stringRedisTemplate.opsForValue().get("user:leto");
//		System.out.println(s);
//		DataType type = stringRedisTemplate.type("user:leto");
//		System.out.println(type);
//		stringRedisTemplate.expire("user:leto", 10, TimeUnit.SECONDS);
//
//		redisTemplate.opsForValue().set(new String("hahah"), "ipoopo");
	}
}
