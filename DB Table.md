# AppDB
# 개발환경 : MySQL

# 회원정보 테이블
```SQL
CREATE TABLE `board_member` (
   `id` varchar(20) COLLATE utf8_bin NOT NULL,
   `passwrod` varchar(20) COLLATE utf8_bin DEFAULT NULL,
   `name` varchar(12) COLLATE utf8_bin DEFAULT NULL,
   `reg_date` datetime DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
``

# 게시물 테이블
```SQL
CREATE TABLE `content_info` (
   `num` int(11) NOT NULL AUTO_INCREMENT,
   `title` varchar(50) COLLATE utf8_bin DEFAULT NULL,
   `contents` varchar(300) COLLATE utf8_bin DEFAULT NULL,
   `readcount` int(11) DEFAULT NULL,
   `like_count` int(11) DEFAULT NULL,
   `hate_count` int(11) DEFAULT NULL,
   `reg_id` varchar(20) COLLATE utf8_bin DEFAULT NULL,
   `reg_date` datetime DEFAULT NULL,
   PRIMARY KEY (`num`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
```

# 호감 정보 테이블
```SQL
CREATE TABLE `favor_info` (
   `board_num` int(11) DEFAULT NULL,
   `reply_num` int(11) DEFAULT NULL,
   `isReply` int(11) DEFAULT NULL,
   `isLike` int(11) DEFAULT NULL,
   `reg_id` varchar(20) COLLATE utf8_bin DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='	'
 ```
 
 # 댓글 정보 테이블
 ```SQL
 CREATE TABLE `reply_info` (
   `num` int(11) NOT NULL AUTO_INCREMENT,
   `contents` varchar(300) COLLATE utf8_bin DEFAULT NULL,
   `like_count` int(11) DEFAULT NULL,
   `hate_count` int(11) DEFAULT NULL,
   `reg_id` varchar(20) COLLATE utf8_bin DEFAULT NULL,
   `reg_date` datetime DEFAULT NULL,
   PRIMARY KEY (`num`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
 ```
