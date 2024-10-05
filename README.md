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

初始化 :  init
- Realm 需使用 Realm kotlin plugin (舊版一堆不相容)
- Realm kotlin 須拉升超過 2.1.0   [Issue_1825]
- Glide 在ksp需使用com.github.bumptech.glide:ksp  [kapt-->ksp]

  [Issue_1825]: <https://github.com/realm/realm-kotlin/issues/1825>
  [Kapt-->ksp]: <https://www.linkedin.com/posts/rezyfr_migrate-from-kapt-to-ksp-android-studio-activity-7090913619143524352-aOyr?utm_source=share&utm_medium=member_desktop>


