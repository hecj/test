ALTER TABLE t_user ADD belongTo INT (1) DEFAULT 1 COMMENT '用户所属类型(0:市场,1:运营)';
ALTER TABLE `t_user` ADD INDEX index_t_user_belongTo ( `belongTo` ) ;
--ALTER TABLE t_linkSource ADD remark VARCHAR (255) DEFAULT NULL COMMENT '备注' ;
-- create t_linkSource
CREATE TABLE `t_linkSource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `linkName` varchar(255) DEFAULT NULL COMMENT '推广渠道名称',
  `linkKey` varchar(255) DEFAULT NULL COMMENT 'source关键字',
  `linkUrl` varchar(255) DEFAULT NULL COMMENT '链接',
  `linkLayout` varchar(255) DEFAULT NULL COMMENT '位置',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `publishiStatus` tinyint(4) DEFAULT '0' COMMENT '发布状态',
  `publishTime` datetime DEFAULT NULL COMMENT '发布时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `createBy` bigint(20) DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ;

--create t_linkSource_total
CREATE TABLE `t_linkSource_total` (
  `id` bigint(20) NOT NULL COMMENT '序列',
  `linkSource_id` bigint(20) DEFAULT NULL COMMENT '渠道id',
  `reg_count` int(12) DEFAULT NULL COMMENT '注册人数',
  `auth_count` int(12) DEFAULT NULL COMMENT '实名人数',
  `remark` varchar(255) DEFAULT NULL COMMENT '首投人数',
  `first_invest_money` decimal(18,2) DEFAULT NULL COMMENT '首投金额（元）',
  `invest_count` int(12) DEFAULT NULL COMMENT '总投资次数（元）',
  `count_at` bigint(20) DEFAULT NULL COMMENT '更新时间（统计时间）',
  `create_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_at` bigint(20) DEFAULT NULL COMMENT '    修改时间',
  PRIMARY KEY (`id`),
  KEY `index_t_linkSource_total_linkSource_id` (`linkSource_id`) USING HASH
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ;


