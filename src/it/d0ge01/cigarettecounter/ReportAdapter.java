package it.d0ge01.cigarettecounter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ReportAdapter extends BaseAdapter {
	private Activity mActivity;
	private ArrayList listaReport;
	private static LayoutInflater inflater = null;
	
	public ReportAdapter(Activity a, ArrayList list) {
		this.mActivity = a;
		this.listaReport = list;
		this.inflater = (LayoutInflater) this.mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return this.listaReport.size();
	}
	
	@Override
	public Object getItem(int item) {
		return item;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		if ( convertView == null )
			vi = this.inflater.inflate(R.layout.row, null);
		
		TextView giorno = (TextView) vi.findViewById(R.id.giorno);
		TextView sigarette = (TextView) vi.findViewById(R.id.sigarette);
		
		ReportRecord corrente = (ReportRecord) this.listaReport.get(position);
		giorno.setText("Giorno: " + corrente.getDay()
								  + corrente.getMonth()
								  + corrente.getYear());
		
		
		// Ugly i know, but this force string type in argument
		sigarette.setText("" + corrente.getN());
		
		return vi;
	}
}
