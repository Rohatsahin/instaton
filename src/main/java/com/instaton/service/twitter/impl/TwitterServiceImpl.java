package com.instaton.service.twitter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

import com.instaton.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;
import com.instaton.entity.black.BlackHashTagEntity;
import com.instaton.entity.black.BlackNameEntity;
import com.instaton.entity.black.BlackWordEntity;
import com.instaton.entity.twitter.TwitterUserEntity;
import com.instaton.service.twitter.BlackHashTagEntityService;
import com.instaton.service.twitter.BlackNameEntityService;
import com.instaton.service.twitter.BlackWordEntityService;
import com.instaton.service.twitter.TwitterUserService;
import com.instaton.util.filter.TweetFilter;

@Service
public class TwitterServiceImpl {

  @Autowired private Twitter twitter;

  @Autowired private BlackHashTagEntityService blackKeywordService;

  @Autowired private TwitterUserService twitterUserService;

  @Autowired private BlackNameEntityService blackNameEntityService;

  @Autowired private BlackWordEntityService blackWordEntityService;

  public List<Tweet> filterTweets(final List<Tweet> tweets) {
    final List<BlackHashTagEntity> blackKeywordList = this.blackKeywordService.findAll();
    final List<TwitterUserEntity> allTwitterUserList = this.twitterUserService.findAll();
    final List<BlackNameEntity> blackNameEntityList = this.blackNameEntityService.findAll();
    final List<BlackWordEntity> blackWordEntityList = this.blackWordEntityService.findAll();

    final List<Tweet> filteredTweets = new ArrayList<>();
    final List<String> userIdList = new ArrayList<>();

    for (final Tweet tweet : tweets) {

      final int followersCount = tweet.getUser().getFollowersCount();
      if (followersCount < 20) {
        continue;
      }

      final int friendsCount = tweet.getUser().getFriendsCount();
      if (friendsCount > 2000 && followersCount < 1000) {
        continue;
      }

      final boolean isBot = friendsCount > followersCount * 5;
      if (friendsCount > 600 && isBot) {
        continue;
      }

      if (TweetFilter.checkIfBlacklistedLanguage(tweet)) {
        continue;
      }

      if (TweetFilter.checkIfLocationBlacklisted(tweet)) {
        continue;
      }

      if (TweetFilter.checkIfBlackWorded(tweet, blackWordEntityList)) {
        continue;
      }

      if (TweetFilter.checkIfBlackKeywordListed(blackKeywordList, tweet)) {
        continue;
      }

      if (TweetFilter.checkIfBlackName(tweet, blackNameEntityList)) {
        continue;
      }

      if (TweetFilter.checkIfNotVisitedBefore(allTwitterUserList, tweet)) {
        continue;
      }

      if (TweetFilter.isBlacklistedLanguage(tweet)) {
        continue;
      }

      final String valueOf = String.valueOf(tweet.getUser().getId());
      if (!userIdList.contains(valueOf)) {
        filteredTweets.add(tweet);
        userIdList.add(valueOf);
      }
    }
    return filteredTweets;
  }

  @Cacheable(
    cacheNames = CacheConstants.TWITTER_PROFILE_SERVICE_CURRENT,
    keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR
  )
  public TwitterProfile getCurrent() {
    return this.twitter.userOperations().getUserProfile();
  }

  // @Cacheable(cacheNames = CacheConstants.TWITTER_SEARCH_CURRENT, keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR)
  public SearchResults getSearch(final SearchParameters searchParameters) {

    return this.twitter.searchOperations().search(searchParameters);
  }
}
