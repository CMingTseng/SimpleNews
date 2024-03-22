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

初始化 :  13aff3d691205cb3870b37fd762350c4aa4bbcf8

加入 productFlavors : 65c7140dfe8f30d3d6c31ee7910bc7e9f9db1c9c 

## 修改需求(Retrofit 轉 Ktor / Ktorfit) 

- Retrofit部分無法跨平台 因此改用全kotlin撰寫的[Ktor](https://ktor.io/)達成跨平台寫法可類似Retrofit

   a1ff75a05b96939c1e95232df2f3dd5f50340d9a

- LiveData部分Google已經建議改用Kotlin Coroutines 的 [Flow](https://developer.android.com/kotlin/flow)

   75a556fc3f20612e0e73772b4015c2ab5b760a06

- 目前Android新的UI為[JetPack Compose](https://developer.android.com/jetpack/compose?hl=zh-tw)
 

