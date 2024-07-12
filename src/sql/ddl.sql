-- Drop existing tables if they exist
DROP TABLE CHAT CASCADE CONSTRAINTS;
DROP TABLE ROOM CASCADE CONSTRAINTS;
DROP TABLE MEETING_COMMENT CASCADE CONSTRAINTS;
DROP TABLE USER_MEETING CASCADE CONSTRAINTS;
DROP TABLE MEETING CASCADE CONSTRAINTS;
DROP TABLE REGION CASCADE CONSTRAINTS;
DROP TABLE USERS CASCADE CONSTRAINTS;

-- Create USERS table
CREATE TABLE USERS (
                       user_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       email varchar2(200),
                       phone_number VARCHAR2(50),
                       user_name VARCHAR2(50),
                       password VARCHAR2(50),
                       profile_img_url varchar2(300),
                       birth DATE,
                       myself_memo CLOB,
                       reg_dt DATE DEFAULT SYSDATE
);

-- Create USER_MEETING table
CREATE TABLE USER_MEETING (
                              user_meeting_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              user_id NUMBER,
                              meeting_id NUMBER,
                              user_type VARCHAR2(30)
);

-- Create MEETING table
CREATE TABLE MEETING (
                         meeting_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         user_id NUMBER,
                         region_id NUMBER,
                         category VARCHAR2(20),
                         subject VARCHAR2(200),
                         context CLOB,
                         file_name varchar2(300),
                         total_member NUMBER,
                         present_member NUMBER default 1,
                         status VARCHAR2(20) default 'CONTINUE',
                         deadline_time timestamp,
                         reg_dt DATE DEFAULT SYSDATE
);


-- Create RE_MEETING table
CREATE TABLE MEETING_COMMENT (
                            meeting_comment_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            meeting_id NUMBER,
                            user_id NUMBER,
                            context CLOB,
                            reg_dt DATE DEFAULT SYSDATE
);

-- Create REGION table
CREATE TABLE REGION (
                        region_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        region_1depth_name VARCHAR2(10),
                        region_2depth_name VARCHAR2(10),
                        region_3depth_name VARCHAR2(10),
                        region_4depth_name VARCHAR2(10),
                        code VARCHAR2(10),
                        longitude NUMBER,
                        latitude NUMBER
);
-- Create ROOM table
CREATE TABLE ROOM (
                      room_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      meeting_id NUMBER
                  );

-- Create CHAT table
CREATE TABLE CHAT (
                      chat_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      room_id NUMBER,
                      user_id NUMBER,
                      message VARCHAR2(2000),
                      message_send_date timestamp default sysdate
);


-- Add foreign key constraints

-- USER_MEETING.user_id references USERS.user_id
ALTER TABLE USER_MEETING
    ADD CONSTRAINT fk_user_meeting_user
        FOREIGN KEY (user_id)
            REFERENCES USERS(user_id);

-- USER_MEETING.meeting_id references MEETING.meeting_id
ALTER TABLE USER_MEETING
    ADD CONSTRAINT fk_user_meeting_meeting
        FOREIGN KEY (meeting_id)
            REFERENCES MEETING(meeting_id);

ALTER TABLE MEETING
    ADD CONSTRAINT fk_meeting_region FOREIGN KEY (region_id) REFERENCES REGION(region_id);
ALTER TABLE MEETING_COMMENT
    ADD CONSTRAINT fk_meeting_comment_meeting FOREIGN KEY (meeting_id) REFERENCES MEETING(meeting_id);

ALTER TABLE MEETING_COMMENT
    ADD CONSTRAINT fk_meeting_comment_user FOREIGN KEY (user_id) REFERENCES USERS(user_id);

ALTER TABLE ROOM
    ADD CONSTRAINT fk_room_meeting FOREIGN KEY (meeting_id) REFERENCES MEETING(meeting_id);

ALTER TABLE CHAT
    ADD CONSTRAINT fk_chat_room FOREIGN KEY (room_id) REFERENCES ROOM(room_id);

ALTER TABLE CHAT
    ADD CONSTRAINT fk_chat_user FOREIGN KEY (user_id) REFERENCES USERS(user_id);

