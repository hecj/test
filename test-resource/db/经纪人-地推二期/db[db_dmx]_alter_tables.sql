ALTER TABLE qr_code ADD valid_day INT(3) DEFAULT 0 COMMENT '有效提成时间' ;
ALTER TABLE broker_role_relation ADD remark VARCHAR (255) DEFAULT NULL COMMENT '备注' ;

-- 初始化数据
UPDATE duomeidai.t_user u SET u.belongTo=0 WHERE u.id IN (
SELECT t.user_id FROM db_dmwx.promotion_relation t 
);
UPDATE duomeidai.t_user u SET u.belongTo=0 WHERE u.source IS NOT NULL ;