package project;

public class customerdet1 {

	String cname,Mobile;
    String address,locality;
    Float cowq,buffq;
    String dos;
	public customerdet1(String cname, String mobile, String address, String locality, Float cowq, Float buffq,
			String dos) {
		super();
		this.cname = cname;
		Mobile = mobile;
		this.address = address;
		this.locality = locality;
		this.cowq = cowq;
		this.buffq = buffq;
		this.dos = dos;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public Float getCowq() {
		return cowq;
	}
	public void setCowq(Float cowq) {
		this.cowq = cowq;
	}
	public Float getBuffq() {
		return buffq;
	}
	public void setBuffq(Float buffq) {
		this.buffq = buffq;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
    

}
