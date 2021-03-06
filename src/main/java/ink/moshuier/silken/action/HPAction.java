package ink.moshuier.silken.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import ink.moshuier.silken.common.MsgConstants;
import ink.moshuier.silken.entity.Music;
import ink.moshuier.silken.entity.Poster;
import ink.moshuier.silken.entity.essay.Essay;
import ink.moshuier.silken.service.EssayService;
import ink.moshuier.silken.service.MusicService;
import ink.moshuier.silken.service.PosterService;

public class HPAction {
	@Resource(name="posterService")
	private PosterService posterService;
	@Resource(name="musicService")
	private MusicService musicService;
	@Resource(name="essayService")
	private EssayService essayService;
	List<Poster> posters;
	List<Essay> essays;
	List<Music>  musics;
	private static Logger logger = LogManager.getLogger(HPAction.class.getName());
	public String load(){
		posters = posterService.list();
		essays = essayService.getHomepageList();
		musics = musicService.getHomepageList();
		return MsgConstants.HOMEPAGE;
	}
	@JSON(serialize=false)
	public PosterService getPosterService() {
		return posterService;
	}
	public void setPosterService(PosterService posterService) {
		this.posterService = posterService;
	}
	@JSON(serialize=false)
	public MusicService getMusicService() {
		return musicService;
	}
	public void setMusicService(MusicService musicService) {
		this.musicService = musicService;
	}
	@JSON(serialize=false)
	public EssayService getEssayService() {
		return essayService;
	}
	public void setEssayService(EssayService essayService) {
		this.essayService = essayService;
	}
	@JSON(serialize=false)
	public List<Poster> getPosters() {
		return posters;
	}
	public void setPosters(List<Poster> posters) {
		this.posters = posters;
	}
	@JSON(serialize=false)
	public List<Essay> getEssays() {
		return essays;
	}
	public void setEssays(List<Essay> essays) {
		this.essays = essays;
	}
	@JSON(serialize=false)
	public List<Music> getMusics() {
		return musics;
	}
	public void setMusics(List<Music> musics) {
		this.musics = musics;
	}
	

	
}
