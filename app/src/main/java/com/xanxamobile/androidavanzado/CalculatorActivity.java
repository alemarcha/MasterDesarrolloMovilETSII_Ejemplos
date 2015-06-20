package com.xanxamobile.androidavanzado;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Esto es una interfaz multiresolución.
 * @author XanXa
 *
 */
public class CalculatorActivity extends Activity {

	

	//=================================================
	// Constants
	//=================================================
	
	//=================================================
	// Fields
	//=================================================
	EditText editText;
	//Funcionalidad botón +
	String suma;

	//Funcionalidad botón -
	String resta;

	//Funcionalidad botón x
	String mult;

	//Funcionalidad botón /
	String div;

	//Funcionalidad botón =
	String igual;
	//Funcionalidad botón c
	String restablecer;


	//=================================================
	// Constructors
	//=================================================
	
	//=================================================
	// override function
	//=================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interfaz_multiresolucion);
		
		//Funcionalidad botón +
		View buttonPlus = findViewById(R.id.buttonPlus);
		buttonPlus.setOnClickListener(new OnClickListenerOp());
		suma=((Button) buttonPlus).getText().toString();
		//Funcionalidad botón -
		View buttonResta = findViewById(R.id.ButtonMinus);
		buttonResta.setOnClickListener(new OnClickListenerOp());
		resta=((Button) buttonResta).getText().toString();
		//Funcionalidad botón x
		View buttonMult = findViewById(R.id.buttonMul);
		buttonMult.setOnClickListener(new OnClickListenerOp());
		mult=((Button) buttonMult).getText().toString();
		//Funcionalidad botón /
		View buttonDiv = findViewById(R.id.ButtonDiv);
		buttonDiv.setOnClickListener(new OnClickListenerOp());
		div=((Button) buttonDiv).getText().toString();
		//Funcionalidad botón =
		View buttonIgual = findViewById(R.id.ButtonEqual);
		buttonIgual.setOnClickListener(new OnClickListenerOp());
		igual=((Button) buttonIgual).getText().toString();
		//Funcionalidad botón c
		View buttonRestablecer = findViewById(R.id.buttonC);
		buttonRestablecer.setOnClickListener(new OnClickListenerOp());
		restablecer=((Button) buttonRestablecer).getText().toString();
		//Funcionalidad botón eliminar
		View buttonEliminar = findViewById(R.id.imageButtonBack);
		buttonEliminar.setOnClickListener(new OnClickListenerEliminar());

		Toast.makeText(this, "Sólo funciona el + el resto de funciones has de implementarlas (ver pdf)", Toast.LENGTH_SHORT).show();
	}
	//=================================================
	// Functions
	//=================================================
	/**
	 * Es la fución que se llamará desde el xml para todos los números, insertará un número en el edit text de arriba
	 * Al ser una fución proveniente de un XML hemos ponerle una View como parámetro y ponerla como pública.
	 * @param v
	 */
	public void insertNumber (View v){
		String numberString = ((Button)v).getText().toString();
//		int number = Integer.parseInt(numberString);
		addText(numberString);
	}
	
	/**
	 * Concatenamos una string a lo que ya haya en el editText
	 * @param stringToConcatenate
	 */
	private void addText(String stringToConcatenate) {
		EditText editTextCalculate = getEditText();
		editTextCalculate.setText(editTextCalculate.getText().toString()+stringToConcatenate);
	}
	


	public class OnClickListenerOp implements OnClickListener {

		public void onClick(View v) {
			EditText editText = getEditText();
			String upText = editText.getText().toString();
			String pulsado=((Button) v).getText().toString();
			if (pulsado.equals(restablecer) ){
				editText.setText("");
			}else if((upText.contains(resta) && upText.lastIndexOf(resta)>0) || upText.contains(suma) || upText.contains(div) || upText.contains(mult)) {
				//Si ya se había usado el plus
				if (upText.lastIndexOf(resta) >0 ) {
					getResultado(upText, resta, pulsado);
				} else if (upText.contains(suma)) {
					getResultado(upText, "\\"+suma, pulsado);
				} else if (upText.contains(div)) {
					getResultado(upText, div, pulsado);
				} else if (upText.contains(mult)) {
					getResultado(upText, mult, pulsado);
				}
			} else {

				if (upText.length()>=1 && !pulsado.equals(igual)) {
					addText(pulsado);
				}
			}
		}
	}

	public class OnClickListenerEliminar implements OnClickListener {

		public void onClick(View v) {
			EditText editText = getEditText();
			String upText = editText.getText().toString();
			if(upText.length()>=1) {
				editText.setText(upText.substring(0, upText.length() - 1));
			}

		}
	}

	public String getResultado(String upText,String opAnterior,String nuevaOp){

		boolean negativo=false;
		if(upText.startsWith("-")){
			upText=upText.substring(1,upText.length());
			negativo=true;
		}
		String splitted[] = upText.split(opAnterior);
		if (splitted.length > 1){
			double first = Double.valueOf(splitted[0]);
			double second = Double.valueOf(splitted[1]);
			if(negativo){
				first=-first;
			}
			double result=first+second;
			if (opAnterior.equals(resta)){
				result=first-second;
			}else if(opAnterior.equals(suma)){
				result=first+second;
			}else if(opAnterior.equals(mult)){
				result=first*second;
			}else if(opAnterior.equals(div)){
				result=first/second;
			}

			if(nuevaOp.equals(igual)){
				nuevaOp="";
			}

			editText.setText(String.valueOf(result)+nuevaOp);
		}

		return upText;
	}

	
	//=================================================
	// Getters and Setters
	//=================================================
	/**
	 * Cacheamos el edit text de calculos para no tener que buscarlo varias veces con findViewById ya que esto es muy lento
	 * @return
	 */
	public EditText getEditText() {
		if (editText == null){
			editText = (EditText)findViewById(R.id.editTextCalculate);
		}
		return editText;
	}
	//=================================================
	// Test
	//=================================================
}
