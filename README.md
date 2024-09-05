
![header](https://capsule-render.vercel.app/api?type=waving&height=300&color=gradient&text=Spring%20Newsfeed)

- 한 줄 정리 : 페이스북, 인스타그램 등과 같은 SNS 프로젝트
- 내용 : 좋아하는 음악을 공유하며 의견을 주고 받는 페이지


# 🚀 STACK 
**Environment**

![인텔리제이](   https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![깃허브](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![깃](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![POSTMAN](https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

**Development**

![자바](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SPRING BOOT](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![SQL](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Gradle](https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

# 🤔 Authors

- [@pookyspooky](https://www.github.com/pookyspooky)
- [@DaegyuHan](https://www.github.com/DaegyuHan)
- [@webstrdy00](https://github.com/webstrdy00)
- [@pringles1234](https://github.com/pringles1234)
- [@tae98](https://www.github.com/tae98)




# 🙏 Acknowledgements

 - [Awesome Readme Templates](https://awesomeopensource.com/project/elangosundar/awesome-README-templates)
 - [Awesome README](https://github.com/matiassingers/awesome-readme)
 - [How to write a Good readme](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)

# 🖼️ Wireframe
<img width="1000" alt="스크린샷_2024-09-05_오후_3 11 00" src="https://github.com/user-attachments/assets/2c9dcc13-f4f5-4bfb-a3ea-436c4d884292">

# 🔖 API Reference

## 유저
#### 회원가입

```http
  POST /api/user/signup
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Body` | `string` | **Required**. {username, password, email} |

#### 로그인

```http
  POST /api/user/signup
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Body`      | `string` | **Required**. {email, password} |

#### 비밀번호 변경

```http
  PUT /api/user/change/password/{userId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `UserId`      | `string` | **Required**. 유저 고유식별 번호 |
| `Body`      | `string` | **Required**. {oldPassword, newPassword} |


#### 회원탈퇴

```http
  DELETE /api/user/change/password/{userId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `UserId`      | `string` | **Required**. 유저 고유식별 번호 |
| `Body`      | `string` | **Required**. {email, password} |

## 프로필

#### 프로필 등록

```http
  POST /api/profile/{profileId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `profileId`| `string` | **Required**. 유저 고유식별 번호 |
| `Body`      | `string` | **Required**. {descirption} |

#### 프로필 조회

```http
  GET /api/profile/{profileId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `profileId` | `string` | **Required**. 유저 고유식별 번호 |

#### 프로필 수정

```http
  PUT /api/profile/{profileId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `profileId`| `string` | **Required**. 유저 고유식별 번호 |

#### 프로필 좋아요

```http
  POST /api/profile/{profileId}/likes
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `profileId`| `string` | **Required**. 유저 고유식별 번호 |

## 게시물

#### 게시물 작성
```http
  POST /api/posts
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Body`      | `string` | **Required**. {title, content} |

#### 게시물 조회
```http
  GET /api/posts
```

#### 게시물 수정
```http
  PUT /api/posts/{postId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `postId`| `string` | **Required**. 게시물 고유식별 번호 |
| `Body`      | `string` | **Required**. {title, content} |

#### 게시물 삭제
```http
  DELETE /api/posts/{postId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `postId`| `string` | **Required**. 게시물 고유식별 번호 |

#### 특정 게시물 조회
```http
  GET /api/posts/{postId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `postId`| `string` | **Required**. 게시물 고유식별 번호 |

#### 특정 게시물 좋아요
```http
  POST /api/posts/{postId}/likes
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `postId`| `string` | **Required**. 게시물 고유식별 번호 |

#### 뉴스피드 게시물 조회
```http
  GET /api/posts/newsfeed
```

#### 게시물 검색
```http
  GET /api/posts/search?keyword=노래&searchType=both(title or content)
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Parameter`| `string` | **Required**. keyword, searchType |

## 댓글

#### 댓글 작성
```http
  POST /api/posts/{postId}/comments
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `postId`| `string` | **Required**. 게시물 고유식별 번호 |
| `Body`      | `string` | **Required**. {comment} |

#### 댓글 수정
```http
  PUT /api/comments/{commentId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `commentId`| `string` | **Required**. 댓글 고유식별 번호 |

#### 댓글 삭제
```http
  DELETE /api/comments/{commentId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `commentId`| `string` | **Required**. 댓글 고유식별 번호 |


#### 댓글 좋아요, 취소
```http
  POST /api/comments/{commentId}/likes
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `commentId`| `string` | **Required**. 댓글 고유식별 번호 |

## 팔로우

#### 팔로우 신청
```http
  POST /api/follow/{followId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `followId`| `string` | **Required**. 팔로우 고유식별 번호 |

#### 팔로우 수락
```http
  PUT /api/follow/request/{followId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `followId`| `string` | **Required**. 팔로우 고유식별 번호 |

#### 팔로잉 목록 조회
```http
  GET /api/follow/following-list
```

#### 팔로워 목록 조회
```http
  GET /api/follow/follower-list
```
#### 팔로우 신청 거부, 팔로잉 삭제
```http
  DELETE /api/follow/{followId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `followId`| `string` | **Required**. 팔로우 고유식별 번호 |

#### 팔로우 신청 목록 조회
```http
  GET /api/follow/request/follower-list
```


# ⚒️ ERD Diagram

![image](https://github.com/user-attachments/assets/bee210d6-1559-43a4-99f9-b268cd2efacc)


#  📊 SQL

     create table users
    (
        user_id     bigint auto_increment
            primary key,
        created_at  datetime(6)  null,
        modified_at datetime(6)  null,
        email       varchar(255) null,
        password    varchar(255) not null,
        username    varchar(255) null
    );
    
    create table alarm
    (
        id                bigint auto_increment
            primary key,
        created_at        datetime(6)             null,
        modified_at       datetime(6)             null,
        alarm_content     varchar(255)            null,
        alarm_receiver_id bigint                  null,
        alarm_sender_id   bigint                  null,
        checking_alarm    enum ('CHECKED', 'NEW') null,
        constraint FK619cta13yhqb34xm53ladadg5
            foreign key (alarm_sender_id) references users (user_id),
        constraint FKn3lovki7ivfvn6124c5uxtl9o
            foreign key (alarm_receiver_id) references users (user_id)
    );
    
    create table follow
    (
        id           bigint auto_increment
            primary key,
        accepted     enum ('ACCEPTED', 'NOT_YET') null,
        follower_id  bigint                       null,
        following_id bigint                       null,
        constraint FK9oqsjovu9bl95dwt8ibiy2oey
            foreign key (following_id) references users (user_id),
        constraint FKjikg34txcxnhcky26w14fvfcc
            foreign key (follower_id) references users (user_id)
    );
    
    create table posts
    (
        id            bigint auto_increment
            primary key,
        created_at    datetime(6)  null,
        modified_at   datetime(6)  null,
        comment_count int          not null,
        content       varchar(255) not null,
        like_count    int          not null,
        title         varchar(100) not null,
        user_id       bigint       null,
        constraint FK5lidm6cqbc7u4xhqpxm898qme
            foreign key (user_id) references users (user_id)
    );
    
    create table comment
    (
        comment_id  bigint auto_increment
            primary key,
        created_at  datetime(6)  null,
        modified_at datetime(6)  null,
        comment     varchar(500) not null,
        post_id     bigint       not null,
        user_id     bigint       not null,
        constraint FK7jok1s6lywoh0srylq8lt7tmn
            foreign key (post_id) references posts (id),
        constraint FKqm52p1v3o13hy268he0wcngr5
            foreign key (user_id) references users (user_id)
    );
    
    create table comment_likes
    (
        id          bigint auto_increment
            primary key,
        created_at  datetime(6) null,
        modified_at datetime(6) null,
        is_like     bit         not null,
        comment_id  bigint      null,
        user_id     bigint      null,
        constraint FK6h3lbneryl5pyb9ykaju7werx
            foreign key (user_id) references users (user_id),
        constraint FKd0epu3dcjc57pwe7lt5jgfqsi
            foreign key (comment_id) references comment (comment_id)
    );
    
    create table file
    (
        id                 bigint auto_increment
            primary key,
        file_path          varchar(255) not null,
        file_size          bigint       not null,
        file_type          varchar(255) null,
        original_file_name varchar(255) not null,
        stored_file_name   varchar(255) not null,
        post_id            bigint       null,
        constraint FKaxrl5nul6wsfiv2edyo27wypp
            foreign key (post_id) references posts (id)
    );
    
    create table likes
    (
        id          bigint auto_increment
            primary key,
        created_at  datetime(6) null,
        modified_at datetime(6) null,
        is_like     bit         not null,
        post_id     bigint      null,
        user_id     bigint      null,
        constraint FKnvx9seeqqyy71bij291pwiwrg
            foreign key (user_id) references users (user_id),
        constraint FKry8tnr4x2vwemv2bb0h5hyl0x
            foreign key (post_id) references posts (id)
    );
    
    create table profiles
    (
        profile_id  bigint auto_increment
            primary key,
        created_at  datetime(6)  null,
        modified_at datetime(6)  null,
        description varchar(255) null,
        user_id     bigint       not null,
        constraint UK4ixsj6aqve5pxrbw2u0oyk8bb
            unique (user_id),
        constraint FK410q61iev7klncmpqfuo85ivh
            foreign key (user_id) references users (user_id)
    );
    
    create table profile_likes
    (
        id          bigint auto_increment
            primary key,
        created_at  datetime(6) null,
        modified_at datetime(6) null,
        is_like     bit         not null,
        profile_id  bigint      null,
        user_id     bigint      null,
        constraint FK6nhqe59u317g9dwdjdf1wcg6w
            foreign key (user_id) references users (user_id),
        constraint FKe1dlsggebplf5p0ljnkvytiq8
            foreign key (profile_id) references profiles (profile_id)
    );
