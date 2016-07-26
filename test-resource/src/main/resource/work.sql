/*

 PASS_KEY = 4B103DF3D3505AF871EFED63A78E3B75; 
 
*/

/* password : 111111 , dealpwd : 123456 */
select * from t_user u where u.`password`="7f26ab96bc9d7a847eb1af2bd6d72521" and u.dealpwd="67148fba83ee494f9f591737de13d40a";

/* password : 123456 , dealpwd : 111111 */
select * from t_user u where u.`password`="67148fba83ee494f9f591737de13d40a" and u.dealpwd="7f26ab96bc9d7a847eb1af2bd6d72521";

/***************认证的用户***************************/
/* password : 123456 , dealpwd : 111111 */
SELECT
	u.id,u.username,p.cellPhone,u.usableSum
FROM
	t_user u
LEFT JOIN t_person p ON (u.id = p.userId)
WHERE
	u.password = "67148fba83ee494f9f591737de13d40a"
AND u.dealpwd = "7f26ab96bc9d7a847eb1af2bd6d72521"
AND p.idNoAuthStatus = 2;

/**************查询平台中正在发布的标,按规则排序********************/
SELECT
	t.id,
	t.borrowTitle,
	t.hasInvestAmount,
	t.borrowAmount,
	t.annualRate,
	t.deadline,
	t.deadline
FROM
	t_borrow t
WHERE
	t.borrowStatus IN (2)
AND t.annualRate >= 9
AND t.annualRate <= 12
AND t.deadline >= 1
AND t.deadline <= 12
AND t.minTenderedSum < 100000
ORDER BY
	t.annualRate DESC,
	t.deadline ASC,
	t.id ASC;

/**************查询平台中用户自动 投过的标********************/
SELECT
	h.userid,
	h.borrowid,
	sum(h.investamount)
FROM
	autoInvest_history h
WHERE
	h.borrowid IN (
		SELECT
			t.id
		FROM
			t_borrow t
		WHERE
			t.borrowStatus IN (2)
		AND t.annualRate >= 9
		AND t.annualRate <= 12
		AND t.deadline >= 1
		AND t.deadline <= 12
		AND t.minTenderedSum < 100000
		ORDER BY
			t.annualRate DESC,
			t.deadline ASC,
			t.id ASC
	)
AND h.userid = 30000189
GROUP BY
	h.userid,
	h.borrowid;

/******************查询自动投标用户超过2个的************************/
SELECT
	b.bid,
	count(*)
FROM
	(
		SELECT
			t.borrowid bid,
			t.userid
		FROM
			autoInvest_history t
		GROUP BY
			t.borrowid,
			t.userid
	) AS b
GROUP BY
	b.bid
HAVING 	count(*)>=2;

/***************查询本标投资最多的用户*****************/
select uid,sumAmount FROM (
select t.userid uid,sum(t.investamount) sumAmount 
from autoinvest_history t where t.borrowid = 469
group by t.userid order by sumAmount desc limit 0,1) as b;

/*******************游标使用**************************/
CREATE PROCEDURE pro_autoinvest_history_check20() 
BEGIN
DECLARE mBid BIGINT;
#游标结束标志
DECLARE mGo INT DEFAULT TRUE;
#借款总金额
DECLARE mBorrowAmount decimal(18,2) default 0;
#已投金额
DECLARE mHasAmount decimal(18,2) default 0;
#用户UID 
DECLARE mUid BIGINT;
DECLARE _cursor CURSOR FOR 
	select h.borrowid from autoinvest_history h
	group by h.borrowid;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET mGo=FALSE;
#打开游标
OPEN _cursor; 
WHILE mGo DO
	#读取数据赋值
	FETCH _cursor INTO mBid;
  #TODO
	#查询本标总额
	SELECT t.borrowAmount INTO mBorrowAmount FROM t_borrow t where t.id = mBid;
	#查询本标通过自动投标投资最多的用户
	select sumAmount into mHasAmount FROM (
	select t.userid uid,sum(t.investamount) sumAmount 
	from autoinvest_history t where t.borrowid = mBid
	group by t.userid order by sumAmount desc limit 0,1) as b;

	IF mBorrowAmount/2 = mHasAmount THEN
		SELECT * from t_borrow t where t.id = mBid;
  END IF;
	
END WHILE;
#关闭游标
close _cursor; 
END ;

/*查询表字段注释*/
select COLUMN_NAME,COLUMN_TYPE,COLUMN_COMMENT 
from information_schema.`COLUMNS` 
where table_schema = 'duomeidai' and TABLE_NAME='t_user' ;

ALTER TABLE t_user ADD belongTo INT (1) DEFAULT 0 COMMENT '用户所属类型(0:市场,1:运营)' AFTER NAME;

--------递推需求--------------
--用户所属类型
ALTER TABLE t_user ADD belongTo INT (1) DEFAULT 0 COMMENT '用户所属类型(0:市场,1:运营)';
--返点使用db_dmwx.broker_role_relation.commission_invist_rate字段
--添加备注
ALTER TABLE broker_role_relation ADD remark varchar (255) DEFAULT NULL COMMENT '备注' BEFORE commission_invist_rate;
--有效提成时间db_dmwx
ALTER TABLE qr_code ADD valid_day INT (3) DEFAULT 0 COMMENT '有效提成时间' BEFORE update_at;

----------------------------
-- 添加索引
ALTER TABLE t_mail ADD INDEX index_mail_borrow_id (borrowId) USING HASH

ALTER TABLE t_mail DROP INDEX index_mail_borrow_id ;
 
----------分组编号排序-----------------------
 SELECT * FROM (
SELECT
	(select count(*) from t_invest ta where ti.borrowId = ta.borrowId and ti.investTime >= ta.investTime) as rank,
	ti.*
FROM
	t_invest ti
GROUP BY
	ti.borrowId,
	ti.investTime
ORDER BY
	ti.borrowId ASC,
	ti.investTime ASC
) AS tp WHERE tp.rank =1 OR tp.rank =7 OR tp.rank =17 OR tp.rank =27;

-------每个标的第1，7，17--
SELECT
	*
FROM
	(
		SELECT
			(
				SELECT
					count(*)
				FROM
					t_invest ta
				WHERE
					ti.borrowId = ta.borrowId
				AND ti.investTime >= ta.investTime
				AND ta.investTime >= '2015-08-17 00:00:00'
				AND ta.investTime < '2015-08-19 00:00:00'
			) AS rank,
			ti.*
		FROM
			t_invest ti
		WHERE
			ti.investTime >= '2015-08-17 00:00:00'
		AND ti.investTime < '2015-08-19 00:00:00'
		GROUP BY
			ti.borrowId,
			ti.investTime
		ORDER BY
			ti.borrowId ASC,
			ti.investTime ASC
	) AS tp
WHERE
	tp.rank IN (1, 7, 17, 27, 37, 47, 57, 67, 77);


