package it.d0ge01.cigarettecounter;

public class ReportRecord {
	private int giorno;
	private int mese;
	private int anno;
	
	private int n;
	public ReportRecord(int giorno, int mese, int anno, int n) {
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
		
		this.n = n;
	}
	
	public void setDay(int n) {
		this.giorno = n;
	}
	
	public int getDay() {
		return this.giorno;
	}
	
	public void setMonth(int n) {
		this.mese = n;
	}
	
	public int getMonth() {
		return this.mese;
	}
	
	public void setYear(int n) {
		this.anno = n;
	}
	
	public int getYear() {
		return this.anno;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	public int getN() {
		return this.n;
	}
}
