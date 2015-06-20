package com.xanxamobile.androidavanzado.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xanxamobile.androidavanzado.BlankFragment;
import com.xanxamobile.androidavanzado.Content_Fragment_4_1_small;
import com.xanxamobile.androidavanzado.R;

/**
 * @author manu
 */
public class ListItemFragment4_1 extends ListFragment {
    public static String textos[] =
            {
                    "Este es el primer texto de todos",
                    "El segundo texto no es tan intuitivo como el segundo pero también vale",
                    "tercer texto",
                    "cuarto texto",
                    "5 Los botones del action bar de arriba están creados por el ListFragment de la izquierda",
                    "6 Pulsa el botón de + para añadir un elemento",
                    "7 Pulsa la papelera para eliminar un elemento",
                    "8 Los nuevos elementos no tendrán texto y si se les pica pondrán a negro el fragment de contenido puesto que no tienen nada escrito",
                    "9",
                    "10 Ejemplo Realizado por Manuel Sánchez Palacios",
                    "11",
                    "12",
                    "13",
                    "14",
                    "15",
                    "16",
                    "17",
                    "18 último elemento del ListView",
            };


    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        List<String> menuLeftItems = new ArrayList<String>();
        for (int i = 0; i < textos.length; i++) {
            menuLeftItems.add((i + 1) + ".- Menu");
        }
        this.setListAdapter(new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, menuLeftItems) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                if (view != null) {
                    //Le ponemos un color aleatorio de background a cada view
                    int color = Color.rgb((int) (255 * Math.random()), (int) (255 * Math.random()), (int) (255 * Math.random()));
                    view.setBackgroundColor(color);

                }
                return view;
            }

        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        //Ahora sustituimos un fragmetn por otro
        ContentFragment4_1 displayFrag = (ContentFragment4_1)
                getFragmentManager().findFragmentById(R.id.contentFragment);
        if (displayFrag == null) {
//Si entramos aquí es porque el fragment no está en pantalla
            Intent intent = new Intent(getActivity(),Content_Fragment_4_1_small.class);

            if (position < textos.length) {

                intent.putExtra("texto", textos[position]);
            }

            startActivity(intent);
        } else {
//Si entramos aquí es porque la pantalla es mayor y el fragment está en pantalla.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ContentFragment4_1 contentFragmentNew = new ContentFragment4_1();
            //le ponemos el texto al fragment pasandole los argomentos mediante un Mapa llamado bundle
            Bundle bundle = new Bundle();
            if (position < textos.length) {
                bundle.putString(ContentFragment4_1.EXTRA_TEXT, textos[position]);

            }

            contentFragmentNew.setArguments(bundle);
            fragmentTransaction.replace(R.id.contentFragment, contentFragmentNew, "contentFragmentNew");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.activity_action_bar, menu);


    }


    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Test
    // ===========================================================


}
