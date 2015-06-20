package com.xanxamobile.androidavanzado;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xanxamobile.androidavanzado.data.Item2_1;
import com.xanxamobile.androidavanzado.utils.ViewUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListView2_1 extends Activity {

    private List<Item2_1> items;
    private Adapter adapter;
    private ViewGroup viewGroupToAddAdapterView;
    private AdapterView actualAdapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2_1);
        items=new ArrayList<Item2_1>();
        Item2_1 i1 = new Item2_1();
        i1.setText1("hola");
        i1.setText2("hola1");
        Item2_1 i2 = new Item2_1();
        i2.setText1("adios");
        i2.setText2("adios2");
        items.add(i1);
        items.add(i2);
        adapter = new AdapterGeneric(this, items);
        viewGroupToAddAdapterView = (ViewGroup) findViewById(R.id.linearLayoutToAddAdapterView);
        Toast.makeText(this, "List view 2_1", Toast.LENGTH_LONG).show();
        actualAdapterView = new ListView(this);

        attachToViewAndAdapter(actualAdapterView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_view2_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_add:
                Toast.makeText(ListView2_1.this, "Add", Toast.LENGTH_SHORT).show();
                addItem();
                attachToViewAndAdapter(actualAdapterView);
                break;
            case R.id.menu_delete:
                Toast.makeText(ListView2_1.this, "Delete", Toast.LENGTH_SHORT).show();
                removeItem();
                attachToViewAndAdapter(actualAdapterView);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickListView (View view){
        //Primero creamos un listView (lo optimo sería cachearlo, pero no vamos a hacerlo en este ejemplo para que cada vez sea uno nuevo)



        TextView v= (TextView)view.findViewById(R.id.textViewColorName2);
        Toast.makeText(this, v.getText(), Toast.LENGTH_SHORT).show();


    }
    

    private void attachToViewAndAdapter(AdapterView adapterView) {
        //Borramos todos los hijos de la view donde vamos a agnadir el adapterView por si había ya otro adapterView.
        viewGroupToAddAdapterView.removeAllViews();
        viewGroupToAddAdapterView.addView(adapterView);
        //Finalmente añadimos el adapter
        adapterView.setAdapter(adapter);
    }


    /**
     * Este es el adapter generico para todas las AdapterViews definidas
     */
    private class AdapterGeneric extends ArrayAdapter<Item2_1> {

        public AdapterGeneric(Context context, List<Item2_1> objects) {
            super(context, -1, objects);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //1. Si el convertView es nulo hemos de inicializarlo, si no, lo reutilizamos:
            if (convertView == null){
                //Inflamos la vista pasando de del xml adapter_generic_item a un objeto visible.
                convertView = ViewUtil.inflateView(parent.getContext(), R.layout.list_view_item_2_1, parent, false);
                Wrapper wrapper = new Wrapper(convertView);
                //Dentro del convertView alojamos el wrapper para reutilizar las búsquedas del textView.
                convertView.setTag(wrapper);
            }

            //Recuperamos el wrapper de la convert View
            Wrapper wrapper = (Wrapper)convertView.getTag();
            Item2_1 color = getItem(position);
            //Obtenemos el texto del color
           /* String text = colorTextHashMap.get(color);
            //Si no lo tenemos cacheado lo calculamos y lo cacheamos en un hashMap.
            //Si este paso fuese muy pesado tendríamos que hacerlo en segundo plano.
            if (text == null)
            {
                //text = getColorText(color);
                colorTextHashMap.put(color, text);
            }*/
            //Finalmente usamos el texto obtenido y lo mostramos.
            wrapper.getTextViewColorName().setText(color.getText1());
            wrapper.getTextViewColorName2().setText(color.getText2());
            //Cambiamos el fondo de la convertView

            //Devolvemos la convertView con el fondo del color deseado
            return convertView;
        }
    }

    /**
     * Es una clase que cachea las búsquedas de Hijos de la View base
     * @author Manuel Sánchez Palacios
     *
     */
    private class Wrapper {
        View base;
        TextView textViewColorName;
        TextView textViewColorName2;

        public Wrapper(View base) {
            super();
            this.base = base;
        }
        /**
         * Con este método nos ahorramos tener que hacer un findViewById, que es muy lento, cada vez que preguntemos por el textView
         * @return el text view actual
         */
        public TextView getTextViewColorName() {
            if (textViewColorName == null)
                textViewColorName = (TextView)base.findViewById(R.id.textViewColorName);
            return textViewColorName;
        }

        public TextView getTextViewColorName2() {
            if (textViewColorName2 == null)
                textViewColorName2 = (TextView)base.findViewById(R.id.textViewColorName2);
            return textViewColorName2;
        }



    }
    public void addItem(){
        Item2_1 i1 = new Item2_1();
        i1.setText1("hola");
        i1.setText2("hola1");
        items.add(i1);
    }

    public void removeItem(){
        if(items.size()>0) {
            items.remove(items.size() - 1);
        }
    }


}
