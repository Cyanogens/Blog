create table blog
(
    id          bigint auto_increment
        primary key,
    title       varchar(255) not null comment '标题',
    mdcontent   varchar(255) not null comment 'md格式的内容',
    htmlcontent varchar(255) not null comment 'html格式的内容',
    summary     varchar(255) not null comment '内容',
    cid         bigint       not null comment '标签id',
    uid         int          not null comment '用户id
',
    publishdata datetime     not null comment '发布数据',
    state       tinyint(1)   not null comment '状态',
    pageview    bigint       not null comment '浏览量',
    likecount   bigint       not null comment '点赞数'
)
    comment '博客内容表';

create table `blog-category`
(
    id  bigint auto_increment
        primary key,
    bid bigint not null comment '博客id',
    cid bigint not null comment '标签id'
);

create table `blog-likes`
(
    id  bigint auto_increment
        primary key,
    bid bigint not null comment '博客id',
    uid int    not null comment '用户id'
);

create table category
(
    id           int auto_increment
        primary key,
    categoryname varchar(255) not null comment '标签名'
);

create table comment
(
    id         bigint auto_increment
        primary key,
    pid        bigint default -1 not null comment '所评论的id',
    bid        bigint            not null comment '博客id',
    uid        int               not null comment '用户id',
    content    varchar(255)      not null comment '评论内容',
    createdate datetime          not null comment '发布时间',
    likecount  bigint            not null comment '点赞数'
);

create table `comment-likes`
(
    id  bigint auto_increment
        primary key,
    cid bigint not null comment '评论id',
    uid int    not null comment '用户id'
);

create table user
(
    id       int auto_increment
        primary key,
    nickname varchar(255) not null comment '昵称',
    username varchar(255) not null comment '用户名',
    password varchar(255) not null comment '密码',
    isenable tinyint(1)   not null comment '用户是否可用',
    sex      varchar(10)  not null comment '性别'
);

