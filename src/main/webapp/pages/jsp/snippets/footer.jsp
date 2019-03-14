<%@page pageEncoding="UTF-8" %>
<div class="footer">
	<div class="foot-ct clearfix">
		<div class="about-wrapper">
			<h4 class="foot-item-head">关于</h4>
			<p><a class="ln-about" target="_blank" href="about/">关于<s:property value="#sitename"/> </a></p>
				<p><a class="ln-about" href="javascript:;" id="contactMe">反馈建议</a></p>
			<p class="icp" style="margin-top: 10px;">© <s:property value="@ink.moshuier.silken.common.TimeManager@getCurrentYear()"/> ⚘ <s:property value="#sitename"/> </p>
		</div>

		<div class="sns-wrapper">
			<h4 class="foot-item-head">关注<s:property value="#sitename"/> </h4>
			<div class="sns-items" style="margin-bottom: 20px;">
				<a class="icon-sns-qq" id="QqAccount">
					<span class="qq-group">
						<s:property value="#sitename"/> QQ
						·531653671
						<br>
						·907251709
					</span>
				</a>
				<a href="QRcode/" target="_blank" class="icon-sns-wechat" id="luooWechatAccount"></a>
				<a href="<s:property value="#weibo"/>" target="_blank" class="icon-sns-weibo"></a>
				<a href="<s:property value="#zhihu"/>" target="_blank" class="icon-sns-zhihu"></a>
			</div>
			<p class="slogan">
				<span class="icon-slogan"></span>
			</p>
		</div>
	</div>
</div>
