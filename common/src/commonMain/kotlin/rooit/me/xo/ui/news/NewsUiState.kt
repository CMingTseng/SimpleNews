package rooit.me.xo.ui.news

import rooit.me.xo.model.Article

public data class NewsUiState(
    public  val articles: List<Article> = emptyList(),
    public val isLoading: Boolean = false,
    public val isRefreshing: Boolean = false, // Separate state for swipe refresh
    public val userMessage: String? = null // For user-facing messages (errors, info)
)