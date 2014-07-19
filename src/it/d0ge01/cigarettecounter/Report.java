package it.d0ge01.cigarettecounter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Report extends Activity{
	
	private ListView listaReport;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		ArrayList reports = new ArrayList();
		
		reports.add(new ReportRecord(15, 07, 2014, 15));
		reports.add(new ReportRecord(16, 07, 2014, 13));
		reports.add(new ReportRecord(17, 07, 2014, 18));
		reports.add(new ReportRecord(18, 07, 2014, 14));
		reports.add(new ReportRecord(19, 07, 2014, 1));
		
		listaReport = (ListView) findViewById(R.id.listReport);
		ReportAdapter madapter = new ReportAdapter(this, reports);
		listaReport.setAdapter(madapter);
		
		
	}
}
