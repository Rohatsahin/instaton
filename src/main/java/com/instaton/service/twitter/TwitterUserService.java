package com.instaton.service.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.instaton.config.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;
import com.instaton.entity.social.twitter.TwitterUserEntity;
import com.instaton.repository.social.twitter.TwitterUserRepository;
import com.instaton.service.database.BaseService;

@Service
public class TwitterUserService implements BaseService {

  @Autowired private TwitterUserRepository repository;

  public void delete(final TwitterUserEntity input) {

    this.repository.delete(input);
  }

  @CacheEvict(
    cacheNames = CacheConstants.TWITTER_USER,
    keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR
  )
  public void evictfindAll() {
    System.out.println("cacheevict");
  }

  @Cacheable(
    cacheNames = CacheConstants.TWITTER_USER,
    keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR
  )
  @Override
  public List<TwitterUserEntity> findAll() {
    return this.repository.findAll();
  }

  public TwitterUserEntity findByScreenName(final String screenName) {
    return this.repository.findByScreenName(screenName);
  }

  public List<TwitterUserEntity> findTop100ByGenderIsNullOrderByUserId() {

    return this.repository.findTop200ByGenderIsNullOrderByUserId();
  }

  public void save(final TwitterUserEntity input) {

    this.repository.save(input);
  }
}
