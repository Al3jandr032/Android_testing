package com.alex.prueba;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class MainActivity extends Activity {
	private static final String CUENTA_TOTAL = "CUENTA_TOTAL";
	private static final String PROPINA_ACTUAL = "PROPINA_ACTUAL";
	private static final String CUENTA_SIN_PROPINA = "CUENTA_SIN_PROPINA";

	private double cuentasinpropina;
	private double cantidadpropina;
	private double cuentafinal;
	
	EditText cuentasinpropinaET;
	EditText cantidadpropinaET;
	EditText cuentafinalET;
	
	SeekBar propinaSeekBar; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

        if(savedInstanceState == null){
        	
        	cuentasinpropina=0.0;
        	cantidadpropina=0.15;
        	cuentafinal= 0.0;
        	
        }else{
       
        	cuentasinpropina= savedInstanceState.getDouble(CUENTA_TOTAL);
        	cantidadpropina = savedInstanceState.getDouble(PROPINA_ACTUAL);
        	cuentafinal     = savedInstanceState.getDouble(CUENTA_SIN_PROPINA);
        }
        cuentasinpropinaET = (EditText) findViewById(R.id.cuentaEditText);
    	cantidadpropinaET= (EditText) findViewById(R.id.propinaEditText);
    	cuentafinalET= (EditText) findViewById(R.id.cunatafinalEditText);
        
    	propinaSeekBar = (SeekBar)findViewById(R.id.seekBar1);
    	
    	propinaSeekBar.setOnSeekBarChangeListener(propinaSeekBarListener);
    	
    	cuentasinpropinaET.addTextChangedListener(cuentasinpropinalistener);
	}
	
	private TextWatcher cuentasinpropinalistener = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			try{
				cuentasinpropina = Double.parseDouble(s.toString());
				
			}
			catch(NumberFormatException e){
				cuentasinpropina= 0.0;
				
			}
			
			Actualizarcuentaypropina();
			
		}

		
    	
    };
    private void  Actualizarcuentaypropina(){
    	cantidadpropina = Double.parseDouble(cantidadpropinaET.getText().toString());
    	
    	cuentafinal = cuentasinpropina +(cuentasinpropina * cantidadpropina);
    	
    	cuentafinalET.setText(String.format("%.02f", cuentafinal));
    }
    protected void onSavedInstanceState(Bundle outState){
    	super.onSaveInstanceState(outState);
    	
    	outState.putDouble(CUENTA_TOTAL, cuentafinal);
    	outState.putDouble(PROPINA_ACTUAL, cantidadpropina);
    	outState.putDouble(CUENTA_SIN_PROPINA, cuentasinpropina);
    	
    }
   private OnSeekBarChangeListener propinaSeekBarListener = new OnSeekBarChangeListener(){

		@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			// TODO Auto-generated method stub
			cantidadpropina = (double)propinaSeekBar.getProgress()*0.01;
			cantidadpropinaET.setText(Double.toString(cantidadpropina));
			Actualizarcuentaypropina();
			
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }; 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
