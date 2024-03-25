package rooit.me.xo.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rooit.me.xo.R
import rooit.me.xo.adapter.ArticleAdapter
import rooit.me.xo.databinding.FragmentNewsBinding
import rooit.me.xo.model.NewAction

class NewsFragment : Fragment() {
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
        lifecycleScope.launch {
            vm.uiState.collect {
                it.news.isEmpty()
                if ( it.news.isEmpty()){
                    binding.refresh.isRefreshing = true
                }else{
                    binding.refresh.isRefreshing = false
                    val adapter = ArticleAdapter(it.news)
                    binding.list.adapter = adapter
                }
            }
        }
        vm.dispatch(NewAction.Load)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}