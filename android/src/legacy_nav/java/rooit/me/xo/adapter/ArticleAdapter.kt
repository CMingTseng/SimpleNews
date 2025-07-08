package rooit.me.xo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import rooit.me.xo.databinding.ItemArticleBinding
import rooit.me.xo.model.Article
import androidx.recyclerview.widget.ListAdapter
import rooit.me.xo.utils.ArticleDiffCallback

class ArticleAdapter : ListAdapter<Article, ArticleAdapter.ArticleHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val itemBinding =
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    inner class ArticleHolder(private val itemBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(article: Article) {
            itemBinding.itemContent.text = article.content ?: "" // 提供默认值以防 content 为 null
            // 其他文本字段也类似处理
            // itemBinding.itemTitle.text = article.title ?: "No Title"

            article.urlToImage?.let { imageUrl ->
                if (imageUrl.isNotBlank()) { // 最好也检查一下 URL 是否为空白
                    Glide.with(itemBinding.root.context)
                        .load(imageUrl)
                        .fitCenter() // 或者 .centerCrop() 根据您的 UI 需求
                        .transform(RoundedCorners(16)) // 确保 dp/px 值适合您的设计
                        .into(itemBinding.itemImage)
                } else {
                    // 如果 URL 为空或空白，可以设置一个默认图像或隐藏 ImageView

                }
            } ?: run {
                // 如果 article.urlToImage 本身为 null，也设置一个默认图像或隐藏
            }
        }
    }
}