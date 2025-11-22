# JAFAR <img width="52" height="52" alt="image" src="https://github.com/user-attachments/assets/7c231c60-2b55-4792-bf6b-d4aaa778bcf5" />

> **J**acob’s **A**dvanced **F**eatured **A**ndroid **R**esume</br>
> 저(Jacob)의 지난 경력 및 실제 현업에서 사용했던 기술들을 하나의 앱 안에 녹여낸 **포트폴리오용 Android 이력서**입니다.


아래 로드맵에 따라 단계적으로 고도화할 예정입니다.

## 진행 상황

- [x] 프로젝트 생성 및 기본 Gradle 설정
- [x] README 초안 작성
- [x] 앱 컨셉 및 핵심 기능 확정
- [x] UI/UX 프로토타입 구현
- [ ] Clean Architecture 레이어 구성 완료
- [ ] ...

## 개발 로드맵 (예정)
### Phase 1 – 설계 및 프로토타입
- 앱 컨셉 구체화 및 핵심 기능 정의
- 기능별 User Flow 작성
- [화면 설계](https://www.figma.com/make/qYni2P6QC67CasuB1XPPDH/Jafar--Community-?node-id=0-1&p=f&t=kXXtMryN9koUUhPc-0&fullscreen=1) </br>
<img width="4028" height="1316" alt="image" src="https://github.com/user-attachments/assets/8cdcc726-d010-42a0-88ff-2896ad5f9bbc" />
- Jetpack Compose 기반 간단한 프로토타입 UI 구현

### Phase 2 – 아키텍처 구축
- Clean Architecture vs Google Architecture 
- Github Flow 브랜치 전략 사용
- 코드 컨벤션 및 ktlint, detekt 설정

### Phase 3 – 백엔드 연동 (Firebase)
- Firebase Functions로 간단한 RESTful API 구현
- DataSource 교체 (Local → Remote)
- Retrofit2 + OkHttp + Moshi/Kotlinx Serialization
- 캐싱 전략 설립

### Phase 4 – 포트폴리오용 기능 추가 (실무 스펙 어필)
| 기능                  | 사용 기술스택                                | 포트폴리오 어필 포인트                                      |
|----------------------|-------------------------------------------|-------------------------------------------------------|
| 지도 멀티 SDK         | Google Maps, Naver Map, Kakao Map SDK     | 국내외 지도 SDK 통합 경험, 좌표 변환, 커스텀 오버레이 등     |
| 동영상 스트리밍 플레이어 | ExoPlayer 완전 커스텀 (DRM 예정)            | 미디어 플레이어 직접 구현 능력, 재생 목록/자막/오프라인 지원 |
| OCR + QR/바코드       | ML Kit, ZXing 또는 Dynamsoft                | 카메라X + ML Kit 실무 수준 활용 사례                        |
| 카메라 고급 기능       | CameraX + 커스텀 뷰 + 필터                  | 최신 CameraX 아키텍처 이해도                               |
| CI/CD 파이프라인       | Fastlane + GitHub Actions                 | 배포 자동화 경험 (internal test → Play Store Internal)     |
| 테스트 커버리지        | JUnit5, MockK, Turbine, Compose Test      | 단위/UI 테스트 작성 능력                                    |

### Phase 5 – 릴리즈 및 배포
- 내부 테스트 트랙 배포 (Google Play Internal Testing)
- 앱 스토어 등록용 스크린샷/설명 작성
- 포트폴리오 PDF 및 발표 자료 제작

## 🛠️ 기술 스택 (현재 및 예정)

- Language: Kotlin 100%
- UI: Jetpack Compose (Material3)
- Architecture: Clean Architecture + Multi-module (검토 중)
- DI: Hilt
- Network: Retrofit2 + OkHttp
- Local DB: Room
- Async: Kotlin Coroutines + Flow
- Maps: Google, Naver, Kakao Map SDK
- Media: ExoPlayer (커스텀 플레이어)
- ML: ML Kit (OCR, Barcode)
- Camera: CameraX
- CI/CD: GitHub Actions + Fastlane
- Backend: Firebase Functions + Firestore (예정)

## 브랜치 전략

`Github Flow` 채택
- `main` : 언제나 배포 가능한 상태
- `feature/*`, `hotfix/*` 등 짧은 수명의 브랜치만 사용

## 프로젝트 구조 (예정)
```
:app                   ← 메인 앱 모듈 (Manifest, Application 클래스, UI 진입점)
:core
   └─ common           ← 공통 유틸리티, Kotlin Extension, Const, Base 클래스 등
:data                  ← Repository 구현체, Remote/Local DataSource, DTO
   └─ remote           ← Retrofit 서비스, API 인터페이스
   └─ local            ← Room Database, DAO, Entity
:domain                ← 순수 Kotlin 모듈 (비즈니스 로직만)
   └─ model            ← Entity (도메인 모델)
   └─ usecase          ← UseCase 클래스들
   └─ repository       ← Repository 인터페이스만
:feature
   ├─ home             ← 홈 화면 (스킬 요약, 프로필 카드 등)
   ├─ career           ← 경력 기술서 화면 (텍스트 + 타임라인 + 이미지)
   ├─ maps             ← Google / Naver / Kakao 지도 멀티 SDK 데모
   ├─ media-player     ← 커스텀 ExoPlayer + 스트리밍 재생 목록
   ├─ ocr-scanner      ← ML Kit OCR + QR/바코드 스캔 (CameraX 기반)
   ├─ camera-gallery   ← CameraX 고급 기능 쇼케이스
   └─ settings         ← 다크모드, 라이선스, 앱 정보 등
```


## 왜 포트폴리오로 프로젝트로 만들게 되었나요?

- 단순한 TODO 앱이 아닌, 실무에서 실제 구현했던 **기술 스택을 모두 직접 보여주며 어필할 수 있는 앱을 만들고 싶었습니다.**
- 또한 단순히 완성된 결과물이 아니라 과정을 노출하여 **그동안의 실무 경험을 간접적으로나마 노출하고 싶었습니다.**
- 특히 여러 도메인을 다양하게 경험하며 도메인 별로 중요시되는 **지도 SDK 경험**, **미디어 플레이어 구현**, **카메라 혹은 ML Kit** 등의 기술 사용 능력을 자연스럽게 어필하고 싶었습니다.

---

⭐ Star 혹은 Watch 해주시면 개발 과정 실시간으로 확인 가능합니다!  
궁금한 점 있으면 Issues나 [LinkedIn](https://www.linkedin.com/in/jacob-yoo-a3593a21b)으로 연락 주세요 :)
