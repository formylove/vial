package ink.moshuier.silken.service;

import java.util.List;

import ink.moshuier.silken.entity.Comment;
import ink.moshuier.silken.service.base.BaseService;
import net.sf.json.JSONObject;

public interface CommentService extends BaseService<Comment>{
	final int PAGESIZE = 10;
	public boolean add(Comment comment) ;
	public List<Comment> getOnePage(int total,int page,String appendence,int append_id);
	public int getLastUnit(String type,int id);
	public JSONObject getPage(int page,Comment comment);
}
