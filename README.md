# MadCamp1: Basic.
>몰입캠프 **첫**주차인 만큼 기본에 집중하는 의미를 담았다. 
>
>휴대폰의 가장 Basic한 기능인 연락처와 갤러리, 퍼즐게임 구현.

### 팀원소개
>윤예지, 류수현

### Development Environment
>Android Studio 4.1.1

---
## 프로젝트 설명
### APP LOGO
<img src = "https://user-images.githubusercontent.com/77380770/124590348-fcefcf00-de95-11eb-85ae-f21a8596f546.png" width="200" height="200">
>앱 시작 화면으로 설정.

### TAB 1 - CONTACT
<img src = "https://user-images.githubusercontent.com/77380770/124599686-e69b4080-dea0-11eb-89d5-3e645cd7b6b2.gif" width="200">  <img src = "https://user-images.githubusercontent.com/77380770/124593332-776e1e00-de99-11eb-867a-70444a632479.gif" width="200">
- 설명
>연락처의 사진, 이름, 번호, 메일을 보여준다.
> 
>해당 연락처를 누르면 상세보기할 수 있다.
>>상세보기에서 번호를 누르면 전화 걸 수 있다.
>
>버튼을 누르면 **검색**과 **추가**기능을 사용할 수 있다.
>
>아래로 당기면 새로고침한다.


- 기능
>핸드폰의 연락처와 연동했다.
>>연락처 추가를 하면 실제 핸드폰 연락처에도 추가된다.
>
>RecyclerView와 CardView로 구현했다.
>
>viewHolder 클릭하면, ContactDetailActivity로 전환된다.
>
>검색과 추가 버튼은 FloatingActionButton으로 구현했다.


### TAB 2 - GALLERY
<img src = "https://user-images.githubusercontent.com/77380770/124611062-0edc6c80-deac-11eb-974e-84289549f51e.gif" width="200">   <img src = "https://user-images.githubusercontent.com/77380770/124610997-fcfac980-deab-11eb-8200-a6673cd8c1e6.gif" width="200">

- 설명
>총 5종류의 꽃 사진이 있는 갤러리다.
>
>초기화면은 종류별 앨범으로 보여진다.
>
>앨범을 누르면, 해당 앨범의 사진들이 보여진다.
>
>사진을 누르면, 해당 사진이 확대돼서 보여진다.

- 기능
>GridView로 구현했다.
>
>viewHolder 클릭하면, 각 position에 따른 세부 앨범으로 GalleryDetailActivity로 전환된다.
>
>GridView item 클릭하면, FullImageActivity로 전환된다.
>
>TAB3에서도 Gallery에 접근하도록 연결했다.

### TAB 3 - GAME
<img src = "https://user-images.githubusercontent.com/77380770/124599987-3da11580-dea1-11eb-8fa2-6a40bb8caf92.gif" width="200"> <img src = "https://user-images.githubusercontent.com/77380770/124600114-5ad5e400-dea1-11eb-964c-870a735232b8.gif" width="200"> 
- 설명
>NxN 슬라이드 퍼즐 게임이다.
>>빈 칸으로 다른 퍼즐 조각 밀어서 원래 이미지 완성하는 게임
>
>초기 화면에서 사용자가 맞출 **이미지**와 **N 사이즈**를 설정한다.
>>이미지는 **카메라**로 찍거나, TAB2의 **갤러리**에서 선택할 수 있다.
>
>다시하기 버튼을 누르면 퍼즐이 다시 섞인다.
>
>퍼즐을 다 맞추면 완성된 이미지가 나오고 Toast메시지가 뜬다.

- 기능
>4가지 방향(위, 아래, 좌, 우)의 xml파일로 애니메이션을 설정하였다.
>
>CheckAvailable로 조각의 위치에 따라 총 9개(왼쪽위, 왼쪽, 왼쪽아래, 가운데위, 가운데, 가운데 아래,,,,)로 나누어 퍼즐이 이동할 수 있는 조건을 체크한다.
>
>이미지의 가로, 세로를 N등분하여 생성한 Bitmap을 bitNXN배열에 넣는다.
>
>RecyclerView로 퍼즐 조각을 보여준다.
>
>퍼즐 조각을 suffle 함수를 써서 완전히 무작위로 섞지 않고, 완성된 퍼즐에서 직접 섞는 방식으로 구현했다.
