# Navigation

##Android Jetpack

## 참고

- [https://developer.android.com/guide/navigation/navigation-getting-started](https://developer.android.com/guide/navigation/navigation-getting-started)
- [https://www.youtube.com/watch?v=MHRt52NNzh0&list=PLxTmPHxRH3VXHOBnaGQcbSGslbAjr8obc&index=12&t=182s](https://www.youtube.com/watch?v=MHRt52NNzh0&list=PLxTmPHxRH3VXHOBnaGQcbSGslbAjr8obc&index=12&t=182s)

## 장점

- Fragment 간 이동을 간편하게 구현
- Fragment 간 데이터 전달을 간편하게 구현

## 사용 방법

- 예제는 1 Activity(MainActivity)와 2 Fragment(MainFragment, SecondFragment)를 사용
- res 에 navigation Directory 생성
    - 프로젝트 > 우 클릭 > New Resource Directory
    - Resource type → navigation 선택
- Navigation Resource File 생성
    - res > navigation Directory 우 클릭
    - Navigation Resource File 선택
    - title(예제는 nav_graph) 입력하고 생성
- nav_graph > New Destination (+버튼) 으로 Fragment 들 생성
- Drag 하여 Fragment 간 화살표(Action 객체) 연결
- Activity layout 에서 Palette에 NavHostFragment 검색하여 우 클릭 > "Add to Design" 클릭
(혹은 드레그)
    - 아래와 같은 소스가 자동 작성 됨

    ```java
    <fragment
    	  android:id="@+id/fragment"
    	  android:name="androidx.navigation.fragment.NavHostFragment"
    	  android:layout_width="match_parent"
    	  android:layout_height="match_parent"
    	  app:defaultNavHost="true"
    	  app:navGraph="@navigation/nav_graph" />
    ```

- 한 Fragment(MainFragment)에서 버튼을 클릭하면 다른 Fragment(SecondFragment)로 이동하는 코드

    ```java
    @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_secondFragment);
    								//nav_graph.xml 에서 Fragment 간 화살표 클릭해서 id 확인할 수 있음
                }
            });
        }
    ```

- Safe Args
    - 장점
        - Safe Args를 사용하여 유형 안전성을 갖춘 데이터 전달
            - 전달하는 데이터의 Type을 컴파일 타임에 체크하여 잘 못 넣어 에러가 날 수 없음
        - 콜백 작성하지 않고도 Fragment 간 데이터를 주고 받을 수 있음
    - 셋팅
        - [https://developer.android.com/jetpack/androidx/releases/navigation](https://developer.android.com/jetpack/androidx/releases/navigation#safe_args)
        - 프로젝트에 Safe Args를 추가하려면 최상위 build.gradle 파일에 다음과 같은 classpath를 포함합니다.

            ```java
            buildscript {
                repositories {
                    google()
                }
                dependencies {
                    def nav_version = "2.3.0-rc01"
                    classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
                }
            }
            ```

        - 자바 모듈 또는 자바와 Kotlin 혼합 모듈에 적합한 자바 언어 코드를 생성하려면 앱 또는 모듈의 build.gradle 파일에 다음 행을 추가합니다.

            ```java
            //Java
            apply plugin: "androidx.navigation.safeargs"

            //Kotlin
            apply plugin: "androidx.navigation.safeargs.kotlin"
            ```

    - 사용 방법
        - [https://developer.android.com/guide/navigation/navigation-pass-data](https://developer.android.com/guide/navigation/navigation-pass-data)
        - 받는 쪽 데이터 정의
            - nav_graph.xml > 받는 쪽 Fragment 클릭 > Attributes > Arguments + 클릭
            
        - 보내는 쪽 소스

            ```java
            @Override
                public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
                    super.onViewCreated(view, savedInstanceState);

            				//액션 객체 == 화살표 == R.id.action_mainFragment_to_secondFragment
                    final MainFragmentDirections.ActionMainFragmentToSecondFragment action =
                            MainFragmentDirections.actionMainFragmentToSecondFragment("Hello"); 

                    view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Navigation.findNavController(view).navigate(action);
                        }
                    });
                }
            ```

        - 받는 쪽 소스

            ```java
            @Override
                public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
                    super.onViewCreated(view, savedInstanceState);

                    String text = SecondFragmentArgs.fromBundle(getArguments()).getVar();
                    TextView textView = view.findViewById(R.id.textView);
                    textView.setText(text);
                }
            ```
