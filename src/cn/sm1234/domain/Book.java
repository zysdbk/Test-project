package cn.sm1234.domain;

public class Book {

	private Integer id;
	private String name;
	private Integer customerid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", customerid=" + customerid + "]";
	}
	
	
}
