package usecase.practica5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import material.ordereddictionary.RBTOrderedDict;
import org.json.JSONArray;
import org.json.JSONObject;

public class TweetAnalysis {

	private RBTOrderedDict<Integer, Tweet> dict_tweet;

	public TweetAnalysis(){
		dict_tweet = new RBTOrderedDict<>();
	}
	
	/**
	 * Adds a new set of tweets to the tree from the given file
	 */
//	public void addFile(String tweetFile) {
//		// String path = "darksouls.json";
//		String path = tweetFile;
//		BufferedReader bf = new BufferedReader(new FileReader(path));
//		String line = null;
//		String json = "";
//		while ((line = bf.readLine()) != null) {
//			json += line;
//		}
//		bf.close();
//		JSONObject obj = new JSONObject(json);
//		JSONArray tweets = obj.getJSONArray("statuses");
//		System.out.println(tweets.length());
//		for (int i=0;i<tweets.length();i++) {
//			JSONObject t = tweets.getJSONObject(i);
//			// System.out.println("Tweet "+i+": "+t.get("text"));
//			Tweet newTweet = new Tweet(t.get("user.name"), t.get("text"), t.get("retweet_count"), t.get("favorite_count"));
//			Integer newTweet_key = newTweet.getRtAndFav();
//			this.dict_tweet.insert(newTweet_key, newTweet);
//		}
//	}
	
	/**
	 * Recovers all the tweets with score larger or equal than min and smaller or equal than max
	 */
	public Iterable<Tweet> findTweets(int min, int max) {
		throw new RuntimeException("Not yet implemented");
//		return this.dict_tweet.findRange(min, max);
	}
	
	/**
	 * Recovers all the tweets with score smaller or equal than percent*MAX_SCORE
	 */
	public Iterable<Tweet> worstTweets(double percent) {
		throw new RuntimeException("Not yet implemented");
//		int MAX_SCORE = dict_tweet.first() * percent; // como sea
//		return dict_tweet.findRange(0, MAX_SCORE);
	}
	
	/**
	 * Recovers all the tweets with score larger or equal than percent*MAX_SCORE
	 */
	public Iterable<Tweet> bestTweets(double percent) {
		throw new RuntimeException("Not yet implemented");
//		int MAX_SCORE = dict_tweet.last() * percent; // como sea
//		return dict_tweet.findRange(MAX_SCORE, Math.inf());
	}

}
