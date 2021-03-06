<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="essay-author-wrapper clearfix">
				<div class="essay-author">
					<p class="title">
						文章作者
					</p>
						<div class="clearfix">
							<a href="${essay.author_link}" target="_blank" class="avatar-wrapper">
								<img src="${essay.portrait}" alt="${essay.author}" class="avatar rounded"/>
							</a>
							<div class="essay-author-info">
								<div class="cell">
									<a href="${essay.author_link}" target="_blank" class="essay-author-name">
										${essay.author}
									</a>
									<p class="essay-author-sign" title="${essay.author_desc}">${essay.author_desc}</p>
								</div>
							</div>
						</div>
										</div>
				<div class="copyright">
					<p class="title">
						版权声明
					</p>
					<div class="copyright-ct">
						「${pageScope.sitename}网」专栏文章，版权所有
					</div>
				</div>
			</div>