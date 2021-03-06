package ink.moshuier.silken.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.stereotype.Component;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.common.StrUtils;
import ink.moshuier.silken.common.TimeManager;
import ink.moshuier.silken.entity.gallery.Corporation;
import ink.moshuier.silken.entity.gallery.Dynasty;
import ink.moshuier.silken.entity.gallery.Nation;
import ink.moshuier.silken.entity.gallery.item.Figure;
@Component("opus")
@Entity
@Table(name = "opus")
public class Opus{
	@Id @Column(name="opus_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String name;
	public String name_en;
	public String spoiler;
	public String cover;
	public String rating;
	public boolean rec_flag = false;
	public String remark;
	@OneToOne(targetEntity = Nation.class)
	@JoinColumn(name="nation_id")
	public Nation nation;
	@OneToOne(targetEntity = Corporation.class)
	@JoinColumn(name="corporation_id")
	public Corporation pictures;
	@OneToOne(targetEntity = Dynasty.class)
	@JoinColumn(name="dynasty_id")
	public Dynasty dynasty;
	@OneToOne(targetEntity = Figure.class)
	@JoinColumn(name="author_directior_id")
	public Figure author_directior;
	@OneToMany(targetEntity = Figure.class,fetch=FetchType.EAGER)
	@JoinColumn(name="protagonists_id")
	public List<Figure> protagonists =new ArrayList<Figure>();
	@ManyToMany(targetEntity=Genre.class, fetch = FetchType.EAGER)
	@JoinTable(name="Opus_Genre",
	joinColumns=@JoinColumn(name="opus_id",referencedColumnName="opus_id"),
	inverseJoinColumns=@JoinColumn(name="genre_id",referencedColumnName="genre_id"))
	public List<Genre> genres = new ArrayList<Genre>();
	public String type;
	public String publish_date;
	public String view_time;
	@Column(updatable=false)
	public String create_date;
	@Column(updatable=false)
	public String create_time;
	public boolean exhibit_flag = true;
	public boolean del_flag = false;
	@OneToMany(targetEntity=Note.class,mappedBy="opus")
	public Set<Note> notes = new HashSet<Note>();
	@Transient
	public final String splitTag = ",";
	@Transient
	public Map<String,String> meta;
	public Opus(){
		Log.print("opus", "created");
		setCreate_date(TimeManager.getDate());
		setCreate_time(TimeManager.getTime());
	}
	
	public Opus(String name_en, String remark) {
		super();
		System.out.println("opus created");
		this.name_en = name_en;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Opus [id=" + id + ", name=" + name + ", original_name=" + name_en 
				+ ", rating=" + rating + ", rec_flag=" + rec_flag + ", remark=" + remark + ", nation="
				+ nation + ", dynasty=" + dynasty + ", author_directior=" + author_directior + ", protagonists="
				+ protagonists + ", genre=" + genres.size() + ", type=" + type + ", publish_date=" + publish_date
				+ ", view_time=" + view_time + ", create_date=" + create_date + ", create_time=" + create_time
				+ ", del_flag=" + del_flag + ", isSingle=" + (notes.size() == 0) + ", splitTag=" + splitTag + ", meta="
				+ meta + "]";
	}
//	public int hashCode() {
//		return id.hashCode()*31;
//	}
//	public boolean equals(Object o) {
//		return (o instanceof Opus && this.id == ((Opus) o).id);
//	}
	public Map<String,String> getMeta() {
		meta = new LinkedHashMap<String,String>();
		if(isBook()){
			meta.put("����", name);
			
			if("�й�".equals(nation)){
				if(!"nope".equals(dynasty)){
					meta.put("����", author_directior+"("+dynasty+")");
				}else{
					if(author_directior == null){
						meta.put("����", author_directior.getName());
					}
				}
				
			}else if(!"nope".equals(nation)){
				meta.put("����", author_directior+"("+nation+")");
				if(!StrUtils.isEmpty(name_en)){
					meta.put("����", name_en);
				}
				
			}else{
				if(author_directior == null){
					meta.put("����", author_directior.getName());
				}
			}
			if(protagonists.size() > 0){
				String names = null;
				for(Figure f:protagonists){
					names += f.getName();
				}
				meta.put("����", names);
			}
			if(genres.isEmpty()){
//				meta.put("����", NoteService.getGenreName(genre));
			}
		}else{
				if(!"�й�".equals(nation) && !"nope".equals(nation)){
						meta.put("��Ӱ��", name+"("+nation+")");
						if(!StrUtils.isEmpty(name_en)){
							meta.put("ԭ��", name_en);
						}
						
				}else {
					meta.put("��Ӱ��", name);
				}
				if(null == author_directior){
					meta.put("����", author_directior.getName());
				}
				if(protagonists.size() > 0){
					String names = null;
					for(Figure f:protagonists){
						names += f.getName();
					}
					meta.put("����", names);
				}
				if(rec_flag){
					if(!StrUtils.isEmpty(remark)){
						meta.put("�Ƽ�����", remark);
					}
				}
					if(!StrUtils.isEmpty(rating)){
						meta.put("����", rating);
					}
				if(genres.isEmpty()){
//					meta.put("����", NoteService.getGenreName(genre));
				}
				if(!StrUtils.isEmpty(publish_date)){
					meta.put("��ӳʱ��", publish_date+"��");
				}
		}
		return meta;
	}
	
	public String getSpoiler() {
		return spoiler;
	}

	public void setSpoiler(String spoiler) {
		this.spoiler = spoiler;
	}

	public void setId(Integer id) {
		System.out.println("setId Integer");
		this.id = id;
	}
	public void setId(int id) {
		System.out.println("setId int");
		this.id = id;
	}
	public void setId(String[] id) {
		System.out.println("setId String[]");
		this.id = Integer.parseInt(id[0]);
	}
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public boolean isBook(){
		if("book".equals(type)){
			return true;
		}else{
			return false;
		}
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
   	public Corporation getPictures() {
		return pictures;
	}

	public void setPictures(Corporation pictures) {
		this.pictures = pictures;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public boolean isExhibit_flag() {
		return exhibit_flag;
	}

	public void setExhibit_flag(boolean exhibit_flag) {
		this.exhibit_flag = exhibit_flag;
	}

	public void setProtagonists(List<Figure> protagonists) {
		this.protagonists = protagonists;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public Dynasty getDynasty() {
		return dynasty;
	}

	public void setDynasty(Dynasty dynasty) {
		this.dynasty = dynasty;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public boolean isRec_flag() {
		return rec_flag;
	}
	public void setRec_flag(boolean rec_flag) {
		this.rec_flag = rec_flag;
	}
	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public Figure getAuthor_directior() {
		return author_directior;
	}

	public void setAuthor_directior(Figure author_directior) {
		this.author_directior = author_directior;
	}

	public List<Figure> getProtagonists() {
		return protagonists;
	}
	
	public List<Integer> getProtagonistIds() {
		List<Integer> ids = new ArrayList<Integer>();
		for(Figure f:protagonists){
			ids.add(f.getId());
		}
		return ids;
	}

	public void setProtagonistIds(String[] protagonists) {
		for(String id:protagonists){
			this.protagonists.add(new Figure(Integer.valueOf(id)));
		}
	}

	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}

	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String getView_time() {
		return view_time;
	}
	public void setView_time(String view_time) {
		this.view_time = view_time;
	}
	public boolean isDel_flag() {
		return del_flag;
	}
	public void setDel_flag(boolean del_flag) {
		this.del_flag = del_flag;
	}

}
