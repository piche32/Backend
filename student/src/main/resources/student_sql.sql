-- 데이터베이스 삭제, 생성, 선택
DROP DATABASE IF EXISTS ssafyb3;
CREATE DATABASE ssafyb3	DEFAULT CHARACTER SET utf8mb4;
USE ssafyb3;

-- 테이블 삭제, 생성
DROP TABLE IF EXISTS student;
CREATE TABLE student (
	userid VARCHAR(50) PRIMARY KEY,
	userpw VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL,
	score INT NOT NULL
);

-- 초기 더미데이터 등록
-- 		list.add(new Student("ssafy", "1234", "ㅂㅅㅎ", 100));
-- 		list.add(new Student("pshpsh", "2345", "ㅂㅅㅎ11", 100));
        
INSERT INTO student (userid, userpw, username, score)
VALUES ('ssafy', '1234', 'ㅂㅅㅎ', 100);
INSERT INTO student (userid, userpw, username, score)
VALUES ('SMKSMK', '2345', 'ㅅㅎㅁ', 90);

COMMIT;

-- 로그인 : SELECT userid, userpw
SELECT userid, userpw, username, score
FROM student
WHERE userid='ssafy' AND userpw = '1234';
-- 목록 전체 조회 : SELECT *
SELECT userid, userpw, username, score
FROM student;
-- 상세 조회 : SELECT userid
SELECT userid, userpw, username, score
FROM student
WHERE userid='ssafy';
-- 등록 : INSERT
INSERT INTO student (userid, userpw, username, score)
VALUES ('ssafy2', '1234', 'ㅂㅅㅎ', 100);
-- 수정 : UPDATE
UPDATE student
SET userpw='9999', username='견자단', score=88
WHERE userid='ssafy';
-- 삭제 : DELETE
DELETE
FROM student
WHERE userid='ssafy';