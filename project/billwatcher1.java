package project;

import java.sql.Date;

public class billwatcher1 {
      String cname;
      Date sdate,edate;
      float cowq,buffq,cowamnt,buffamnt,total;
      int status;
      Date paydate;
	public billwatcher1(String cname, Date sdate, Date edate, float cowq, float buffq, float cowamnt, float buffamnt,
			float total, int status, Date paydate) {
		this.cname = cname;
		this.sdate = sdate;
		this.edate = edate;
		this.cowq = cowq;
		this.buffq = buffq;
		this.cowamnt = cowamnt;
		this.buffamnt = buffamnt;
		this.total = total;
		this.status = status;
		this.paydate = paydate;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	public float getCowq() {
		return cowq;
	}
	public void setCowq(float cowq) {
		this.cowq = cowq;
	}
	public float getBuffq() {
		return buffq;
	}
	public void setBuffq(float buffq) {
		this.buffq = buffq;
	}
	public float getCowamnt() {
		return cowamnt;
	}
	public void setCowamnt(float cowamnt) {
		this.cowamnt = cowamnt;
	}
	public float getBuffamnt() {
		return buffamnt;
	}
	public void setBuffamnt(float buffamnt) {
		this.buffamnt = buffamnt;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getPaydate() {
		return paydate;
	}
	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
      
      
}
