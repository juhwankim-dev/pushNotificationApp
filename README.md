<h1 align="center">
    <img src="https://github.com/juhwankim-dev/pushNotificationApp/blob/main/PushNotification/app/src/main/res/drawable-v24/icon_sheep.png" width="100" height="100" />
    <br> 아냥이
</h1>

<p align="center">
    <img src="https://img.shields.io/badge/version-v1.1.0-blue"> <img src="https://img.shields.io/badge/license-Apache--2.0-green">
</p>

<p align="center">
아냥이는 안양대학교 학생들을 위한
공지사항 푸시 알림 서비스 앱 입니다.
</p>

## Installation

[Goole Playstore](https://play.google.com/store/apps/details?id=com.juhwan.anyang_yi)

## Release Note
| Version | Release Date | Changes | Detail |
|--------|--------------|---------|--------|
| v1.1.0  | 2021.03.29   | 더 다양한 공지사항 확인, 연락처 검색, MVVM 구조로 변환, 버그 수정|[Blog](https://todaycode.tistory.com/37)

## Features

- 여기저기 흩어져 있는 공지를 한 곳에서 확인
- 키워드를 등록하면 공지가 올라왔을 때 푸시알림을 받음
- 부서 연락처를 빠르게 검색
- 학사 일정을 간편하게 확인

## Screenshot
<img src="https://github.com/juhwankim-dev/pushNotificationApp/blob/main/images/screenshot_1.png" width="100%">
<img src="https://github.com/juhwankim-dev/pushNotificationApp/blob/main/images/screenshot_2.png" width="100%">

## Flow Chart
<img src="https://github.com/juhwankim-dev/pushNotificationApp/blob/main/images/app_flow1.jpg" width="100%">
<img src="https://github.com/juhwankim-dev/pushNotificationApp/blob/main/images/app_flow2.jpg" width="100%">
<img src="https://github.com/juhwankim-dev/pushNotificationApp/blob/main/images/app_flow3.jpg" width="100%">
<img src="https://github.com/juhwankim-dev/pushNotificationApp/blob/main/images/app_flow4.jpg" width="100%">

## Project Architecture
<img src="https://github.com/juhwankim-dev/pushNotificationApp/blob/main/images/project_architecture.png" width="100%">

ViewModel, LiveData, Repository을 사용하여 MVVM 구조 기반으로 앱을 제작하였습니다.

특성상 최초 한 번만 요청해도 되는 데이터는 ViewModel을 거치지 않고 싱글톤으로 사용하는 것이

더 효율적이라 판단하여 위와 같은 구조로 변형을 하였습니다.

## Tech

적용한 기술들을 나열하였습니다.

* Architecture
  - Livecycle
  - Livedata
  - Navigation
  - ViewModel

* UI
  - ConstraintLayout
  - MaterialDesign
  - RecyclerView
  - Fragment
  - ViewPager2
  - TabLayout
  - WebView
  - SearchView
  - Segmented button (custom)
  - Transition animation

* Third Party
  - Firebase
  - Retrofit2
  - Jsoup
  - Glide
  - Kotlin Coroutine
  - Lottie
  - Room
  - Inko

## Problem

앱을 제작하며 맞닥뜨린 문제들을 고민하고 해결했던 과정을 기록해두었습니다.

| Problem | Blog Link |
| ------ | ------ |
| Token값 노출 | [https://todaycode.tistory.com/16][16] |
| Firebase 보안 | [https://todaycode.tistory.com/19][19] |
| 크롤링 방법 | [https://todaycode.tistory.com/21][21] |
| UI | [https://todaycode.tistory.com/14][14] |
| FCM 푸시 알림 | [https://todaycode.tistory.com/8][8] |
| 크롤링 봇 제작 | [https://todaycode.tistory.com/11][11] |
| Server | [https://todaycode.tistory.com/22][22] |
| 푸시알림 방식 | [https://todaycode.tistory.com/9][9] |

## License

```
Copyright 2021 Juhwan Kim

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


**문의 및 건의사항은 이메일로 보내주세요.**
**juhwan.dev@gmail.com**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [Lifecycle]: <https://developer.android.com/topic/libraries/architecture/lifecycle>
   [LiveData]: <https://developer.android.com/topic/libraries/architecture/livedata>
   [Navigation]: <https://developer.android.com/guide/navigation>
   [ViewModel]: <https://developer.android.com/topic/libraries/architecture/viewmodel>
   
   [16]: <https://todaycode.tistory.com/16>
   [19]: <https://todaycode.tistory.com/19>
   [21]: <https://todaycode.tistory.com/21>
   [14]: <https://todaycode.tistory.com/14>
   [8]: <https://todaycode.tistory.com/8>
   [11]: <https://todaycode.tistory.com/11>
   [22]: <https://todaycode.tistory.com/22>
   [9]: <https://todaycode.tistory.com/9>
