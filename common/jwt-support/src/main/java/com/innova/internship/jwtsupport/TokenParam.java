package com.innova.internship.jwtsupport;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenParam {
	private final Map<String, Object> claimMap = new HashMap<>();
	private String[] audience;
	private Date expiredAt;

	public static Builder builder() {
		return new Builder();
	}

	public Map<String, Object> getClaimMap() {
		return claimMap;
	}

	public String[] getAudience() {
		return audience;
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

	public static class Builder {
		private final TokenParam tokenParam;

		public Builder() {
			tokenParam = new TokenParam();
		}

		public Builder expireAt(Date expireAt) {
			tokenParam.expiredAt = expireAt;
			return this;
		}

		public Builder audience(String[] audience) {
			tokenParam.audience = audience;
			return this;
		}

		public Builder put(String name, String value) {
			tokenParam.claimMap.put(name, value);
			return this;
		}

		public Builder putIfNotNullValue(String name, String value) {
			if (value != null) {
				tokenParam.claimMap.put(name, value);
			}
			return this;
		}

		public Builder putAll(Map<String, String> claims) {
			tokenParam.claimMap.putAll(claims);
			return this;
		}

		public TokenParam build() {
			return tokenParam;
		}
	}
}
