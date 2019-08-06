package com.core;

public class SectionCount implements Comparable<SectionCount>{
	private String section;
	private Integer trafficCount;

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Integer getTrafficCount() {
		return trafficCount;
	}

	public void setTrafficCount(Integer trafficCount) {
		this.trafficCount = trafficCount;
	}

	public SectionCount(String section, Integer trafficCount) {
		super();
		this.section = section;
		this.trafficCount = trafficCount;
	}

	@Override
	public int compareTo(SectionCount o) {		
		return trafficCount - o.trafficCount;
	}
	
	@Override
	public String toString()
	{
		return section + "\t" + trafficCount;
	}
	
	public void inc()
	{
		trafficCount ++;
	}
}
