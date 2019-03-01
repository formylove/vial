package ink.moshuier.silken.service;

import ink.moshuier.silken.entity.Genre;
import ink.moshuier.silken.entity.Opus;
import ink.moshuier.silken.service.base.BaseService;

public interface GenreService extends BaseService<Genre>{
	public Genre get(String name);
	void injectGenres(Opus opus, String[] genres);
}
