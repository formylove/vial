/**
 * 
 */
package ink.moshuier.silken.entity.gallery;

/**
 * @author Administrator
 *Continent
 */
public enum Continent {
NorthAmerica(0,"����"),MiddleAmerica(1,"����"),SouthAmerica(2,"����"),EastAsia(3,"����"),SoutheastAsia(4,"������"),
SouthAsia(5,"����"),MiddleAsia(6,"����"),EasternEurope(7,"��ŷ"),NorthernEurope(8,"��ŷ"),
WesternEurope(9,"��ŷ"),Oceania(10,"������"),Africa(11,"����"),Antarctica(12,"�ϼ���");
	private String name;
	private int index;
	private Continent(int index,String name){
		this.name = name;
		this.index = index;
	}
	public String getName() {
		System.out.println("continent getName");
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	static public Continent get(String name) {
		for(Continent c:Continent.values())
		{
			if(c.getName().equals(name)){
				return c;
			}
		}
		return Continent.Antarctica;
	}
	static public Continent get(int index) {
		for(Continent c:Continent.values())
		{
			if(c.getIndex() == index){
				return c;
			}
		}
		return Continent.Antarctica;
	}
	
}
