create sequence hibernate_sequence start with 1 increment by 1;

    create table Item (
       itemId 主キー（自動生成） not null,
        itemCode 商品コード,
        itemName 商品名,
        salesPrice 販売価格,
        stockPrice 仕入価格,
        primary key (itemId)
    );

    create table LoginUser (
       loginUserId 主キー（自動生成） not null,
        loginId ログインID,
        loginPassword パスワード,
        userName ユーザー名,
        primary key (loginUserId)
    );

    create table OrderDetails (
       orderDetailId 主キー（自動生成） not null,
        itemCode 商品コード,
        itemId 商品テーブル主キー,
        itemName 商品名,
        orderNumber 注文数,
        salesPrice 販売価格,
        primary key (orderDetailId)
    );

    create table OrderMain (
       orderMainId 主キー（自動生成） not null,
        itemCode1 注文商品1.商品コード,
        itemCode2 注文商品2.商品コード,
        itemName1 注文商品1.商品名,
        itemName2 注文商品2.商品名,
        loginUserId ログインユーザ主キー,
        orderCode 注文コード,
        orderNumber1 注文商品1.注文数,
        orderNumber2 注文商品2.注文数,
        primary key (orderMainId)
    );
