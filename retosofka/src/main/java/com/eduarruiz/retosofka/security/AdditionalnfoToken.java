package com.eduarruiz.retosofka.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.eduarruiz.retosofka.service.PlayerService;

@Component
public class AdditionalnfoToken implements TokenEnhancer {

	@Autowired
	private PlayerService playerService;

	//este método permite enviar información adicional del jugador y el accestoken
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<>();
		info.put("idPlayer", playerService.findByUserName(authentication.getName()).getEmailPlayer().toString());
		info.put("fullNamePlayer", playerService.findByUserName(authentication.getName()).getNamePlayer());
		info.put("levelPlayer", playerService.findByUserName(authentication.getName()).getLevel());
		info.put("scorePlayer", playerService.findByUserName(authentication.getName()).getScore());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}
}
