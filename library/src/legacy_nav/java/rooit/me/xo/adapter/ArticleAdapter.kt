package rooit.me.xo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import rooit.me.xo.databinding.ItemArticleBinding
import rooit.me.xo.model.Article

class ArticleAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val itemBinding =
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bind(articles[position])
    }

   inner class ArticleHolder(private val itemBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(article: Article) {
            itemBinding.itemContent.text = article.content
            //Ref : https://medium.com/smartherd/shape-your-image-circle-rounded-square-or-cuts-at-the-corner-of-image-in-android-cf92f38c217f
            article.urlToImage?.let {
                Glide.with(itemBinding.root.context).load(it).fitCenter().transform(  RoundedCorners(16)).into(itemBinding.itemImage)
            }
        }
    }
}