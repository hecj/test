var refresh = function(wxShareTitle, wxShareDesc, locationUrl) {
     var wx_share_img_url = __imgHead__ + "/img/logo3.png";
     jQuery.getScript("/index_sign.jsp?url=" + encodeURIComponent(location.href.split('#')[0]), function() {
       wx.config({
         debug: false,
         appId: wx_share_appid,
         timestamp: wx_timestamp,
         nonceStr: wx_nonceStr,
         signature: wx_signature,
         jsApiList: [
           'onMenuShareAppMessage',
           'onMenuShareTimeline'
         ]
       });
       wx.ready(function() {
         //----分享到朋友----
         wx.onMenuShareAppMessage({
           title: wxShareTitle,
           desc: wxShareDesc,
           link: locationUrl,
           imgUrl: wx_share_img_url,
           trigger: function(res) {
            
           },
           success: function(res) {

           },
           cancel: function(res) {

           },
           fail: function(res) {
             alert(JSON.stringify(res));
           }
         });
         //----分享到朋友圈----
         wx.onMenuShareTimeline({
           title: wxShareTitle,
           link: locationUrl,
           imgUrl: wx_share_img_url,
           trigger: function(res) {

           },
           success: function(res) {},
           cancel: function(res) {

           },
           fail: function(res) {
             alert(JSON.stringify(res));
           }
         });
       });
       wx.error(function(res) {
         alert(res.errMsg);
       });
     });
   }
   refresh(__shareTitle1__, __shareDesc1__, __wwwHead__);
