package com.xanxamobile.androidavanzado;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xanxamobile.androidavanzado.receiver.AlarmReceiver;
import com.xanxamobile.androidavanzado.receiver.AlarmReceiver30Minute;

/**
 * VERSION ANDROID STUDIO.
 * ESTA ES LA ACTIVITY INICIAL, EL PUNTO DE PARTIDA.
 * <pre>
 * Este es un menú donde se muestran todos los ejemplos.
 * 
 * Los ejercicios para hacer en casa están etiquetados como <b>"TODO por el alumno"</b> 
 * para buscar estos TODO pulsa la pestaña TODO abajo a la izquierda.
 * En esta pantalla te aparecerán todos los ejercicios que tienes que hacer.
 * Estos ejercicios no son entregables tan sólo servirán para que aprendas
 * Todos están resueltos en código de otras clases java.
 * 
 * Los distintos ejercicios estarán ordenados con una lista de puntos.
 *    Si encuentras un ejercicio en el que pone "<b>EXTRA --></b>" este será un apartado más complejo
 *    Tan sólo opcional.
 * </pre>
 *
 * TODO por el alumno2: Piedra, papel o tijera ver HTML
 * @author Manuel Sánchez Palacios
 *
 */
public class AAListActivityMenu extends ListActivity {
	/**
	 * ESTAS SON TODAS LAS ACTIVITIES QUE SE UTILIZAN EN NUESTOS EJEMPLOS
	 * Está puesta igual que la lista que aparece en la Pantalla del teléfono
	 */
	public final static Object activityEjemplos[][] = {
		{"Interfaces propias Calculator",CalculatorActivity.class},
		{"Adapter Generic", ActivityAdapterGeneric.class},
		{"Action Bar Example",ActivityActionBar.class},
		{"Action Bar Avanzado",AdvancedActionBarActivity.class},
		{"Fragments Example",FragmentActivityExample.class},
		{"Animations",GreenHouseActivity.class},
		{"Animations VS Animator VS LayoutTransitions", ActivityLayoutTransition.class},
		{"Notifications", ActivityNotifications.class},
		{"Alarm", ActivityAlarm.class},
		{"Sensors", ActivitySensor.class},
		{"Gestos", GestureActivity.class},
		{"Reconocimiento de voz", VoiceRecognitionActivity.class},
		{"De texto A voz", TTSActivity.class},
		{"RSS", WidgetRSSReaderActivity.class},
		{"Canvas AsyncTask Animation",ActivityCanvasBackgroundAnimations.class}, 
		{"Activity --> Broadcast --> Service", ActivityBroadCastReceiverAndService.class},
		{"Piedra, papel, tijera",ActivityPiedraPapelTijera.class},
			{"List_View_2_1",ListView2_1.class},
			{"Fragment_4_1",BlankFragment.class},
		};

	public void launchAlarm30(){
		Intent intent = new Intent(this, AlarmReceiver30Minute.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		AlarmManager am = (AlarmManager)this.getSystemService(Activity.ALARM_SERVICE);
		long time = 1*60*1000;

		am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, time, time, pendingIntent);


	}
	
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        Intent intent = getIntent();
	        String path = intent.getStringExtra("com.example.android.apis.Path");
	        
	        if (path == null) {
	            path = "";
	        }
	        String nombreEjemplos[] = new String[this.activityEjemplos.length];
	        for (int i = 0; i < nombreEjemplos.length; i++) {
				nombreEjemplos[i] = (String)this.activityEjemplos[i][0];
			}
	        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombreEjemplos));
	        //Le ponemos fondo a la activity
	        this.getListView().setBackgroundResource(R.drawable.background);
		  launchAlarm30();
	    }
	  
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
		 Intent intent = null;
		intent = new Intent(this, (Class)activityEjemplos[position][1]);//Cargamos la activity
		startActivity(intent);
    }
}
