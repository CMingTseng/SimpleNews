# SimpleNews
## Power By Neo

[![N|Solid](https://i.imgur.com/k8oJm4N.png)](https://rooit.notion.site/Android-coding-test-a23daff0c96142a6a720a781cc8f8606)


這是某公司的面試題目,要求以下條件開發.

- 使⽤ kotlin 語⾔
- 使⽤ Retrofit library
- 實作 Dependency injection
- 使⽤ Realm
- 實作 MVVM 或 MVVMC 軟體框架
- 可以使⽤任何協助你開發的 third party libs

# 初始化 :  init  9fc63b196668175ac0873b679b0e5cda4ae90ed8
- Realm 需使用 Realm kotlin plugin (舊版一堆不相容)
- Realm kotlin 須拉升超過 2.1.0   [Issue_1825]
- 建議捨棄Realm 因為已經....被罵翻了   [Issue_1852] 
 [Issue_1857] 
 [reddit] 
 [从 Realm 迁移到 SQLDelight]
- Glide 在ksp需使用com.github.bumptech.glide:ksp  [kapt-->ksp]

# 改用 StateFlow 取代 LiveData : 
### Why not Flow or LiveData?
1. LiveData
    - LiveData has always been a love/hate thing for me. It's great for some things but can be very annoying for others. For example you MUST have an observer for livedata to emit anything. Making it pretty useless in a repository or anywhere except viewmodel->view communication.
1. Flow
    1. Flow is great. I use flows for my use cases when building out clean architecture. You don't need an "android observer" because flow lives in the coroutine world and there's even support for operators like RxJava has.
    1. Easily converted from livedata with a single function call.
    1. Tons of other useful properties. Check out the flow [docs](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/).
1. StateFlow
    1. StateFlow is the newest addition to kotlinx.coroutines.flow. Simply put StateFlow is a flow with special properties.
    1. Originally I had planned to use StateFlow in this course but it's really just totally unnecessary. I don't want to show you an unrealistic example of how to use something so I'm stinking with the very simple [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState) in viewmodels.

  [Issue_1825]: <https://github.com/realm/realm-kotlin/issues/1825>
  [Issue_1852]: <https://github.com/realm/realm-kotlin/issues/1852#issuecomment-2456592109>
  [Issue_1857]: <https://github.com/realm/realm-kotlin/issues/1857>
  [reddit]: <https://www.reddit.com/r/androiddev/comments/1fg13ov/comment/ln07lzo/>
  [从 Realm 迁移到 SQLDelight]: <https://segmentfault.com/a/1190000046550165>
  [kapt-->ksp]: <https://www.linkedin.com/posts/rezyfr_migrate-from-kapt-to-ksp-android-studio-activity-7090913619143524352-aOyr?utm_source=share&utm_medium=member_desktop>


