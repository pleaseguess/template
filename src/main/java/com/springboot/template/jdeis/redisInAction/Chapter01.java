package com.springboot.template.jdeis.redisInAction;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.util.*;

public class Chapter01 {
    private static final int ONE_WEEK_IN_SECONDS = 7 * 86400;   //一周的时间常量(s)
    private static final int VOTE_SCORE = 432;                   //投票得分
    private static final int ARTICLES_PER_PAGE = 25;            //

    public static final void main(String[] args) {
        new Chapter01().run();
    }

    public void run() {
        Jedis conn = new Jedis("47.98.111.76",6379);
        conn.select(15);

        String articleId = postArticle(conn, "username", "A title", "http://www.google.com");
        System.out.println("We posted a new article with id: " + articleId);
        System.out.println("Its HASH looks like:");
        Map<String,String> articleData = conn.hgetAll("article:" + articleId);
        for (Map.Entry<String,String> entry : articleData.entrySet()){
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println();

        articleVote(conn, "other_user", "article:" + articleId);
        String votes = conn.hget("article:" + articleId, "votes");
        System.out.println("We voted for the article, it now has votes: " + votes);
        assert Integer.parseInt(votes) > 1;

        System.out.println("The currently highest-scoring articles are:");
        List<Map<String,String>> articles = getArticles(conn, 1);
        printArticles(articles);
        assert articles.size() >= 1;

        addGroups(conn, articleId, new String[]{"new-group"});
        System.out.println("We added the article to a new group, other articles include:");
        articles = getGroupArticles(conn, "new-group", 1);
        printArticles(articles);
        assert articles.size() >= 1;
    }

    //文章发布功能返回文章ID
    public String postArticle(Jedis conn, String user, String title, String link) {
        //生成一个新的文章id
        String articleId = String.valueOf(conn.incr("article:"));

        //将发布文章的用户添加到文章已投票用户名单里面，然后将这个名单的过期时间设置为一周
        String voted = "voted:" + articleId;
        conn.sadd(voted, user);
        conn.expire(voted, ONE_WEEK_IN_SECONDS);

        //将文章信息存储在一个散列里面
        long now = System.currentTimeMillis() / 1000;
        String article = "article:" + articleId;
        HashMap<String,String> articleData = new HashMap<String,String>();
        articleData.put("title", title);
        articleData.put("link", link);
        articleData.put("user", user);
        articleData.put("now", String.valueOf(now));
        articleData.put("votes", "1");
        conn.hmset(article, articleData);

        //将文章ID添加到根据评分排序的有序集合
        conn.zadd("score:", now + VOTE_SCORE, article);
        //将文章ID添加到根据时间排序的有序集合
        conn.zadd("time:", now, article);

        return articleId;
    }

    //文章投票功能
    public void articleVote(Jedis conn, String user, String article) {
        long cutoff = (System.currentTimeMillis() / 1000) - ONE_WEEK_IN_SECONDS;
        //计算文章的投票截止时间
        if (conn.zscore("time:", article) < cutoff){
            return;
        }
        //从key中取出文章id
        String articleId = article.substring(article.indexOf(':') + 1);
        //如果用户是第一次为文章投票，那么增加文章的投票数量和评分
        if (conn.sadd("voted:" + articleId, user) == 1) {
            conn.zincrby("score:", VOTE_SCORE, article);
            conn.hincrBy(article, "votes", 1);
        }
    }


    public List<Map<String,String>> getArticles(Jedis conn, int page) {
        return getArticles(conn, page, "score:");
    }

    // 分页获取文章
    public List<Map<String,String>> getArticles(Jedis conn, int page, String order) {

        //设置文章的起始索引和结束索引
        int start = (page - 1) * ARTICLES_PER_PAGE;
        int end = start + ARTICLES_PER_PAGE - 1;

        //获取多个文章的ID
        Set<String> ids = conn.zrevrange(order, start, end);

        //根据文章的id获取文章的详细信息
        List<Map<String,String>> articles = new ArrayList<Map<String,String>>();
        for (String id : ids){
            Map<String,String> articleData = conn.hgetAll(id);
            articleData.put("id", id);
            articles.add(articleData);
        }

        return articles;
    }

    //将文章添加到分组
    public void addGroups(Jedis conn, String articleId, String[] toAdd) {
        String article = "article:" + articleId;
        for (String group : toAdd) {
            conn.sadd("group:" + group, article);
        }
    }

    //从群组里面获取一整页数据
    public List<Map<String,String>> getGroupArticles(Jedis conn, String group, int page) {
        return getGroupArticles(conn, group, page, "score:");
    }
    //从群组里面获取一整页数据
    public List<Map<String,String>> getGroupArticles(Jedis conn, String group, int page, String order) {
        //为每组的排列顺序都创建一个键
        String key = order + group;
        if (!conn.exists(key)) {
            //
            ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
            //根据评分或者发布时间对群组文章进行排序
            conn.zinterstore(key, params, "group:" + group, order);
            //让redis在60s之后自动删除这个集合
            conn.expire(key, 60);
        }
        //分页并获取文章数据
        return getArticles(conn, page, key);
    }

    /**
     *
     * @param articles
     */
    private void printArticles(List<Map<String,String>> articles){
        for (Map<String,String> article : articles){
            System.out.println("  id: " + article.get("id"));
            for (Map.Entry<String,String> entry : article.entrySet()){
                if (entry.getKey().equals("id")){
                    continue;
                }
                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
