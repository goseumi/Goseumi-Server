create table school (
    id bigint not null auto_increment,
    name varchar(50) not null,
    address varchar(50),
    address_number bigint,
    page_url varchar(255),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table member (
    id bigint not null auto_increment,
    school_id bigint,
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
    foreign key (school_id) references school(id)
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
    school_id bigint,
    board_category_id bigint,
    member_id bigint,
    title varchar(255),
    content varchar(255),
    state enum ('VISIBLE','DELETE','BLIND'),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    foreign key (school_id) references school(id),
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
    school_id bigint,
    member_id bigint,
    subject varchar(255),
    teacher varchar(255),
    rating float(53) not null,
    content varchar(255),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    foreign key (school_id) references school(id),
    foreign key (member_id) references member(id)
) engine=InnoDB DEFAULT CHARACTER SET utf8mb4;

create table school_auth (
    id bigint not null auto_increment,
    member_id bigint,
    school_id bigint,
    accept enum ('YES','NO'),
    reject_reason varchar(255),
    url varchar(255),
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id),
    foreign key (member_id) references member(id),
    foreign key (school_id) references school(id)
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
