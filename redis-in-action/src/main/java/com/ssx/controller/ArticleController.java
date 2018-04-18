package com.ssx.controller;

import com.ssx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by SSX on 2017/11/3.
 */
@RestController
public class ArticleController {

    private final static int ONE_WEEK_IN_SECOND = 7 * 86400;
    private final static int VOTE_SCORE = 86400 / 200;
    private final static String ERROR = "error";
    private static final int ARTICLE_PER_PAGE = 10;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(String username, HttpSession session) {
        session.setAttribute("username", username);
        return "hello";
    }

    @PutMapping("vote/articleId/{articleId}")
    public String vote(@PathVariable("articleId") Integer articleId, HttpSession session) {
        long cutOff = System.currentTimeMillis() / 1000 - ONE_WEEK_IN_SECOND;
        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();
        Double score = opsForZSet.score("time:", "articleId:" + articleId);
        if (score == null) {
            score = 0.0D;
        }
        if (cutOff > score) {
            return ERROR;
        }
        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            return ERROR;
//        }
        Integer userId = userService.findIdByUserName(username);
        if (userId == null) {
            return "";
        }
        if (stringRedisTemplate.opsForSet().add("voted:" + articleId, userId + "") > 0) {
            opsForZSet.incrementScore("score:", articleId + "", VOTE_SCORE);
            stringRedisTemplate.opsForHash().increment(articleId + "", "votes", 1);
        }
        return ERROR;
    }

    @PostMapping("/article")
    public String postArticle(String title, String link, HttpSession session) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(link)) {
            return ERROR;
        }
        Long articleId = stringRedisTemplate.opsForValue().increment("article:", 1);
        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
        Object username = session.getAttribute("username");
        if (username == null) {
            return ERROR;
        }
        Integer uid = userService.findIdByUserName((String) username);
        if (uid == null) {
            return ERROR;
        }
        String key = "voted:" + articleId;
        opsForSet.add(key, String.valueOf(uid));
        stringRedisTemplate.expire(key, ONE_WEEK_IN_SECOND, TimeUnit.SECONDS);

        long now = System.currentTimeMillis();
        String article = "articleId:" + articleId;
        HashMap<String, String> map = new HashMap<>(4);
        map.put("title:", title);
        map.put("link:", link);
        map.put("time:", String.valueOf(now));
        map.put("userId:", String.valueOf(uid));
        stringRedisTemplate.opsForHash().putAll(article, map);
        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();
        opsForZSet.add("score:", article, now + VOTE_SCORE);
        opsForZSet.add("time:", article, now);
        return articleId + "";
    }

    @GetMapping("/article/page/{page}")
    public Object findArticles(@PathVariable("page") Integer page, String order) {
        if (page == null || page <= 0) {
            return ERROR;
        }
        int start = (page - 1) * ARTICLE_PER_PAGE;
        int end = start + ARTICLE_PER_PAGE - 1;

        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();
        Set<String> set = opsForZSet.reverseRange(order + ":", start, end);
        if (set == null || set.isEmpty()) {
            return ERROR;
        }
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
        List<Map<String, String>> result = new ArrayList<>();
        for (String aid : set) {
            Map<String, String> values = opsForHash.entries(aid);
            result.add(values);
        }
        return result;
    }

    @PostMapping("/group/article/{articleId}")
    public void updateGroup(@PathVariable("articleId") Integer articleId, String addGroup, String removeGroup) {
        String article = "articleId:" + articleId;
        updateGroup(addGroup, article, (byte) 1);
        updateGroup(removeGroup, article, (byte) 0);
    }

    @PostMapping("/group/{group}/order/{order}")
    public Object getGroupArticle(@PathVariable("group") String group, @PathVariable("order") String order, Integer page) {
        String key = order + ":" + group;
        String groupKey = "group:" + group;
        String orderKey = order + ":";
        if (!stringRedisTemplate.hasKey(groupKey) || !stringRedisTemplate.hasKey(orderKey)) {
            return ERROR;
        }
        if (!stringRedisTemplate.hasKey(key)) {
            ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();
            opsForZSet.intersectAndStore(groupKey, orderKey, key);
            stringRedisTemplate.expire(key, 60, TimeUnit.SECONDS);
        }
        return findArticles(page, key);
    }

    private void updateGroup(String group, String article, byte updateType) {
        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
        if (group != null) {
            for (String g : group.split(";")) {
                String key = "group:" + g;
                if (!stringRedisTemplate.hasKey(key)) {
                    continue;
                }
                if (updateType == 1) {
                    opsForSet.add(key, article);
                }
                if (updateType == 0) {
                    opsForSet.remove(key, article);
                }
            }
        }
    }
}


























