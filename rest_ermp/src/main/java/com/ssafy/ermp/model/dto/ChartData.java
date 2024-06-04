package com.ssafy.ermp.model.dto;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ChartData {
	private static String[] colorList = {
	                                     "rgba(100,149,237,0.8)",
	                                     "rgba(255,69,0,0.8)",
	                                     "rgba(50,205,50,0.8)",
	                                     "rgba(255,215,0,0.8)",
	                                     "rgba(147,112,219,0.8)",
	                                     "rgba(169,169,169,0.8)",
	                                     "rgba(255,140,0,0.8)",
	                                     "rgba(255,105,180,0.8)",
	                                     "rgba(210,105,30,0.8)",
	                                     "rgba(0,255,255,0.8)",
	                                     "rgba(255,0,255,0.8)",
	                                     "rgba(0,128,128,0.8)",
	                                     "rgba(107,142,35,0.8)",
	                                     "rgba(50,205,50,0.8)",
	                                     "rgba(75,0,130,0.8)",
	                                     "rgba(148,0,211,0.8)",
	                                     "rgba(255,215,0,0.8)",
	                                     "rgba(169,169,169,0.8)",
	                                     "rgba(128,0,0,0.8)",
	                                     "rgba(64,224,208,0.8)",
	                                     "rgba(0,0,128,0.8)",
	                                     "rgba(255,127,80,0.8)",
	                                     "rgba(210,180,140,0.8)",
	                                     "rgba(216,191,216,0.8)"
	};



	
	// 날짜에 해당하는 레이블
	private List<String> labels;
	
	private List<ChartDataSet> datasets;
	
	
	private class ChartDataSet {
		private String label; // 운동 타입	에 해당하는 레이블
		private String backgroundColor;
		private List<Integer> data; // 해당하는 날짜(idx)에서의 데이터
		
		public ChartDataSet() {
		}

		public ChartDataSet(String label, String backgroundColor, List<Integer> data) {
			this.label = label;
			this.backgroundColor = backgroundColor;
			this.data = data;
		}

		public ChartDataSet(String label, String backgroundColor) {
			this.label = label;
			this.backgroundColor = backgroundColor;
			this.data = new ArrayList<>();
			for(int i =0; i<labels.size(); i++) {
				this.data.add(0);				
			}
		}

		public String getLabel() {
			return label;
		}



		public void setLabel(String label) {
			this.label = label;
		}



		public String getBackgroundColor() {
			return backgroundColor;
		}



		public void setBackgroundColor(String backgroundColor) {
			this.backgroundColor = backgroundColor;
		}



		public List<Integer> getData() {
			return data;
		}



		public void setData(List<Integer> data) {
			this.data = data;
		}



		@Override
		public String toString() {
			return "ChartDataSet [label=" + label + ", backgroundColor=" + backgroundColor + ", data=" + data + "]";
		}
	}
	
	
	
	public ChartData() {
	}

	public ChartData(List<String> labels, List<ChartDataSet> datasets) {
		this.labels = labels;
		this.datasets = datasets;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<ChartDataSet> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<ChartDataSet> datasets) {
		this.datasets = datasets;
	}
	
	@Override
	public String toString() {
		return "ChartData [labels=" + labels + ", datasets=" + datasets + "]";
	}
	
	
	// 입력 데이터 가공 위한 메서드들
	
	
	public ChartData(List<Routine> routines, String startDate, String endDate) {
		labels = new ArrayList<>();
		datasets = new ArrayList<>();
		makeLabels(startDate, endDate);
		makeDatasets(routines);
		
	}
	
	private void makeLabels(String startDate, String endDate) {
		LocalDate curr = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		while(curr.compareTo(end)<=0) {
			labels.add(curr.toString());
			curr = curr.plus(1, ChronoUnit.DAYS);
		}
	}
	
	private void makeDatasets(List<Routine> routines) {
		for(Routine routine: routines) {
			// 타입 존재하는지 찾기
			int datasetIdx = findType(routine.getType());
			// 해당 날짜 찾기
			int dateIdx = findDate(routine.getDay());
			
			// 데이터 주입
			int weight = 1;
			// km면 30배함
			if (routine.getUnit().equals("km"))
				weight = 30;
			if (routine.getUnit().equals("분"))
				weight = 5;
			datasets.get(datasetIdx).getData().set(dateIdx, routine.getAchieveAmount()*weight); 
			
			
		}
	}
	
	private int findType(String type) {
		for(int i=0; i<datasets.size(); i++) {
			if (datasets.get(i).label.equals(type)) {
				return i;
			}
		}
		datasets.add(new ChartDataSet(type, colorList[datasets.size()%colorList.length]));
		return datasets.size()-1;
	}
	
	private int findDate(String day) {
		int idx= 0;
		for(String date : labels) {
			if (date.equals(day)) {
				return idx;
			}
			idx++;
		}
		return -1;
	}

	
	
	
}


