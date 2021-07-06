# MadCamp1: Basic.
>프로젝트 한줄로 설명하기

### 팀원소개
>윤예지, 류수현

### Development Environment
>Android Studio 4.1.1

### apk file
>[download](링크주소넣기)

---
## 프로젝트 설명
### APP LOGO
<img src = "https://user-images.githubusercontent.com/77380770/124590348-fcefcf00-de95-11eb-85ae-f21a8596f546.png" width="200" height="200">


### TAB 1 - CONTACT
- 설명
>연락처의 사진, 이름, 번호, 메일을 보여준다.
> 
>해당 연락처를 누르면 상세보기할 수 있다.
>
>버튼을 누르면 **검색**과 **추가** 기능을 사용할 수 있다.

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
- 설명
>NxN 슬라이드 퍼즐 게임이다.
>>빈 칸으로 다른 퍼즐 조각 밀어서 원래 이미지 완성하는 게임
>
>초기 화면에서 사용자가 맞출 **이미지**와 **N 사이즈**를 설정한다.
>>이미지는 **카메라**로 찍거나, TAB2의 **갤러리**에서 선택할 수 있다.
>
>

- 기능
>
>
>
>
>
