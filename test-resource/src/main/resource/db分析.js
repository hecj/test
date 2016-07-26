
/**
 * 添加投资
 * borrowId ：标Id
 * userId ：用户Id
 * dealPWD : 交易密码
 * investAmount ：投资金额
 * totalSlice : 投资份数
 * fundPlanId : 非理财计划fundPlanId默认为0
 * isRebateUsable ：是否使用返利余额 1表示是 0表示不是
 * usedCoupon ： 是否使用可用余额 1表示是 0表示不是
 * investTypeParam ： 投资方式（0 PC手动投标  1 移动端   2自动投标 3微信服务号）
 */
addBorrowInvest(long borrowId, long userId,
			String dealPWD, BigDecimal investAmount, String basePath,
			long totalSlice, int investFrom, long fundPlanId,
			int isRebateUsable, int usedCoupon, long couponID ,long  investTypeParam)
			
			
------------还款逻辑---------
frontpayAction FrontMyPaymentAction.preSubmitPay 还款方法




------------db-----------

duomeidai.t_user : 用户表
duomeidai.t_person : 个人资料表
duomeidai.t_idno_auth ：实名认证成功记录
duomeidai.t_idno_auth_history：实名认证历史记录
duomeidai.t_user_coupon:用户现金卷表
duomeidai.t_operation_log：系统操作日志
duomeidai.t_materialsauth：上传资料中的各项资料认证

duomeidai.t_linksource:source表
duomeidai.t_rebate_invite_relation：注册推荐返利对记录
duomeidai.t_rebate_invite_code:返利邀请随机码表
duomeidai.user_reserve：经纪人-客户预约
duomeidai.user_issued_notes:用户下发记录表

duomeidai.t_invest : 投资表
duomeidai.t_borrow : 借款表

duomeidai.autoInvest : 自动投标设置表
duomeidai.autoInvest_history : 自动投标投资记录表

duomeidai.t_debt : 债权转让表
duomeidai.t_invest_debt : 债权投资表

duomeidai.t_recharge_detail：充值表
duomeidai.t_recharge_order_temp：充值订单临时表（充值成功）
duomeidai.t_rechargeNumber_records：充值成功流水表
duomeidai.t_fundrecord ：资金流动信息

duomeidai.t_invest_repayment ：相对投资人的还款记录
duomeidai.t_repayment ：借款中的还款记录相对借款人
duomeidai.t_pre_repayment：预还款记录
duomeidai.t_borrow_withdrawal : 借款放款记录表

duomeidai.t_thirdPart_syncAccountRecord：用户同步账号记录表
duomeidai.t_thirdPart_syncAccountRecord_temp：用户同步账号记录临时表
duomeidai.t_thirdPart_TrsBidNoticeRecord:标的成功通知记录表
duomeidai.t_thirdPart_transRecord ：第三方转账流水表
duomeidai.t_thirdPart_withdrawalRecord ：提现首信易响应记录表

duomeidai.t_thirdPart_trsCutPayment_temp : 代扣记录表（主表）
duomeidai.t_thirdPart_trsCutPayment_temp_record : 代扣记录临时表
duomeidai.t_thirdPart_trsCutPaymentRecord : 代扣记录表

duomeidai.t_borrow_withdrawal :借款放款记录表
duomeidai.t_withdraw ：用户提现记录表

duomeidai.t_approve_notice_template ：提醒模版记录
duomeidai.t_sms_send_history ：短信发送历史记录
duomeidai.t_notice_task : 通知任务
duomeidai.t_notice_task_log :通知任务执行记录
duomeidai.t_sms_unsubscribe 短信黑名单表（系统发的短信，用户主动推送的不受控制）

duomeidai.tbl_bank_info : 银行列表
duomeidai.t_bankcard：用户绑定银行卡信息
duomeidai.bank_limit：银行支付限额表
duomeidai.tbl_bank_info：银行信息表

duomeidai.bt_rights：管理系统对应菜单跳转拦截
duomeidai.t_admin：管理员
duomeidai.t_mail：用户站内信
duomeidai.t_news: 公告

------------首信易接口分析-------------
短信、绑卡、实名、提现、充值、投标失败


帐号同步接口：http://p2p.yizhifubj.com/duomei/synclient
TransCode（交易码） SYN001001
10000	账户同步开通          已用(开户)
10001	同步用户身份证信息       已用(实名) 
10002	同步用户手机号码
10003	查询账户是否开通
10004	同步用户银行卡信息     已用 绑卡 (RechargeAction.addBankInfo()) 增加提现银行卡
10005	查询银行卡验证结果	

邮件接口：https://sendcloud.sohu.com/webapi/mail.send.xml
	
短信接口：http://api.yizhifubj.com/merchant/ack/serviceSmsApi.jsp
	
充值接口：http://pay.yizhifubj.com/customer/gb/pay_bank.jsp
		参数带回调接口
------------------提现------------------
提现提交：https://www.duomeidai.com/addWithdraw.action
	     RechargeAction.addWithdraw
	     t_withdraw 添加提现申请 statue : 1   状态(1 默认审核中  2 已提现 3 取消 4转账中 5 失败
	     t_fundrecord 添加资金流水表
	     
后台提现审核：admin/updateWithdrawCheck.action
生成提现记录：t_thirdPart_withdrawalRecord

审核成功
跑任务 查询4转账中的提现

提现查询：http://p2p.yizhifubj.com/duomei/transCashFee
	 提现交易码:TRS001006 
	 18000：提现
	 18002: 查询 
	 
------代扣成功后回调通知---------------------------
String com.longdai.action.front.PayEasyAction.payEasyTrsCutPaymentAgentNotify() 
---------------------------------------

一、数据库范式：
第一范式：数据库中的所有字段都是单一属性，不可再分的。这个单一属性是由基本数据类型所构成的，如整数，浮点数，字符串等。
换句话说：第一范式要求数据库中的表都是二维表。

第二范式：数据库中的表中不存在非关键字段对任一候选关键字段的部分函数依赖。
部分函数依赖是指存在着组合关键字段中的某一关键字决定非关键字的情况。
换句话说：所有单关键字段的表都符合第二范式。

二、数据库，表，字段的命名规范

三、表的拆分
表的垂直拆分：为了控制表的宽度可以进行表的垂直拆分
1.经常一起查询的列到一起
2.text,blob等大字段拆分出到附加表中

表的水平拆分：为了控制表的大小可以进行表的水平拆分 Hash key






   
   