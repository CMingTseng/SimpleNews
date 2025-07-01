package rooit.me.xo.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
        requireActivity()?.apply {
            (this as AppCompatActivity).apply {
                supportActionBar?.apply {
                    show()
                    title=getString(R.string.title_news)
                }
            }
        }
        vm.allNews.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                val adapter = ArticleAdapter(it)
                binding.list.adapter = adapter
            }
        })
        vm.isRefreshing.observe(viewLifecycleOwner) {
            binding.refresh.isRefreshing = it
        }
        binding.refresh.setOnRefreshListener {
            vm.fetchTopHeadlines()
        }
        vm.fetchTopHeadlines()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}