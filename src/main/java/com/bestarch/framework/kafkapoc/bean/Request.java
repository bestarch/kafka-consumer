package com.bestarch.framework.kafkapoc.bean;

import java.io.Serializable;

/**
 * 
 * @author abhishek.srivastava4
 *
 */
public class Request implements Serializable {

	private int requestId;
	
	private String requestName;
	
	private String details;
	
	private double cost;
	
	private String serialNumber;
	
	private String miscDetails;
	
	private int count;
	
	public Request() { }

	public Request(int requestId, String requestName, String details, double cost, String serialNumber,
			String miscDetails, int count) {
		this.requestId = requestId;
		this.requestName = requestName;
		this.details = details;
		this.cost = cost;
		this.serialNumber = serialNumber;
		this.miscDetails = miscDetails;
		this.count = count;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMiscDetails() {
		return miscDetails;
	}

	public void setMiscDetails(String miscDetails) {
		this.miscDetails = miscDetails;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + requestId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (requestId != other.requestId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", requestName=" + requestName + ", details=" + details + ", cost="
				+ cost + ", serialNumber=" + serialNumber + ", miscDetails=" + miscDetails + ", count=" + count + "]";
	}
	
}
