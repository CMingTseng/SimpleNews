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

        binding.list.apply {
            adapter = rooit.me.xo.adapter.ArticleAdapter()
        }

        binding.refresh.setOnRefreshListener {
            vm.dispatch(NewsViewAction.RefreshNews)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            vm.dispatch(NewsViewAction.RefreshNews)
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.uiState.collectLatest { state ->
                    // Update SwipeRefreshLayout's refreshing state
                    binding.refresh.isRefreshing = state.isRefreshing

                    (binding.list.adapter as? rooit.me.xo.adapter.ArticleAdapter)?.submitList(state.articles)

                    // Handle loading state for initial load (e.g., show a ProgressBar)
                    // This is simplified here, you might have a dedicated ProgressBar
                    if (state.isLoading && !state.isRefreshing && state.articles.isEmpty()) {
                        binding.list.visibility = View.GONE
                    } else {
                        // Visibility based on articles and user messages
                        if (state.articles.isNotEmpty()) {
                            binding.list.visibility = View.VISIBLE
                        } else {
                            // No articles, show message if available, otherwise hide list
                            binding.list.visibility = View.GONE
                            if (state.userMessage != null) {
                            } else {

                            }
                        }
                    }

                    // Show toast for other user messages (e.g., transient errors if list is already populated)
                    if (state.userMessage != null && state.articles.isNotEmpty() && !state.isLoading && !state.isRefreshing) {
                        // Avoid showing "Loading..." or "No news" as toast if list is present
                        if (state.userMessage != "Loading news..." && state.userMessage != "No news available." && state.userMessage != "No new articles found from network.") {

                        }
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}