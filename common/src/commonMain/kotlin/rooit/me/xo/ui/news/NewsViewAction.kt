package rooit.me.xo.ui.news

public sealed class NewsViewAction {
    public data object InitialLoadOrRetry : NewsViewAction() // For initial load or retry after error
    public data object RefreshNews : NewsViewAction()
}