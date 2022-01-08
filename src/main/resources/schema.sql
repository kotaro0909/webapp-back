create sequence IF NOT EXISTS hibernate_sequence start with 1 increment by 1;

    create table IF NOT EXISTS Item (
       itemId bigint not null,
        itemCode varchar(20),
        itemName varchar(255),
        salesPrice decimal(12,2),
        stockPrice decimal(12,2),
        primary key (itemId)
    );

    create table IF NOT EXISTS LoginUser (
       loginUserId bigint not null,
        loginId varchar(20),
        loginPassword varchar(255),
        userName varchar(255),
        primary key (loginUserId)
    );

    create table IF NOT EXISTS OrderDetails (
       orderDetailId bigint not null,
        itemCode varchar(20),
        itemId bigint,
        itemName varchar(255),
        orderNumber bigint,
        salesPrice decimal(12,2),
        primary key (orderDetailId)
    );

    create table IF NOT EXISTS OrderMain (
       orderMainId bigint not null,
        itemCode1 varchar(20),
        itemCode2 varchar(20),
        itemName1 varchar(255),
        itemName2 varchar(255),
        loginUserId bigint,
        orderCode varchar(20),
        orderNumber1 bigint,
        orderNumber2 bigint,
        primary key (orderMainId)
    );
