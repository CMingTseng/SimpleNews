package rooit.me.xo.utils

import androidx.recyclerview.widget.DiffUtil
import rooit.me.xo.model.Article

class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        // 使用 Article 的唯一标识符
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        // 因为 Article 是 data class，可以直接比较内容
        return oldItem == newItem
    }
}