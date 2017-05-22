package com.instaton.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "userconnection")
public class UserConnection implements Serializable {

	private static final long serialVersionUID = -6513324326814304874L;

	@Id
	@Column(name = "userid", unique = true, nullable = false)
	private String userId;

	@Column(name = "providerid")
	@NotEmpty
	private String providerId;

	@Column(name = "provideruserid")
	@NotEmpty
	private String providerUserId;

	@Column(name = "rank")
	private int rank;

	@Column(name = "displayname")
	private String displayName;

	@Column(name = "profileurl")
	private String profileUrl;

	@Column(name = "imageurl")
	private String imageUrl;

	@Column(name = "accesstoken")
	private String accessToken;

	@Column(name = "secret")
	private String secret;

	@Column(name = "refreshtoken")
	private String refreshToken;

	@Column(name = "expiretime")
	private Long expireTime;

	public String toString() {
		return "userId = " + userId + ", providerId = " + providerId + ", providerUserId = " + providerUserId
				+ ", rank = " + rank + ", displayName = " + displayName + ", profileUrl = " + profileUrl
				+ ", imageUrl = " + imageUrl + ", accessToken = " + accessToken + ", secret = " + secret
				+ ", refreshToken = " + refreshToken + ", expireTime = " + expireTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

}