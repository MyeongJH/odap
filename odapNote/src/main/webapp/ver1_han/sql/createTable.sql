-- 답변자
DROP TABLE IF EXISTS ANSWERER RESTRICT;

-- Question
DROP TABLE IF EXISTS QUESTION RESTRICT;

-- Member
DROP TABLE IF EXISTS MEMBER RESTRICT;

-- Bookmark
DROP TABLE IF EXISTS BOOKMARK RESTRICT;

-- Answer
DROP TABLE IF EXISTS ANSWER RESTRICT;

-- 클래스멤버
DROP TABLE IF EXISTS CMEMBER RESTRICT;

-- Class
DROP TABLE IF EXISTS CLASS RESTRICT;

-- 답변자
CREATE TABLE ANSWERER (
  ANO INTEGER NOT NULL, -- 답변번호
  MNO INTEGER NOT NULL  -- 회원번호
);

-- 답변자
ALTER TABLE ANSWERER
  ADD CONSTRAINT PK_ANSWERER -- 답변자 기본키
    PRIMARY KEY (
      ANO, -- 답변번호
      MNO  -- 회원번호
    );

-- Question
CREATE TABLE QUESTION (
  QNO  INTEGER      NOT NULL, -- 질문번호
  CNO  INTEGER      NOT NULL, -- 클래스번호
  QTIT VARCHAR(50)  NOT NULL, -- 제목
  QCNT VARCHAR(255) NULL,     -- 내용
  QCD  DATE         NOT NULL, -- 생성일
  QST  BOOLEAN      NOT NULL, -- 답변완료여부
  QPIC VARCHAR(50)  NULL,     -- 사진
  MNO  INTEGER      NOT NULL  -- 회원번호
);

-- Question
ALTER TABLE QUESTION
  ADD CONSTRAINT PK_QUESTION -- Question 기본키
    PRIMARY KEY (
      QNO -- 질문번호
    );

ALTER TABLE QUESTION
  MODIFY COLUMN QNO INTEGER NOT NULL AUTO_INCREMENT;

-- Member
CREATE TABLE MEMBER (
  MNO  INTEGER      NOT NULL, -- 회원번호
  MID  VARCHAR(50)  NOT NULL, -- 아이디
  MPW  VARCHAR(50)  NOT NULL, -- 패스워드
  MNM  VARCHAR(50)  NOT NULL, -- 이름
  MTEL VARCHAR(30)  NOT NULL, -- 전화
  MADR VARCHAR(255) NULL,     -- 주소
  MJOB VARCHAR(255) NULL,     -- 직장
  MPG  VARCHAR(255) NULL,     -- 홈페이지
  MPIC VARCHAR(50)  NULL,     -- 사진
  MCL  VARCHAR(50)  NULL      -- 클래스
);

-- Member
ALTER TABLE MEMBER
  ADD CONSTRAINT PK_MEMBER -- Member 기본키
    PRIMARY KEY (
      MNO -- 회원번호
    );

ALTER TABLE MEMBER
  MODIFY COLUMN MNO INTEGER NOT NULL AUTO_INCREMENT;

-- Bookmark
CREATE TABLE BOOKMARK (
  BNO INTEGER NOT NULL, -- 즐겨찾기번호
  MNO INTEGER NOT NULL, -- 회원번호
  QNO INTEGER NULL      -- 질문번호
);

-- Bookmark
ALTER TABLE BOOKMARK
  ADD CONSTRAINT PK_BOOKMARK -- Bookmark 기본키
    PRIMARY KEY (
      BNO -- 즐겨찾기번호
    );

ALTER TABLE BOOKMARK
  MODIFY COLUMN BNO INTEGER NOT NULL AUTO_INCREMENT;

-- Answer
CREATE TABLE ANSWER (
  ANO  INTEGER     NOT NULL, -- 답변번호
  ACON VARCHAR(50) NOT NULL, -- 내용
  QNO  INTEGER     NULL      -- 질문번호
);

-- Answer
ALTER TABLE ANSWER
  ADD CONSTRAINT PK_ANSWER -- Answer 기본키
    PRIMARY KEY (
      ANO -- 답변번호
    );

ALTER TABLE ANSWER
  MODIFY COLUMN ANO INTEGER NOT NULL AUTO_INCREMENT;

-- 클래스멤버
CREATE TABLE CMEMBER (
  MNO   INTEGER      NOT NULL, -- 회원번호
  CNO   INTEGER      NOT NULL, -- 클래스번호
  CMITR VARCHAR(255) NULL,     -- 자기소개
  CMAD  DATE         NOT NULL, -- 가입신청일
  CMST  BOOLEAN      NOT NULL  -- 상태
);

-- 클래스멤버
ALTER TABLE CMEMBER
  ADD CONSTRAINT PK_CMEMBER -- 클래스멤버 기본키
    PRIMARY KEY (
      MNO, -- 회원번호
      CNO  -- 클래스번호
    );

ALTER TABLE CMEMBER
  MODIFY COLUMN MNO INTEGER NOT NULL AUTO_INCREMENT;

-- Class
CREATE TABLE CLASS (
  CNO  INTEGER      NOT NULL, -- 클래스번호
  MNO  INTEGER      NULL,     -- 강사회원번호
  CNM  VARCHAR(50)  NOT NULL, -- 클래스명
  CCD  DATE         NOT NULL, -- 생성일
  CDES VARCHAR(255) NOT NULL, -- 설명
  CSUB VARCHAR(50)  NOT NULL  -- 과목
);

-- Class
ALTER TABLE CLASS
  ADD CONSTRAINT PK_CLASS -- Class 기본키
    PRIMARY KEY (
      CNO -- 클래스번호
    );

ALTER TABLE CLASS
  MODIFY COLUMN CNO INTEGER NOT NULL AUTO_INCREMENT;

-- 답변자
ALTER TABLE ANSWERER
  ADD CONSTRAINT FK_ANSWER_TO_ANSWERER -- Answer -> 답변자
    FOREIGN KEY (
      ANO -- 답변번호
    )
    REFERENCES ANSWER ( -- Answer
      ANO -- 답변번호
    );

-- 답변자
ALTER TABLE ANSWERER
  ADD CONSTRAINT FK_MEMBER_TO_ANSWERER -- Member -> 답변자
    FOREIGN KEY (
      MNO -- 회원번호
    )
    REFERENCES MEMBER ( -- Member
      MNO -- 회원번호
    );

-- Question
ALTER TABLE QUESTION
  ADD CONSTRAINT FK_CLASS_TO_QUESTION -- Class -> Question
    FOREIGN KEY (
      CNO -- 클래스번호
    )
    REFERENCES CLASS ( -- Class
      CNO -- 클래스번호
    );

-- Question
ALTER TABLE QUESTION
  ADD CONSTRAINT FK_MEMBER_TO_QUESTION -- Member -> Question
    FOREIGN KEY (
      MNO -- 회원번호
    )
    REFERENCES MEMBER ( -- Member
      MNO -- 회원번호
    );

-- Bookmark
ALTER TABLE BOOKMARK
  ADD CONSTRAINT FK_MEMBER_TO_BOOKMARK -- Member -> Bookmark
    FOREIGN KEY (
      MNO -- 회원번호
    )
    REFERENCES MEMBER ( -- Member
      MNO -- 회원번호
    );

-- Bookmark
ALTER TABLE BOOKMARK
  ADD CONSTRAINT FK_QUESTION_TO_BOOKMARK -- Question -> Bookmark
    FOREIGN KEY (
      QNO -- 질문번호
    )
    REFERENCES QUESTION ( -- Question
      QNO -- 질문번호
    );

-- Answer
ALTER TABLE ANSWER
  ADD CONSTRAINT FK_QUESTION_TO_ANSWER -- Question -> Answer
    FOREIGN KEY (
      QNO -- 질문번호
    )
    REFERENCES QUESTION ( -- Question
      QNO -- 질문번호
    );

-- 클래스멤버
ALTER TABLE CMEMBER
  ADD CONSTRAINT FK_MEMBER_TO_CMEMBER -- Member -> 클래스멤버
    FOREIGN KEY (
      MNO -- 회원번호
    )
    REFERENCES MEMBER ( -- Member
      MNO -- 회원번호
    );

-- 클래스멤버
ALTER TABLE CMEMBER
  ADD CONSTRAINT FK_CLASS_TO_CMEMBER -- Class -> 클래스멤버
    FOREIGN KEY (
      CNO -- 클래스번호
    )
    REFERENCES CLASS ( -- Class
      CNO -- 클래스번호
    );

-- Class
ALTER TABLE CLASS
  ADD CONSTRAINT FK_MEMBER_TO_CLASS -- Member -> Class
    FOREIGN KEY (
      MNO -- 강사회원번호
    )
    REFERENCES MEMBER ( -- Member
      MNO -- 회원번호
    );