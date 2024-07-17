package br.com.alura.forumhub.dto;

public record FeedItemDto(long postId, String title, String content, String username) {
}