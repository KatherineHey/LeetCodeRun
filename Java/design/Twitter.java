package design;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import utility.IO;
/**
 * Design a simplified version of Twitter where users can post tweets, 
 * follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed.
 * Your design should support the following methods:
        postTweet(userId, tweetId): Compose a new tweet.
        getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed.
        Each item in the news feed must be posted by users who the user followed or by the user herself.
        Tweets must be ordered from most recent to least recent.
        
        follow(followerId, followeeId): Follower follows a followee.
        unfollow(followerId, followeeId): Follower unfollows a followee.
 * @author https://leetcode.com/problems/design-twitter/discuss/82825/Java-OO-Design-with-most-efficient-function-getNewsFeed
 *
 */
public class Twitter {
    private static int tweetTimestamp = 0;
    
    HashMap<Integer, User> existingUsers = new HashMap<Integer, User>();
    public class User {
        int userId;
        // queue of tweets from the user
        Set<Integer> followees = new HashSet<>();
        Tweet tweetHead;
        public User(int userId) {
            this.userId = userId;
            tweetHead = null;
        }
        
        public void tweet(Tweet tweet) {
            if (tweetHead == null)
                tweetHead = tweet;
            else {
                tweet.next = tweetHead;
                tweetHead = tweet;
            }
        }
        
        public void follow(int followeeId) {
            if (!existingUsers.containsKey(followeeId)) {
                User newUser = new User(followeeId);
                existingUsers.put(followeeId, newUser);
            }
            
            followees.add(followeeId);
        }
        
        public void unfollow(int followeeId) {
            followees.remove(followeeId);
        }
    }
    
    public class Tweet {
        int tweetId;
        int tweetTime;
        public Tweet next;
        
        public Tweet(int tweetId) {
            this.tweetId = tweetId;
            tweetTimestamp++;
            this.tweetTime = tweetTimestamp;
            next = null;
        }
    }
    /** Initialize your data structure here. */
    public Twitter() {
        
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!existingUsers.containsKey(userId)) {
            User newUser = new User(userId);
            existingUsers.put(userId, newUser);
        }
        
        Tweet tweet = new Tweet(tweetId);
        existingUsers.get(userId).tweet(tweet);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. 
     * Each item in the news feed must be posted by users who the user followed or by the user herself. 
     * Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed= new ArrayList<Integer>();
        User user = existingUsers.get(userId);
        if (user == null) return newsFeed;
        
        // Get top 10 tweets from the user and all the followee's of the user
        PriorityQueue<Tweet> recentTweets = new PriorityQueue<>(10, (x,y)->y.tweetTime-x.tweetTime);
        
        // add all the tweets head to the priorityQueue recentTweets
        if (user.tweetHead != null) recentTweets.add(user.tweetHead);
        
        for (int followeeId : user.followees) {
            Tweet followeeTweetHead = existingUsers.get(followeeId).tweetHead;
            if (followeeTweetHead != null)
                recentTweets.add(followeeTweetHead);
        }
        
        int i = 0;
        while (i < 10 && !recentTweets.isEmpty()) {
            Tweet t = recentTweets.poll();
            if (t.next != null) recentTweets.add(t.next);
            
            if (!newsFeed.contains(t.tweetId)) {
                newsFeed.add(t.tweetId);
                i++;
            }
        }
        
        return newsFeed;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!existingUsers.containsKey(followerId)) {
            User newUser = new User(followerId);
            existingUsers.put(followerId, newUser);
        }
        
        if (!existingUsers.containsKey(followeeId)) {
            User newUser = new User(followeeId);
            existingUsers.put(followeeId, newUser);
        }
        
        User follower = existingUsers.get(followerId);
        follower.follow(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!existingUsers.containsKey(followerId) || !existingUsers.containsKey(followeeId))
            return;
        
        User follower = existingUsers.get(followerId);
        follower.unfollow(followeeId);
    }
    
    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        
        twitter.postTweet(1,5);
//        List<Integer> news1 = twitter.getNewsFeed(1);
//        IO.printArrayList(news1);
//        
        
        twitter.follow(1,1);
        List<Integer> news2 = twitter.getNewsFeed(1);
        IO.printArrayList(news2);
//        
//        twitter.unfollow(2, 1);
//        List<Integer> news3 = twitter.getNewsFeed(2);
//        IO.printArrayList(news3);
        
//        twitter.unfollow(1, 2);
//        List<Integer> news3 = twitter.getNewsFeed(1);
//        IO.printArrayList(news3);
    }
}
