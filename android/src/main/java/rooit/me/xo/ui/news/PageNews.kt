package rooit.me.xo.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rooit.me.xo.R
import rooit.me.xo.adapter.ArticleAdapter
import rooit.me.xo.databinding.FragmentNewsBinding

class PageNews : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val vm: NewsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            show()
            title = getString(R.string.title_news)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.isRefreshing.collect { isLoading ->
                    binding.refresh.isRefreshing = isLoading
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.allNews.collectLatest { articles ->
                    binding.list.adapter = ArticleAdapter(articles)
//                    if (binding.list.adapter == null) {
//                        val adapter = ArticleAdapter(articles)
//                        binding.list.adapter = adapter
//                    }else{
//                        (binding.list.adapter as ArticleAdapter).submitList(articles)
//                    }
//                    (binding.list.adapter as? ArticleAdapter)?.submitList(articles)
//                        ?: run {
//                            val newAdapter = ArticleAdapter(articles)
//                            binding.list.adapter = newAdapter
//                        }
                }
            }
        }

        binding.refresh.setOnRefreshListener {
            vm.fetchTopHeadlines(forceRefresh = true)
        }
        vm.fetchTopHeadlines()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}