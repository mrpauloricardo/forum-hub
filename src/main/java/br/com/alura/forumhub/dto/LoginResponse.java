package br.com.alura.forumhub.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}