package com.uloo.entity;



@SuppressWarnings("unused")
public class BudgetAnnualBaseOfferScheme implements java.io.Serializable{
	private static final long serialVersionUID = 1L;




	private Integer id;
	/** 年份 */
	private Integer year;
	/** 客户类型1、一年4次以上2、一年3次3、一年2次4、一年一次5、两年内未回厂6、新增客户预测 */
	private Integer customerType;
	/** 保有客户基数 */
	private Integer basicNumberCustomer;
	/** 回厂台次 */
	private Integer vehicleReturnTimes;
	/** 占总回厂占比 */
	private Double returnRatio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public Integer getBasicNumberCustomer() {
		return basicNumberCustomer;
	}

	public void setBasicNumberCustomer(Integer basicNumberCustomer) {
		this.basicNumberCustomer = basicNumberCustomer;
	}

	public Integer getVehicleReturnTimes() {
		return vehicleReturnTimes;
	}

	public void setVehicleReturnTimes(Integer vehicleReturnTimes) {
		this.vehicleReturnTimes = vehicleReturnTimes;
	}

	public Double getReturnRatio() {
		return returnRatio;
	}

	public void setReturnRatio(Double returnRatio) {
		this.returnRatio = returnRatio;
	}

	@Override
	public String toString() {
		return "BudgetAnnualBaseOfferScheme{" +
				"id=" + id +
				", year=" + year +
				", customerType=" + customerType +
				", basicNumberCustomer=" + basicNumberCustomer +
				", vehicleReturnTimes=" + vehicleReturnTimes +
				", returnRatio=" + returnRatio +
				'}';
	}
}