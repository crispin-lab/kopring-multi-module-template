# 코프링 멀티 모듈 템플릿

## 멀티 모듈 구성 방식

### build-logic(gradle convention plugin 구성)

- ProjectExtensions
    - gradle project 환경 구성을 위한 extension 모음
- LinterGitHookInstallTask
    - kotlinter git hooks 설치를 위한 gradle 테스트 추가 용도
- JvmLibraryConventionPlugin
    - jvm 개발 환경 구성을 위한 플러그인
    - kotlinter 와 kotest 환경도 함께 포함
- KotestLibraryConventionPlugin
    - kotest 사용을 위한 환경 구성 플러그인
- KopringLibraryConventionPlugin
    - kotlin spring 사용을 위한 환경 구성 플러그인
    - spring test 도 함께 포함
- SpringTestLibraryConventionPlugin
    - spring test 사용을 위한 환경 구성 플러그인

### version catalog

- 라이브러리 버전 관리를 위한 version catalog 방식 사용
    - libs.versions.toml 파일을 사용하여 버전 관리
