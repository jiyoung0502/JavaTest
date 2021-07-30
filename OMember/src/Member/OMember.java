package Member;

public class OMember {

	// ÇÊµå
	private int stuno;
	private String oname;
	private String obirth;
	
	// getter, setter
	public int getStuno() {
		return stuno;
	}
	public void setStuno(int stuno) {
		this.stuno = stuno;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getObirth() {
		return obirth;
	}
	public void setObirth(String obirth) {
		this.obirth = obirth;
	}
	
	// toString
	@Override
	public String toString() {
		return "OMember [stuno=" + stuno + ", oname=" + oname + ", obirth=" + obirth + "]";
	}
	
	// Constructor
	public OMember() {
		super();
	}
	
	public OMember(int stuno, String oname, String obirth) {
		super();
		this.stuno = stuno;
		this.oname = oname;
		this.obirth = obirth;
	}
	
	
}
