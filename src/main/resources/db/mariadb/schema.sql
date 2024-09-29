-- create table school (
--     id bigint not null auto_increment,
--     name varchar(50) not null,
--     address varchar(50),
--     address_number bigint,
--     page_url varchar(255),
--     created_at datetime(6),
--     updated_at datetime(6),
--     primary key (id)
-- ) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table school (
    ATPT_OFCDC_SC_CODE varchar(255),
    ATPT_OFCDC_SC_NM varchar(255),
    SD_SCHUL_CODE bigint not null,
    SCHUL_NM varchar(255),
    ENG_SCHUL_NM varchar(255),
    SCHUL_KND_SC_NM varchar(255),
    LCTN_SC_NM varchar(255),
    JU_ORG_NM varchar(255),
    FOND_SC_NM varchar(255),
    ORG_RDNZC varchar(255),
    ORG_RDNMA varchar(255),
    ORG_RDNDA varchar(255),
    ORG_TELNO varchar(255),
    HMPG_ADRES varchar(255),
    COEDU_SC_NM varchar(255),
    ORG_FAXNO varchar(255),
    HS_SC_NM varchar(255),
    INDST_SPECL_CCCCL_EXST_YN varchar(255),
    HS_GNRL_BUSNS_SC_NM varchar(255),
    SPCLY_PURPS_HS_ORD_NM varchar(255),
    ENE_BFE_SEHF_SC_NM varchar(255),
    DGHT_SC_NM varchar(255),
    FOND_YMD date,
    FOAS_MEMRD date,
    created_at datetime(6),
    updated_at datetime(6),
    primary key (sd_schul_code)
    ) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table member (
    id bigint not null auto_increment,
    SD_SCHUL_CODE bigint,
    email varchar(255),
    password varchar(255),
    nickname varchar(255),
    role enum ('USER','ADMIN','GUEST'),
    phone varchar(255),
    status enum ('ACTIVE','BANNED','DORMANCY'),
    withdraw enum ('YES','NO'),
    mail_auth enum ('YES','NO'),
    school_auth enum ('YES','NO'),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    foreign key (SD_SCHUL_CODE) references school(SD_SCHUL_CODE)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table board_category (
    id bigint not null auto_increment,
    name varchar(255),
    state enum ('VISIBLE','DELETE','BLIND'),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table board (
    id bigint not null auto_increment,
    SD_SCHUL_CODE bigint,
    board_category_id bigint,
    member_id bigint,
    title varchar(255),
    content varchar(255),
    state enum ('VISIBLE','DELETE','BLIND'),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    foreign key (SD_SCHUL_CODE) references school(SD_SCHUL_CODE),
    foreign key (board_category_id) references board_category(id),
    foreign key (member_id) references member(id)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table banned (
    id bigint not null auto_increment,
    member_id bigint,
    period_date bigint,
    reason varchar(255),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    foreign key (member_id) references member(id)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table board_clip (
    id bigint not null auto_increment,
    member_id bigint,
    board_id bigint,
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    foreign key (member_id) references member(id),
    foreign key (board_id) references board(id)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table comment (
    id bigint not null auto_increment,
    board_id bigint,
    child_comment_id bigint,
    comment varchar(255),
    state enum ('VISIBLE','DELETE','BLIND'),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    foreign key (board_id) references board(id),
    foreign key (child_comment_id) references comment(id)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table market (
    id bigint not null auto_increment,
    member_id bigint,
    title varchar(255),
    content varchar(255),
    price bigint,
    sell enum ('YES','NO'),
    state enum ('VISIBLE','DELETE','BLIND'),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    foreign key (member_id) references member(id)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table message (
    id bigint not null auto_increment,
    sender_id bigint,
    receiver_id bigint,
    content varchar(255),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    foreign key (sender_id) references member(id),
    foreign key (receiver_id) references member(id)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table review (
    id bigint not null auto_increment,
    SD_SCHUL_CODE bigint,
    member_id bigint,
    subject varchar(255),
    teacher varchar(255),
    rating float(53) not null,
    content varchar(255),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    foreign key (SD_SCHUL_CODE) references school(SD_SCHUL_CODE),
    foreign key (member_id) references member(id)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table school_auth (
    id bigint not null auto_increment,
    member_id bigint,
    SD_SCHUL_CODE bigint,
    accept enum ('YES','NO'),
    reject_reason varchar(255),
    url varchar(255),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    foreign key (member_id) references member(id),
    foreign key (SD_SCHUL_CODE) references school(SD_SCHUL_CODE)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table sessions (
    id bigint not null auto_increment,
    member_id bigint,
    sessions_code varchar(255),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    unique (member_id),
    foreign key (member_id) references member(id)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;
